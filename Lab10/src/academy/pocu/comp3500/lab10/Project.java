package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        int[][] matrix = new int[tasks.length][tasks.length];

        HashMap<Task, Integer> taskIndex = new HashMap<>(tasks.length);
        Node[] taskNodes = new Node[tasks.length];
        ArrayList<Node> startNodes = new ArrayList<>(); // I think there's always only one start node

        for (int i = 0; i < tasks.length; ++i) {
            taskIndex.put(tasks[i], i);
            taskNodes[i] = new Node(tasks[i].getTitle());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                startNodes.add(taskNodes[i]);
                continue;
            }

            for (var pre : tempPredecessors) {
                matrix[i][taskIndex.get(pre)] = 1;
            }
        }

        for (int i = 0; i < taskNodes.length; ++i) {
            for (int j = 0; j < tasks.length; ++j) {
                if (matrix[j][i] == 1) {
                    taskNodes[i].addNeighbor(taskNodes[j]);
                }
            }
        }

        return null;
    }


}