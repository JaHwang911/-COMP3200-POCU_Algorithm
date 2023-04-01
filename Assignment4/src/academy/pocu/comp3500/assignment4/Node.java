package academy.pocu.comp3500.assignment4;

import java.util.ArrayList;

final class Node {
    private final String title;
    private final ArrayList<Node> neighbors;
    private final int estimate;
    private boolean isLoopNode;
    private int amount;
    private int backEdgeAmount;

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

    public int getRemainingAmount() {
        return this.estimate - this.amount;
    }

    public int getRemainingBackEdgeAmount() {
        return -this.backEdgeAmount;
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

    public void addBackEdgeAmount(final int amount) {
        assert (this.backEdgeAmount + amount <= 0);
        this.backEdgeAmount += amount;
    }

    public void setIsLoop() {
        this.isLoopNode = true;
    }
}
