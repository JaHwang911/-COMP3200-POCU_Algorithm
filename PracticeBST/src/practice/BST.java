package practice;

import java.util.ArrayList;

public class BST {
    private static Node root;
    private static int count;

    public BST() { }

    public BST(final int data) {
        root = new Node(data);
        count = 1;
    }

    public void insert(final int data) {
        if (root == null) {
            root = new Node(data);
            ++count;

            return;
        }

        insertRecursive(root, data);
        ++count;
    }

    private void insertRecursive(final Node root, final int data) {
        if (root.data < data) {
            if (root.right == null) {
                root.right = new Node(data);
                return;
            }

            insertRecursive(root.right, data);
            return;
        }

        if (root.left == null) {
            root.left = new Node(data);
            return;
        }

        insertRecursive(root.left, data);
    }

    public static boolean search(final int data) {
        return searchOrNullRecursive(root, data) != null;
    }

    private static Node searchOrNullRecursive(Node root, final int data) {
        if (root == null) {
            return null;
        }

        if (root.data ==  data) {
            return root;
        }

        if (root.data < data) {
            return searchOrNullRecursive(root.right, data);
        }

        return searchOrNullRecursive(root.left, data);
    }

    public static boolean delete(final int data) {
        boolean[] isDeleteNode = new boolean[] {false};

        root = deleteRecursive(isDeleteNode, root, data);

        return isDeleteNode[0];
    }

    private static Node deleteRecursive(boolean[] isDeleteNode, Node node, final int data) {
        if (node == null) {
            return null;
        }

        if (node.data < data) {
            node.right = deleteRecursive(isDeleteNode, node.right, data);
        } else if (node.data > data) {
            node.left = deleteRecursive(isDeleteNode, node.left, data);
        } else {
            isDeleteNode[0] = true;

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node swapNode = node.right;

            while (swapNode.left != null) {
                swapNode = swapNode.left;
            }

            node.data = swapNode.data;
            node.right = deleteRecursive(isDeleteNode, node.right, swapNode.data);
        }

        return node;
    }
}
