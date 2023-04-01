package academy.pocu.comp3500.assignment4;

final class Edge {
    private final Node startNode;
    private final Node endNode;
    private Edge backEdge;
    private final int capacity;
    private int amount;
    private final boolean isBackEdge;

    public Edge(Node startNode, Node endNode, final int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;
        this.isBackEdge = (capacity == 0);
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Node getEndNode() {
        return this.endNode;
    }

    public Edge getBackEdge() {
        return this.backEdge;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getRemainingAmount() {
        return this.capacity - this.amount;
    }

    public boolean isBackEdge() {
        return this.isBackEdge;
    }

    public void addAmount(final int amount) {
        assert (this.amount + amount <= this.capacity);
        this.amount += amount;
    }

    public void addBackEdge(final Edge backEdge) {
        this.backEdge = backEdge;
    }

    public void initAmount() {
        this.amount = 0;
        this.backEdge.amount = 0;
    }
}
