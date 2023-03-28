package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.HashMap;
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

        Point[] totalPoints = new Point[points.length + 1];
        HashMap<Point, Integer> pointIndex = new HashMap<>();
        Edge[] edges = new Edge[MAX_EDGES_COUNT];
        int edgesCount = 0;

        totalPoints[0] = startPoint;
        pointIndex.put(totalPoints[0], 0);
        for (int i = 1; i < totalPoints.length; ++i) {
            totalPoints[i] = points[i - 1];
            pointIndex.put(totalPoints[i], i);
        }

        for (int i = 0; i < totalPoints.length - 1; ++i) {
            for (int j = i + 1; j < totalPoints.length; ++j) {
                edges[edgesCount++] = new Edge(totalPoints[i], totalPoints[j]);
            }
        }

        assert (edgesCount == MAX_EDGES_COUNT);

        sortByDistanceRecursive(edges, 0, edges.length - 1);

        int[] cycleArray = new int[totalPoints.length];

        for (int i = 0; i < cycleArray.length; ++i) {
            cycleArray[i] = i;
        }

        ArrayList<Edge> mstEdges = new ArrayList<>(points.length);
        for (Edge edge : edges) {
            int fromIndex = pointIndex.get(edge.getStartLocation());
            int toIndex = pointIndex.get(edge.getEndLocation());

            if (findParent(cycleArray, fromIndex, toIndex)) {
                continue;
            }

            unionParent(cycleArray, fromIndex, toIndex);
            mstEdges.add(edge);
        }

        assert (mstEdges.size() == totalPoints.length - 1);

        ArrayList<Node> mstNodes = new ArrayList<>(points.length);
        HashMap<Node, Integer> discoveredNodes = new HashMap<>();
        int nodeIndex = 0;

        for (Edge edge : mstEdges) {
            Node from = new Node(edge.getStartLocation());
            Node to = new Node(edge.getEndLocation());

            if (!discoveredNodes.containsKey(from) && !discoveredNodes.containsKey(to)) {
                discoveredNodes.put(from, nodeIndex++);
                discoveredNodes.put(to, nodeIndex++);

                mstNodes.add(from);
                mstNodes.add(to);

                from.addNeighbor(to);
                to.addNeighbor(from);

                continue;
            } else if (discoveredNodes.containsKey(from) && discoveredNodes.containsKey(to)) {
                Node fromNode = mstNodes.get(discoveredNodes.get(from));
                Node toNode = mstNodes.get(discoveredNodes.get(to));

                fromNode.addNeighbor(toNode);
                toNode.addNeighbor(fromNode);
                continue;
            }

            if (discoveredNodes.containsKey(from)) {
                Node fromNode = mstNodes.get(discoveredNodes.get(from));
                fromNode.addNeighbor(to);
                to.addNeighbor(fromNode);

                discoveredNodes.put(to, nodeIndex++);
                mstNodes.add(to);
            } else if (discoveredNodes.containsKey(to)) {
                Node toNode = mstNodes.get(discoveredNodes.get(to));
                toNode.addNeighbor(from);
                from.addNeighbor(toNode);

                discoveredNodes.put(from, nodeIndex++);
                mstNodes.add(from);
            }
        }

        assert (mstNodes.size() == totalPoints.length);

        Node startNode = null;
        for (Node n : mstNodes) {
            if (n.getPoint().equals(startPoint)) {
                startNode = n;
                break;
            }
        }

        assert (startNode != null);

        ArrayList<Node> mstDFSList = new ArrayList<>();
        HashMap<Node, Boolean> discovered = new HashMap<>();
        getMSTSearchListRecursive(startNode, discovered, mstDFSList);

        discovered.clear();
        for (Node n : mstDFSList) {
            if (discovered.get(n) == null) {
                discovered.put(n, true);
                result.add(n.getPoint());
            }
        }

        result.add(startPoint);

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

    private static int getParentRecursive(final int[] parent, final int n) {
        if (parent[n] == n) {
            return n;
        }

        parent[n] = getParentRecursive(parent, parent[n]);
        return parent[n];
    }

    private static void unionParent(final int[] parent, int n, int m) {
        n = getParentRecursive(parent, n);
        m = getParentRecursive(parent, m);

        if (n < m) {
            parent[m] = n;
        } else {
            parent[n] = m;
        }
    }

    private static boolean findParent(final int[] parent, int n, int m) {
        n = getParentRecursive(parent, n);
        m = getParentRecursive(parent, m);

        if (n == m) {
            return true;
        }

        return false;
    }

    private static void getMSTSearchListRecursive(Node node, HashMap<Node, Boolean> discoveredNodes, ArrayList<Node> out) {
        if (discoveredNodes.get(node) != null) {
            return;
        }

        discoveredNodes.put(node, true);
        out.add(node);

        for (Node n : node.getNeighbor()) {
            getMSTSearchListRecursive(n, discoveredNodes, out);
        }

        out.add(node);
    }
}