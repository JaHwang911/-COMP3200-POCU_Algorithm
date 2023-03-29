package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class Project {
    private final HashMap<String, Integer> taskIndex;
    private final Node[] nodes;
    private final Node[] reverseDirectionNodes;
    private final ArrayList<Node> totalStartNodes;

    public Project(final Task[] tasks) {
        this.taskIndex = new HashMap<>(tasks.length);
        this.nodes = new Node[tasks.length];
        this.reverseDirectionNodes = new Node[tasks.length];
        this.totalStartNodes = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
            this.taskIndex.put(tasks[i].getTitle(), i);
            this.nodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
            this.reverseDirectionNodes[i] = new Node(tasks[i].getTitle(), tasks[i].getEstimate());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                this.totalStartNodes.add(nodes[i]);
                continue;
            }

            int toNodeIndex = this.taskIndex.get(tasks[i].getTitle());

            for (var pre : tempPredecessors) {
                int fromNodeIndex = this.taskIndex.get(pre.getTitle());

                this.nodes[fromNodeIndex].addNeighbor(this.nodes[toNodeIndex]);
                this.reverseDirectionNodes[toNodeIndex].addNeighbor(this.reverseDirectionNodes[fromNodeIndex]);
            }
        }
    }

    public int findTotalManMonths(final String task) {
        LinkedList<Node> reversePostorderTraversal = getReversePostorderTraversalList(task);
        HashMap<Node, Boolean> discovered = new HashMap<>();
        LinkedList<Node> tempOut = new LinkedList<>();

        int i = 0;
        int resultSum = 0;

        while (i < reversePostorderTraversal.size()) {
            tempOut.clear();
            Node currentNode = this.reverseDirectionNodes[this.taskIndex.get(reversePostorderTraversal.get(i).getTitle())];

            getSCC(currentNode, discovered, tempOut);

            int nodeCount = tempOut.size();

            if (nodeCount == 1) {
                resultSum += this.nodes[taskIndex.get(tempOut.getFirst().getTitle())].getEstimate();
            }

            i += nodeCount;
        }

        return resultSum;
    }

    public int findMinDuration(final String task) {
        LinkedList<Node> reversePostorderTraversal = getReversePostorderTraversalList(task);
        HashMap<Node, Boolean> discovered = new HashMap<>();
        LinkedList<Node> tempOut = new LinkedList<>();

        int i = 0;
        int resultSum = 0;
        int maxStartNodeEstimate = 0;

        while (i < reversePostorderTraversal.size()) {
            tempOut.clear();
            Node currentNode = this.reverseDirectionNodes[this.taskIndex.get(reversePostorderTraversal.get(i).getTitle())];

            getSCC(currentNode, discovered, tempOut);

            int nodeCount = tempOut.size();

            if (nodeCount == 1) {
                Node node = tempOut.getFirst();

                if (node.getNeighbors().size() == 0) {
                    if (maxStartNodeEstimate < node.getEstimate()) {
                        maxStartNodeEstimate = node.getEstimate();
                    }

                    i += nodeCount;
                    continue;
                }

                resultSum += this.nodes[taskIndex.get(node.getTitle())].getEstimate();
            }

            i += nodeCount;
        }

        resultSum += maxStartNodeEstimate;

        return resultSum;
    }

    public int findMaxBonusCount(final String task) {
        return -1;
    }

    private void getStartNodes(final String task, final ArrayList<Node> out) {
        int index = this.taskIndex.get(task);
        Node milestone = this.reverseDirectionNodes[index];
        HashMap<Node, Boolean> discovered = new HashMap<>();
        Stack<Node> stack = new Stack<>();

        discovered.put(milestone, true);
        stack.push(milestone);

        while (!stack.empty()) {
            Node next = stack.pop();

            if (next.getNeighbors().size() == 0) {
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

    private LinkedList<Node> getReversePostorderTraversalList(final String task) {
        ArrayList<Node> startNodes = new ArrayList<>(this.totalStartNodes.size());

        getStartNodes(task, startNodes);

        LinkedList<Node> reversePostorderTraversal = new LinkedList<>();
        HashMap<Node, Boolean> discovered = new HashMap<>();

        for (Node n : startNodes) {
            getReversePostorderTraversalListRecursive(task, n, discovered, reversePostorderTraversal);
        }

        return reversePostorderTraversal;
    }

    private boolean getReversePostorderTraversalListRecursive(final String task, final Node node, final HashMap<Node, Boolean> discovered, final LinkedList<Node> out) {
        if (discovered.get(node) != null) {
            return true;
        } else if (node.getTitle().equals(task)) {
            discovered.put(node, true);
            out.addFirst(node);
            return true;
        }

        discovered.put(node, true);
        boolean hasTask = false;
        for (var neighbor : node.getNeighbors()) {
            hasTask = getReversePostorderTraversalListRecursive(task, neighbor, discovered, out);
        }

        if (hasTask) {
            out.addFirst(node);
        }

        return hasTask;
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