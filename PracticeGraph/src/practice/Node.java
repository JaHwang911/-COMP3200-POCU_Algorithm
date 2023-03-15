package practice;

import java.util.ArrayList;

public class Node {
    private final static int DEFAULT_NEIGHBORS_SIZE = 64;
    public int data;
    public ArrayList<Node> neighbors;

    public Node(int data) {
        this.data = data;
        this.neighbors = new ArrayList<>(DEFAULT_NEIGHBORS_SIZE);
    }
}
