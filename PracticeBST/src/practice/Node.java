package practice;

import java.util.ArrayList;

public class Node {
    private final int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public static Node search(int data) {
        return null;
    }

    public static Node insertRecursive(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (node.getData() > data) {
            return insertRecursive(node.getLeft(), data);
        }

        return insertRecursive(node.getRight(), data);
    }

    public static void traverseInOrder(Node root, ArrayList<Integer> out) {
        traverseInOrderRecursive(root, out);
    }

    private static void traverseInOrderRecursive(Node root, ArrayList<Integer> out) {
        if (root == null) {
            return;
        }

        traverseInOrderRecursive(root.getLeft(), out);
        out.add(root.data);
        traverseInOrderRecursive(root.getRight(), out);
    }
}
