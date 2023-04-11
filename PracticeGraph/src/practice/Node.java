package practice;

import java.util.ArrayList;

public class Node {
    private final static int DEFAULT_NEIGHBORS_SIZE = 64;
    public String name;
    public ArrayList<Node> neighbors;

    public Node(final String name) {
        this.name = name;
        this.neighbors = new ArrayList<>(DEFAULT_NEIGHBORS_SIZE);
    }

    public void addNeighbor(final Node... nodes) {
        for (Node n : nodes) {
            this.neighbors.add(n);
        }
    }
}
