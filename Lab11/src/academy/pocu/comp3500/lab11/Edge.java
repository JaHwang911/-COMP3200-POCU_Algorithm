package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

public final class Edge {
    private final Point startLocation;
    private final Point endLocation;
    private final int distance;

    public Edge(Point startLocation, Point endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;

        final int BASE = Math.abs(this.startLocation.getX() - this.endLocation.getX());
        final int HEIGHT = Math.abs(this.startLocation.getY() - this.endLocation.getY());

        if (BASE == 0 || HEIGHT == 0) {
            this.distance = BASE + HEIGHT;
            return;
        }

        this.distance = BASE * BASE + HEIGHT * HEIGHT;
    }

    public Point getStartLocation() {
        return this.startLocation;
    }

    public Point getEndLocation() {
        return this.endLocation;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Edge) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Edge other = (Edge) obj;

        return this.startLocation.equals(other.startLocation) || this.startLocation.equals(other.endLocation)
                && this.endLocation.equals(other.startLocation) || this.endLocation.equals(other.startLocation);
    }

    @Override
    public int hashCode() {
        return (this.startLocation.getX() + this.startLocation.getY() + this.endLocation.getX() + this.endLocation.getY());
    }
}
