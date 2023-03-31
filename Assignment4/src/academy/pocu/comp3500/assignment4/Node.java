package academy.pocu.comp3500.assignment4;

import java.util.ArrayList;

public final class Node {
    private final String title;
    private final ArrayList<Node> neighbors;
    private final int estimate;
    private boolean isLoopNode;
    private int amount;

    public Node(String title, int estimate) {
        this.title = title;
        this.neighbors = new ArrayList<>(64);
        this.estimate = estimate;
    }

    public String getTitle() {
        return this.title;
    }

    public int getEstimate() {
        return this.estimate;
    }

    public ArrayList<Node> getNeighbors() {
        return this.neighbors;
    }

    public int getNeighborsSize() {
        return this.neighbors.size();
    }

    public int getAmount() {
        return this.amount;
    }

    public int getRemainAmount() {
        return this.estimate - this.amount;
    }

    public void addNeighbor(final Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public boolean isLoopNode() {
        return this.isLoopNode;
    }

    public void addAmount(final int amount) {
        assert (this.amount + amount <= this.estimate);
        this.amount += amount;
    }

    public void setIsLoop() {
        this.isLoopNode = true;
    }
}
