package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.HashSet;

public final class Node {
    private final Point point;
    private final HashSet<Node> neighbor;

    public Node(final Point point) {
        this.point = point;
        this.neighbor = new HashSet<>();
    }

    public Point getPoint() {
        return this.point;
    }

    public HashSet<Node> getNeighbor() {
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
