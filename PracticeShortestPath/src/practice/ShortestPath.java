package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public final class ShortestPath {
    public static LinkedList<Node> Run(Node startNode, Node destination) {
        HashMap<Node, Node> prevNode = new HashMap<>();
        HashSet<Node> discovered = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);
        discovered.add(startNode);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.getTitle().equals(destination.getTitle())) {
                break;
            }

            for (Node neighbor : curr.getNeighbors()) {
                if (!discovered.contains(neighbor)) {
                    queue.add(neighbor);
                    discovered.add(neighbor);
                    prevNode.put(neighbor, curr);
                }
            }
        }

        LinkedList<Node> shortestPath = new LinkedList<>();
        Node next = destination;
        shortestPath.addFirst(next);

        while (prevNode.get(next) != null) {
            next = prevNode.get(next);
            shortestPath.addFirst(next);
        }

        return shortestPath;
    }
}
