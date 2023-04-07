package scc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public final class SCC {
    public static void printSCCCountAndList(Node[] nodes) {
        HashMap<String, Integer> nodeIndex = new HashMap<>();
        Node[] directionNodes = new Node[nodes.length];
        Node[] reverseDirectionNodes = new Node[nodes.length];

        for (int i = 0; i < nodes.length; ++i) {
            nodeIndex.put(nodes[i].getTitle(), i);
            directionNodes[i] = new Node(nodes[i]);
            reverseDirectionNodes[i] = new Node(nodes[i].getTitle());
        }

        for (int i = 0; i < reverseDirectionNodes.length; ++i) {
            for (Node neighbor : directionNodes[i].getNeighbors()) {
                int index = nodeIndex.get(neighbor.getTitle());
                reverseDirectionNodes[index].addNeighbor(reverseDirectionNodes[i]);
            }
        }

        LinkedList<Node> outTopologicalList = new LinkedList<>();
        HashSet<Node> discovered = new HashSet<>();
        for (Node node : directionNodes) {
            sortTopologicallyRecursive(node, discovered, outTopologicalList);
        }

        assert (outTopologicalList.size() == nodes.length);

        LinkedList<Node> tempOut = new LinkedList<>();
        int numOfSCC = 0;
        discovered.clear();

        for (Node node : outTopologicalList) {
            tempOut.clear();
            int index = nodeIndex.get(node.getTitle());

            getSCCRecursive(reverseDirectionNodes[index], discovered, tempOut);

            if (tempOut.size() == 0) {
                continue;
            }

            if (tempOut.size() == 1) {
                System.out.printf("- %s", tempOut.getFirst().getTitle());
            } else {
                System.out.printf("- %s", tempOut.getFirst().getTitle());

                for (int i = tempOut.size() - 1; i >= 1; --i) {
                    System.out.printf(" %s", tempOut.get(i).getTitle());
                }
            }

            ++numOfSCC;
            System.out.println();
        }

        System.out.printf("Total SCC Count: %d", numOfSCC);
        System.out.println();
    }

    private static void sortTopologicallyRecursive(Node node, HashSet<Node> discovered, LinkedList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);

        for (Node n : node.getNeighbors()) {
            sortTopologicallyRecursive(n, discovered, out);
        }

        out.addFirst(node);
    }

    private static void getSCCRecursive(Node node, HashSet<Node> discovered, LinkedList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);

        for (Node neighbor : node.getNeighbors()) {
            getSCCRecursive(neighbor, discovered, out);
        }

        out.addFirst(node);
    }
}
