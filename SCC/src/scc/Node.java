package scc;

import java.util.ArrayList;

public final class Node {
    private final String title;
    private final ArrayList<Node> neighbors;

    public Node(final String title) {
        this.title = title;
        this.neighbors = new ArrayList<>();
    }

    public Node(final String title, ArrayList<Node> neighbors) {
        this.title = title;
        this.neighbors = new ArrayList<>(neighbors.size());

        for (Node node : neighbors) {
            this.neighbors.add(node);
        }
    }

    public Node(final Node other) {
        this(other.title, other.neighbors);
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof Node) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return this.title.hashCode();
    }
}
