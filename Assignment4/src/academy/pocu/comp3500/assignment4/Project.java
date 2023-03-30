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

        HashMap<Node, Boolean> discovered = new HashMap<>();
        LinkedList<Node> postorderTraversalReverseList = new LinkedList<>();
        for (Node n : this.totalStartNodes) {
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
        ArrayList<Node> startNodes = new ArrayList<>(this.totalStartNodes.size());

        getStartNodesToTask(task, startNodes);
        
        LinkedList<Node> postorderTraversalReverseList = new LinkedList<>();
        HashMap<Node, Boolean> discovered = new HashMap<>();
        int[] estimate = new int[1];
        int resultSum = 0;

        for (Node n : startNodes) {
            estimate[0] = 0;
            getPostorderTraversalReverseListToTaskRecursive(task, n, true, estimate ,discovered, postorderTraversalReverseList);

            if (resultSum < estimate[0]) {
                resultSum = estimate[0];
            }
        }

        for (Node n : postorderTraversalReverseList) {
            resultSum += n.getEstimate();
        }

        return resultSum;
    }

    public int findMaxBonusCount(final String task) {
        return -1;
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
        ArrayList<Node> startNodes = new ArrayList<>(this.totalStartNodes.size());

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

    private boolean getPostorderTraversalReverseListToTaskRecursive(final String task,
                                                                    final Node node,
                                                                    boolean isSinglePath,
                                                                    int[] maxEstimate,
                                                                    final HashMap<Node, Boolean> added,
                                                                    final LinkedList<Node> out) {
        if (node.getTitle().equals(task)) {
            if (added.get(node) == null) {
                added.put(node, true);
                out.addFirst(node);
            }

            return true;
        } else if (node.isLoopNode() || node.getNeighborsSize() == 0) {
            return false;
        }

        if (isSinglePath) {
            isSinglePath = (this.reverseDirectionNodes[this.taskIndex.get(node.getTitle())].getNeighborsSize() <= 1);
        }

        boolean hasTask = false;
        for (var neighbor : node.getNeighbors()) {
            if (getPostorderTraversalReverseListToTaskRecursive(task, neighbor, isSinglePath, maxEstimate, added, out)) {
                hasTask = true;
            }
        }

        if (hasTask && added.get(node) == null) {
            if (isSinglePath) {
                maxEstimate[0] += node.getEstimate();
            } else {
                out.addFirst(node);
                added.put(node, true);
            }
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