package academy.pocu.comp3500.assignment4;

final class Edge {
    private final Node startNode;
    private final Node endNode;
    private final int capacity;
    private int amount;

    public Edge(Node startNode, Node endNode, final int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Node getEndNode() {
        return this.endNode;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getRemainingAmount() {
        return this.capacity - this.amount;
    }

    public void addAmount(final int amount) {
        assert (this.amount + amount <= this.capacity);
        this.amount += amount;
    }

    public void subAmount(final int amount) {
        this.amount -= amount;
    }

    public void initAmount() {
        this.amount = 0;
    }
}
