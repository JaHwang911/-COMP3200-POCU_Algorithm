package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public final class Project {
    private final HashMap<String, Integer> taskIndex;
    private final Node[] nodes;
    private final Node[] reverseDirectionNodes;
    private final HashMap<Node, ArrayList<Edge>> edges;
    private final HashMap<Node, ArrayList<Edge>> backEdges;
    private final Node virtualStartNode;

    public Project(final Task[] tasks) {
        this.taskIndex = new HashMap<>(tasks.length);
        this.nodes = new Node[tasks.length];
        this.reverseDirectionNodes = new Node[tasks.length];
        this.edges = new HashMap<>();
        this.backEdges = new HashMap<>();
        this.virtualStartNode = new Node("", 0);

        for (int i = 0; i < tasks.length; ++i) {
            this.taskIndex.put(tasks[i].getTitle(), i);
            this.nodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
            this.reverseDirectionNodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                this.virtualStartNode.addNeighbor(this.nodes[i]);
                continue;
            }

            int toNodeIndex = this.taskIndex.get(tasks[i].getTitle());

            for (var pre : tempPredecessors) {
                int fromNodeIndex = this.taskIndex.get(pre.getTitle());

                this.nodes[fromNodeIndex].addNeighbor(this.nodes[toNodeIndex]);
                this.reverseDirectionNodes[toNodeIndex].addNeighbor(this.reverseDirectionNodes[fromNodeIndex]);
            }
        }

        HashMap<Node, Boolean> discovered = new HashMap<>();
        LinkedList<Node> postorderTraversalReverseList = new LinkedList<>();
        for (Node n : this.virtualStartNode.getNeighbors()) {
            getPostorderTraversalReverseListRecursive(n, discovered, postorderTraversalReverseList);
        }

        LinkedList<Node> tempOut = new LinkedList<>();
        discovered.clear();

        for (Node n : postorderTraversalReverseList) {
            tempOut.clear();
            Node currentNode = this.reverseDirectionNodes[this.taskIndex.get(n.getTitle())];

            getSCC(currentNode, discovered, tempOut);

            if (tempOut.size() > 1) {
                for (Node loop : tempOut) {
                    loop.setIsLoop();
                    this.nodes[this.taskIndex.get(loop.getTitle())].setIsLoop();
                }
            }
        }

        for (Node n : this.nodes) {
            ArrayList<Edge> tempEdges = new ArrayList<>(n.getNeighborsSize());

            for (Node neighbor : n.getNeighbors()) {
                tempEdges.add(new Edge(n, neighbor, n.getEstimate()));
            }

            this.edges.put(n, tempEdges);
        }

        for (Node n : this.reverseDirectionNodes) {
            ArrayList<Edge> tempBackEdges = new ArrayList<>(n.getNeighborsSize());

            for (Node neighbor : n.getNeighbors()) {
                tempBackEdges.add(new Edge(n, neighbor, 0));
            }

            this.backEdges.put(n, tempBackEdges);
        }
    }

    public int findTotalManMonths(final String task) {
        LinkedList<Node> postorderTraversalReverseList = getPostorderTraversalReverseListToTask(task);
        int resultSum = 0;

        for (Node n : postorderTraversalReverseList) {
            resultSum += n.getEstimate();
        }

        return resultSum;
    }

    public int findMinDuration(final String task) {
        return getMinDurationRecursive(this.reverseDirectionNodes[this.taskIndex.get(task)]);
    }

    public int findMaxBonusCount(final String task) {
        int result = 0;

        initEdgesAmount();

        LinkedList<Node> outShortestPath = new LinkedList<>();
        int minFlow = getMinFlowToTask(task, outShortestPath);

        while (minFlow != -1) {
            for (Node n : outShortestPath) {
                n.addAmount(minFlow);
                n.addBackEdgeAmount(-minFlow);
            }

            outShortestPath.clear();
            minFlow = getMinFlowToTask(task, outShortestPath);
        }

        Node milestone = this.reverseDirectionNodes[this.taskIndex.get(task)];
        ArrayList<Edge> backEdges = this.backEdges.get(milestone);

        for (Edge e : backEdges) {
            result += e.getAmount();
        }

        return result;
    }

    private void getPostorderTraversalReverseListRecursive(final Node node, final HashMap<Node, Boolean> discovered, final LinkedList<Node> out) {
        if (discovered.get(node) != null) {
            return;
        }

        discovered.put(node, true);
        
        for (Node n : node.getNeighbors()) {
            getPostorderTraversalReverseListRecursive(n, discovered, out);
        }

        out.addFirst(node);
    }

    private void getStartNodesToTask(final String task, final ArrayList<Node> out) {
        int index = this.taskIndex.get(task);
        Node milestone = this.reverseDirectionNodes[index];
        HashMap<Node, Boolean> discovered = new HashMap<>();
        Stack<Node> stack = new Stack<>();

        discovered.put(milestone, true);
        stack.push(milestone);

        while (!stack.empty()) {
            Node next = stack.pop();

            if (next.getNeighborsSize() == 0) {
                out.add(this.nodes[this.taskIndex.get(next.getTitle())]);
            }

            for (Node n : next.getNeighbors()) {
                if (discovered.get(n) == null) {
                    stack.push(n);
                    discovered.put(n, true);
                }
            }
        }
    }

    private LinkedList<Node> getPostorderTraversalReverseListToTask(final String task) {
        ArrayList<Node> startNodes = new ArrayList<>(this.virtualStartNode.getNeighborsSize());

        getStartNodesToTask(task, startNodes);

        LinkedList<Node> postorderTraversalReverseList = new LinkedList<>();
        HashMap<Node, Boolean> added = new HashMap<>();

        for (Node n : startNodes) {
            getPostorderTraversalReverseListToTaskRecursive(task, n, added, postorderTraversalReverseList);
        }

        return postorderTraversalReverseList;
    }

    private boolean getPostorderTraversalReverseListToTaskRecursive(final String task, final Node node, final HashMap<Node, Boolean> added, final LinkedList<Node> out) {
        if (node.getTitle().equals(task)) {
            if (added.get(node) == null && !node.isLoopNode()) {
                added.put(node, true);
                out.addFirst(node);
            }

            return true;
        } else if (node.isLoopNode() || node.getNeighborsSize() == 0) {
            return false;
        }

        boolean hasTask = false;
        for (var neighbor : node.getNeighbors()) {
            if (getPostorderTraversalReverseListToTaskRecursive(task, neighbor, added, out)) {
                hasTask = true;
            }
        }

        if (hasTask && added.get(node) == null) {
            out.addFirst(node);
            added.put(node, true);
        }

        return hasTask;
    }

    private int getMinDurationRecursive(final Node node) {
        if (node.getNeighborsSize() == 0) {
            return node.getEstimate();
        }

        int sum = node.getEstimate();
        int max = Integer.MIN_VALUE;

        for (Node n : node.getNeighbors()) {
            int temp = getMinDurationRecursive(n);
            if (max < temp) {
                max = temp;
            }
        }

        sum += max;

        return sum;
    }

    private int getMinFlowToTask(final String task, LinkedList<Node> out) {
        HashMap<Node, Node> prevNode = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        prevNode.put(this.virtualStartNode, this.virtualStartNode);
        queue.add(this.virtualStartNode);

        boolean isFindTask = false;

        while (!queue.isEmpty() && !isFindTask) {
            Node curr = queue.poll();

            if (prevNode.get(curr) != null) {
                continue;
            }

            for (Edge e : this.edges.get(curr)) {
                if (e.getRemainingAmount() == 0) {
                    ArrayList<Edge> backEdges = this.backEdges.get(curr);

                    for (Edge b : backEdges) {
                        if (e.getRemainingAmount() == 0) {
                            continue;
                        }

                        prevNode.put(b.getStartNode(), b.getEndNode());
                    }

                    continue;
                }

                Node next = e.getEndNode();
                if (prevNode.get(next) == null) {
                    queue.add(next);
                    prevNode.put(next, curr);

                    if (next.getTitle().equals(task)) {
                        out.addFirst(next);
                        isFindTask = true;
                        break;
                    }
                }
            }
        }

        if (!isFindTask) {
            return -1;
        }

        Node currNode = out.getFirst();
        while (!prevNode.get(currNode).getTitle().equals(this.virtualStartNode.getTitle())) {
            Node node = prevNode.get(currNode);
            out.addFirst(node);

            currNode = node;
        }

        int resultMinFlow = Integer.MAX_VALUE;
        ArrayList<Edge> edgesResult = new ArrayList<>();

        // Todo
        // 밑에 반복문에서 경로 간선들만 전부 받아서 반환.
        // 현재 최단 경로를 찾는 out은 이 함수의 지역 변수 선언

        for (int i = 0; i < out.size() - 1; ++i) {
            ArrayList<Edge> temp = this.edges.get(out.get(i));

            for (Edge e : temp) {
                if (e.getEndNode().equals(out.get(i + 1))) {
                    resultMinFlow = Math.min(resultMinFlow, e.getRemainingAmount());
                    break;
                }
            }
        }

        return resultMinFlow;
    }

    private void initEdgesAmount() {
        for (Node n : this.nodes) {
            for (Edge e : this.edges.get(n)) {
                e.initAmount();
            }
        }

        for (Node rn : this.reverseDirectionNodes) {
            for (Edge bE: this.backEdges.get(rn)) {
                bE.initAmount();
            }
        }
    }

    private void getSCC(final Node node, final HashMap<Node, Boolean> discovered, final LinkedList<Node> out) {
        if (discovered.get(node) != null) {
            return;
        }

        discovered.put(node, true);
        for (var neighbor : node.getNeighbors()) {
            getSCC(neighbor, discovered, out);
        }

        out.addFirst(node);
    }
}