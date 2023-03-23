package academy.pocu.comp3500.lab10;

import java.util.ArrayList;

public class Node {
    private final String title;
    private final ArrayList<Node> neighbors;

    public Node(String title) {
        this.title = title;
        this.neighbors = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void addNeighbor(final Node neighbor) {
        assert (!this.neighbors.contains(neighbor));
        this.neighbors.add(neighbor);
    }

    public ArrayList<Node> getNeighbor() {
        return this.neighbors;
    }
}
