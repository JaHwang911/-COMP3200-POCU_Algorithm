package practice;

import java.util.ArrayList;

public class Node {
    private final int data;
    private Node parent;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node leftOrNull) {
        this.left = leftOrNull;
    }

    public void setRight(Node rightOrNull) {
        this.right = rightOrNull;
    }
}
