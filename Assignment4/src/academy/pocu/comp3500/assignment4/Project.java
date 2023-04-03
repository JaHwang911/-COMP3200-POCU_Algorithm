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
    private final ArrayList<Node> startNodes;

    public Project(final Task[] tasks) {
        this.taskIndex = new HashMap<>(tasks.length);
        this.nodes = new Node[tasks.length];
        this.reverseDirectionNodes = new Node[tasks.length];
        this.startNodes = new ArrayList<>(tasks.length);
        this.edges = new HashMap<>();

        for (int i = 0; i < tasks.length; ++i) {
            this.taskIndex.put(tasks[i].getTitle(), i);
            this.nodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
            this.reverseDirectionNodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                this.startNodes.add(this.nodes[i]);
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
        for (Node n : this.startNodes) {
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
            ArrayList<Edge> tempEdges = this.edges.get(n);

            if (tempEdges == null) {
                tempEdges = new ArrayList<>(n.getNeighborsSize());
                this.edges.put(n, tempEdges);
            }

            for (Node neighbor : n.getNeighbors()) {
                Edge edge = new Edge(n, neighbor, n.getEstimate());
                Edge backEdge = new Edge(neighbor, n, 0);

                edge.addBackEdge(backEdge);
                backEdge.addBackEdge(edge);

                tempEdges.add(edge);

                ArrayList<Edge> tempBackEdge = this.edges.get(neighbor);

                if (tempBackEdge == null) {
                    tempBackEdge = new ArrayList<>(64);
                    this.edges.put(neighbor, tempBackEdge);
                }

                tempBackEdge.add(backEdge);
            }
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
        for (Node n : this.startNodes) {
            if (n.getTitle().equals(task)) {
                return n.getEstimate();
            }
        }

        int result = 0;

        initEdgesAmount();

        LinkedList<Edge> outEdge = new LinkedList<>();
        LinkedList<Node> outPath = new LinkedList<>();
        int minFlow = getMinFlowToTask(task, outEdge, outPath);

        while (minFlow != -1) {
            Node currentNode = outPath.poll();
            Node nextNode = outPath.poll();

            while (!outEdge.isEmpty()) {
                Edge edge = outEdge.poll();
                edge.addAmount(minFlow);

                if (currentNode != null && nextNode != null
                        && edge.getStartNode().getTitle().equals(currentNode.getTitle())
                        && edge.getEndNode().getTitle().equals(nextNode.getTitle())) {
                    edge.getBackEdge().addAmount(-minFlow);

                    currentNode = nextNode;
                    nextNode = outPath.poll();
                }
            }

            outPath.clear();

            minFlow = getMinFlowToTask(task, outEdge, outPath);
        }

        Node targetNode = this.nodes[this.taskIndex.get(task)];
        ArrayList<Edge> resultEdge = this.edges.get(targetNode);

        for (Edge e : resultEdge) {
            if (e.isBackEdge()) {
                result -= e.getAmount();
            }
        }

        return Math.min(result, targetNode.getEstimate());
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
        ArrayList<Node> startNodes = new ArrayList<>(this.startNodes.size());

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

    private int getMinFlowToTask(final String task, LinkedList<Edge> outEdges, LinkedList<Node> outPath) {
        HashMap<Node, Node> prevNodes = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Edge> backEdgeQueue = new LinkedList<>();

        for (Node node : this.startNodes) {
            queue.add(node);
        }

        Node targetNode = null;
        while (!queue.isEmpty() && targetNode == null) {
            Node currNode = queue.poll();
            boolean hasOnlyBackEdge = true;
            backEdgeQueue.clear();

            for (Edge edge : this.edges.get(currNode)) {
                if (edge.getRemainingAmount() == 0) {
                    continue;
                }

                if (!edge.isBackEdge()) {
                    hasOnlyBackEdge = false;
                } else {
                    backEdgeQueue.add(edge);
                    continue;
                }

                Node next = edge.getEndNode();
                if (prevNodes.get(next) == null) {
                    queue.add(next);
                    prevNodes.put(next, currNode);

                    if (next.getTitle().equals(task)) {
                        targetNode = next;
                        prevNodes.put(targetNode, currNode);
                        outPath.addFirst(targetNode);
                        break;
                    }
                }
            }

            if (hasOnlyBackEdge) {
                for (Edge backEdge : backEdgeQueue) {
                    assert (backEdge.getAmount() < 0);
                    int amount = backEdge.getAmount();

                    Node node = backEdge.getEndNode();
                    if (prevNodes.get(currNode).equals(node)) {
                        continue;
                    }

                    queue.add(node);
                    for (Edge e : this.edges.get(node)) {
                        if (!e.isBackEdge()) {
                            e.addAmount(amount);
                        }
                    }

                    backEdge.addAmount(-amount);
                }
            }
        }

        if (targetNode == null) {
            return -1;
        }

        int resultMinFlow = Integer.MAX_VALUE;

        do {
            Node currNode = prevNodes.get(targetNode);
            outPath.addFirst(currNode);

            for (Edge edge : this.edges.get(currNode)) {
                if (!edge.isBackEdge()) {
                    outEdges.addFirst(edge);
                    resultMinFlow = Math.min(resultMinFlow, edge.getRemainingAmount());
                }
            }

            targetNode = currNode;
        } while (prevNodes.get(targetNode) != null);

        return resultMinFlow;
    }

    private void initEdgesAmount() {
        for (Node n : this.nodes) {
            for (Edge e : this.edges.get(n)) {
                e.initAmount();
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