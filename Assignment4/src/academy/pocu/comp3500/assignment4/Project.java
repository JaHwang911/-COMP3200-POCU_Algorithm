package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Project {
    public Project(final Task[] tasks) {
        HashMap<String, Integer> taskIndex = new HashMap<>(tasks.length);
        Node[] nodes = new Node[tasks.length];
        Node[] reverseDirectionNodes = new Node[tasks.length];
        ArrayList<Node> startNodes = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
            taskIndex.put(tasks[i].getTitle(), i);
            nodes[i] = new Node(tasks[i].getTitle());
            reverseDirectionNodes[i] = new Node(tasks[i].getTitle());
        }

        for (int i = 0; i < tasks.length; ++i) {
            List<Task> tempPredecessors = tasks[i].getPredecessors();

            if (tempPredecessors.size() == 0) {
                startNodes.add(nodes[i]);
                continue;
            }

            int toNodeIndex = taskIndex.get(tasks[i].getTitle());

            for (var pre : tempPredecessors) {
                int fromNodeIndex = taskIndex.get(pre.getTitle());

                nodes[fromNodeIndex].addNeighbor(nodes[toNodeIndex]);
                reverseDirectionNodes[toNodeIndex].addNeighbor(reverseDirectionNodes[fromNodeIndex]);
            }
        }
    }

    public int findTotalManMonths(final String task) {
        return -1;
    }

    // 스타트 노드의 최대치를 포함 시켜서 반환
    public int findMinDuration(final String task) {
        return -1;
    }

    public int findMaxBonusCount(final String task) {
        return -1;
    }
}