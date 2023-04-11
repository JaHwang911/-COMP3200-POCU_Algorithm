package practice;

import java.util.ArrayList;
import java.util.HashSet;

public final class DFS {
    public static ArrayList<Node> depthFirstSearchByPostorder(Node node) {
        HashSet<Node> discovered = new HashSet<>();
        ArrayList<Node> ret = new ArrayList<>();

        dfsByPostorder(node, discovered, ret);

        return ret;
    }

    public static ArrayList<Node> depthFirstSearchByPreorder(Node node) {
        HashSet<Node> discovered = new HashSet<>();
        ArrayList<Node> ret = new ArrayList<>();

        dfsByPreorder(node, discovered, ret);

        return ret;
    }

    private static void dfsByPostorder(Node node, HashSet<Node> discovered, ArrayList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);

        for (Node neighbor : node.neighbors) {
            dfsByPostorder(neighbor, discovered, out);
        }

        out.add(node);
    }

    private static void dfsByPreorder(Node node, HashSet<Node> discovered, ArrayList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);
        out.add(node);

        for (Node neighbor : node.neighbors) {
            dfsByPreorder(neighbor, discovered, out);
        }
    }
}
