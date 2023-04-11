package practice;

import java.util.ArrayList;

public final class Node {
    private final String title;
    private final ArrayList<Node> neighbors;

    public Node(final String title) {
        this.title = title;
        this.neighbors = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<Node> getNeighbors() {
        return this.neighbors;
    }

    public void addNeighbor(final Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public void addNeighbor(final Node... nodes) {
        for (Node n : nodes) {
            addNeighbor(n);
        }
    }
}
