package practice;

public final class Edge {
    private final String n1;
    private final String n2;
    private final int weight;

    public Edge(String n1, String n2, final int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    public String getNode1() {
        return this.n1;
    }

    public String getNode2() {
        return this.n2;
    }

    public int getWeight() {
        return this.weight;
    }
}
