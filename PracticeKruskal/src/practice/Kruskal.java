package practice;

import java.util.ArrayList;

public class Kruskal {
    public static ArrayList<Edge> run(String[] nodes, Edge[] edges) {
        DisjointSet disjointSet = new DisjointSet(nodes);
        ArrayList<Edge> ret = new ArrayList<>();

        sortByWeight(edges, 0, edges.length - 1);

        for (Edge e : edges) {
            String n1 = e.getNode1();
            String n2 = e.getNode2();

            String p1 = disjointSet.find(n1);
            String p2 = disjointSet.find(n2);

            if (p1.equals(p2)) {
                continue;
            }

            disjointSet.union(p1, p2);
            ret.add(e);
        }

        return ret;
    }

    private static void sortByWeight(Edge[] edges, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (edges[j].getWeight() < edges[right].getWeight()) {
                Edge temp = edges[j];
                edges[j] = edges[i];
                edges[i] = temp;

                ++i;
            }
        }

        Edge temp = edges[i];
        edges[i] = edges[right];
        edges[right] = temp;

        sortByWeight(edges, left, i - 1);
        sortByWeight(edges, i + 1, right);
    }
}
