package academy.pocu.comp3500.assignment4;

import java.util.ArrayList;

public final class Node {
    private final String title;
    private final ArrayList<Node> neighbors;
    private final int estimate;

    public Node(String title, int estimate) {
        this.title = title;
        this.neighbors = new ArrayList<>(64);
        this.estimate = estimate;
    }

    public String getTitle() {
        return this.title;
    }

    public void addNeighbor(final Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public int getEstimate() {
        return this.estimate;
    }

    public ArrayList<Node> getNeighbors() {
        return this.neighbors;
    }
}
