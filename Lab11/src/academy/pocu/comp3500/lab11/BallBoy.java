package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        final List<Point> result = new ArrayList<>(points.length + 2);
        Point startPoint = new Point(0, 0);

        if (points.length == 0) {
            result.add(startPoint);
            return result;
        }

        final int MAX_EDGES_COUNT = (points.length + 1) * points.length / 2;

        if (MAX_EDGES_COUNT == 1) {
            assert (points.length == 1);
            result.add(startPoint);
            result.add(points[0]);
            result.add(startPoint);

            return result;
        }

        Edge[] edges = new Edge[MAX_EDGES_COUNT];
        int edgesCount = 0;

        for (Point p : points) {
            edges[edgesCount++] = new Edge(startPoint, p);
        }

        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                edges[edgesCount++] = new Edge(points[i], points[j]);
            }
        }

        assert (edgesCount == MAX_EDGES_COUNT);

        sortByDistanceRecursive(edges, 0, edges.length - 1);

        ArrayList<Edge> mstEdges = new ArrayList<>(points.length);
        HashSet<Point> includedPoints = new HashSet<>();

        for (Edge edge : edges) {
            if (!includedPoints.contains(edge.getStartLocation()) || !includedPoints.contains(edge.getEndLocation())) {
                includedPoints.add(edge.getStartLocation());
                includedPoints.add(edge.getEndLocation());

                mstEdges.add(edge);
            }
        }

        assert (mstEdges.size() == points.length);

        HashSet<Node> mstNodes = new HashSet<>(points.length);
        Node startNode = null;

        for (Edge edge : mstEdges) {
            Node from = new Node(edge.getStartLocation());
            Node to = new Node(edge.getEndLocation());

            if (startNode == null) {
                if (from.getPoint().equals(startPoint)) {
                    startNode = from;
                } else if (to.getPoint().equals(startPoint)) {
                    startNode = to;
                }
            }

            from.addNeighbor(to);
            to.addNeighbor(from);

            mstNodes.add(from);
            mstNodes.add(to);
        }

        assert (startNode != null);

        ArrayList<Node> mstDFSList = new ArrayList<>();
        HashSet<Node> discovered = new HashSet<>();
        getMSTSearchListRecursive(startNode, discovered, mstDFSList);

        discovered.clear();
        for (Node n : mstDFSList) {
            if (!discovered.contains(n)) {
                discovered.add(n);
                result.add(n.getPoint());
            }
        }

        return result;
    }

    private static void sortByDistanceRecursive(Edge[] edges, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (edges[j].getDistance() <= edges[right].getDistance()) {
                Edge temp = edges[j];
                edges[j] = edges[i];
                edges[i] = temp;

                ++i;
            }
        }

        Edge temp = edges[i];
        edges[i] = edges[right];
        edges[right] = temp;

        sortByDistanceRecursive(edges, left, i - 1);
        sortByDistanceRecursive(edges, i + 1, right);
    }

    private static void getMSTSearchListRecursive(Node node, HashSet<Node> discovered, ArrayList<Node> out) {
        if (discovered.contains(node)) {
            return;
        }

        discovered.add(node);
        out.add(node);

        for (Node n : node.getNeighbor()) {
            getMSTSearchListRecursive(n, discovered, out);
        }

        out.add(node);
    }
}