package academy.pocu.comp3500.lab11.data;

public final class Point {
    private final int x;
    private final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Point) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Point other = (Point) obj;

        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return this.x ^ (this.y << 16);
    }
}
