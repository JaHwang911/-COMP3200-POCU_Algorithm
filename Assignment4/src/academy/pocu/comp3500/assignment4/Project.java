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
    private final Node virtualStartNode;

    public Project(final Task[] tasks) {
        this.taskIndex = new HashMap<>(tasks.length);
        this.nodes = new Node[tasks.length];
        this.reverseDirectionNodes = new Node[tasks.length];
        this.edges = new HashMap<>();
        this.virtualStartNode = new Node("", Integer.MAX_VALUE);

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

        ArrayList<Edge> tempVirtualEdges = new ArrayList<>();
        this.edges.put(this.virtualStartNode, tempVirtualEdges);

        for (Node n : this.virtualStartNode.getNeighbors()) {
            ArrayList<Edge> tempEdges = this.edges.get(n);

            if (tempEdges == null) {
                tempEdges = new ArrayList<>(n.getNeighborsSize());
                this.edges.put(n, tempEdges);
            }

            Edge e = new Edge(this.virtualStartNode, n, Integer.MAX_VALUE);
            Edge be = new Edge(n, this.virtualStartNode, 0);

            e.addBackEdge(be);
            be.addBackEdge(e);

            tempVirtualEdges.add(e);
            tempEdges.add(be);
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
        for (Node n : this.virtualStartNode.getNeighbors()) {
            if (n.getTitle().equals(task)) {
                return n.getEstimate();
            }
        }

        int result = 0;

        initEdgesAmount();

        LinkedList<Edge> outEdge = new LinkedList<>();
        HashMap<Node, Boolean> discovered = new HashMap<>();
        int minFlow = getMinFlowToTask(task, outEdge);

        while (minFlow != -1) {
            for (Edge e : outEdge) {
                e.addAmount(minFlow);
                e.getBackEdge().addAmount(-minFlow);

                if (discovered.get(e.getStartNode()) == null) {
                    if (e.isBackEdge()) {
                        e.getStartNode().addAmount(-minFlow);
                    } else {
                        e.getStartNode().addAmount(minFlow);
                    }
                    discovered.put(e.getStartNode(), true);
                }
            }

            outEdge.clear();
            discovered.clear();
            minFlow = getMinFlowToTask(task, outEdge);
        }

        Node prevNode = this.nodes[this.taskIndex.get(task)];
        ArrayList<Edge> resultEdge = this.edges.get(prevNode);

        for (Edge e : resultEdge) {
            if (e.isBackEdge()) {
                result -= e.getAmount();
            }
        }

        return Math.min(result, prevNode.getEstimate());
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

    private int getMinFlowToTask(final String task, LinkedList<Edge> out) {
        HashMap<Node, Node> prevNodes = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Node[] tempStartNode = new Node[this.virtualStartNode.getNeighborsSize()];

        Node prevNode = null;

        ArrayList<Edge> tempStartEdges = this.edges.get(this.virtualStartNode);

        for (int i = 0; i < tempStartEdges.size(); ++i) {
            assert (!tempStartEdges.get(i).isBackEdge());
            tempStartNode[i] = tempStartEdges.get(i).getEndNode();
        }

        sortNodeByEstimateRecursive(tempStartNode, 0, tempStartNode.length - 1);

        for (Node n : tempStartNode) {
            prevNodes.put(n, this.virtualStartNode);
            queue.add(n);

            if (n.getTitle().equals(task)) {
                prevNode = n;
                break;
            }
        }

        while (!queue.isEmpty() && prevNode == null) {
            Node currNode = queue.poll();

            for (Edge e : this.edges.get(currNode)) {
                if (e.getRemainingAmount() == 0) {
                    continue;
                }

                Node next = e.getEndNode();
                if (prevNodes.get(next) == null) {
                    queue.add(next);
                    prevNodes.put(next, currNode);

                    if (next.getTitle().equals(task)) {
                        prevNode = next;
                        break;
                    }
                }
            }
        }

        if (prevNode == null) {
            return -1;
        }

        int resultMinFlow = Integer.MAX_VALUE;

        do {
            Node currNode = prevNodes.get(prevNode);
            boolean isRightWay = false;

            for (Node neighbor : currNode.getNeighbors()) {
                if (neighbor.equals(prevNode)) {
                    isRightWay = true;
                    break;
                }
            }

            ArrayList<Edge> tempEdges = this.edges.get(currNode);

            for (Edge e : tempEdges) {
                if (isRightWay) {
                    if (!e.isBackEdge()) {
                        out.addFirst(e);
                        resultMinFlow = Math.min(resultMinFlow, e.getRemainingAmount());
                    }
                } else {
                    if (e.isBackEdge()) {
                        out.addFirst(e);
                        resultMinFlow = Math.min(resultMinFlow, e.getRemainingAmount());
                    }
                }
            }

            prevNode = currNode;
        } while (prevNodes.get(prevNode) != null && !prevNodes.get(prevNode).getTitle().equals(this.virtualStartNode.getTitle()));

        return resultMinFlow;
    }

    private void sortNodeByEstimateRecursive(Node[] node, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (node[j].getRemainingAmount() > node[right].getRemainingAmount()) {
                Node temp = node[j];
                node[j] = node[i];
                node[i] = temp;

                ++i;
            }
        }

        Node temp = node[i];
        node[i] = node[right];
        node[right] = temp;

        sortNodeByEstimateRecursive(node, left, i - 1);
        sortNodeByEstimateRecursive(node, i + 1, right);
    }

    private void initEdgesAmount() {
        for (Node n : this.nodes) {
            for (Edge e : this.edges.get(n)) {
                e.initAmount();
            }

            n.initAmount();
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