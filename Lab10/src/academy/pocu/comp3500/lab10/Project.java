package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        int[][] matrix = new int[tasks.length][tasks.length];

        HashMap<String, Integer> taskIndex = new HashMap<>(tasks.length);
        Node[] taskNodes = new Node[tasks.length];
        Node[] reverseDirectionNodes = new Node[tasks.length];
        ArrayList<Node> startNodes = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
            taskIndex.put(tasks[i].getTitle(), i);
            taskNodes[i] = new Node(tasks[i].getTitle());
            reverseDirectionNodes[i] = new Node(tasks[i].getTitle());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                startNodes.add(taskNodes[i]);
                continue;
            }

            for (var pre : tempPredecessors) {
                matrix[i][taskIndex.get(pre.getTitle())] = 1;
            }
        }

        for (int i = 0; i < taskNodes.length; ++i) {
            for (int j = 0; j < tasks.length; ++j) {
                if (matrix[j][i] == 1) {
                    taskNodes[i].addNeighbor(taskNodes[j]);
                }

                if (matrix[i][j] == 1) {
                    reverseDirectionNodes[i].addNeighbor(reverseDirectionNodes[j]);
                }
            }
        }

        HashSet<Node> discovered = new HashSet<>(tasks.length);
        LinkedList<Node> out = new LinkedList<>();

        for (Node node : startNodes) {
            postorderTraversalRecursive(node, discovered, out);
        }
        ArrayList<String> result = new ArrayList<>();

        int i = 0;

        LinkedList<Node> tempOut = new LinkedList<>();
        discovered.clear();

        while (i < out.size()) {
            tempOut.clear();
            int index = taskIndex.get(out.get(i).getTitle());

            postorderTraversalRecursive(reverseDirectionNodes[index], discovered, tempOut);

            if (tempOut.size() == 1) {
                result.add(tempOut.getFirst().getTitle());
            } else if (includeMaintenance && tempOut.size() > 1) {
                result.add(tempOut.getFirst().getTitle());

                for (int j = tempOut.size() - 1; j >= 1; --j) {
                    result.add(tempOut.get(j).getTitle());
                }
            }

            i += tempOut.size();
        }

        return result;
    }

    private static void postorderTraversalRecursive(Node node, final HashSet<Node> discovered, final LinkedList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);
        for (var neighbor : node.getNeighbor()) {
            postorderTraversalRecursive(neighbor, discovered, out);
        }

        out.addFirst(node);
    }
}