package practice;

import java.util.ArrayList;

public final class Node {
    private int value;
    private Node right;
    private Node left;

    public Node(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setLeft(final Node node) {
        this.left = node;
    }

    public void setRight(final Node node) {
        this.right = node;
    }
}
