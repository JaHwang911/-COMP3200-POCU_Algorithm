package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;

public final class Node {
    private final Point point;
    private final ArrayList<Node> neighbor;

    public Node(final Point point) {
        this.point = point;
        this.neighbor = new ArrayList<>();
    }

    public Point getPoint() {
        return this.point;
    }

    public ArrayList<Node> getNeighbor() {
        return this.neighbor;
    }

    public void addNeighbor(final Node point) {
        this.neighbor.add(point);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return obj instanceof Node && this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.point.hashCode();
    }
}
