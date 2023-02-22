package practice;

public class BST {
    private static Node root;

    public BST() { }

    public BST(final int data) {
        root = new Node(data);
    }

    public static void insert(final int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        root = insertRecursive(root, data);
    }

    private static Node insertRecursive(Node node, final int data) {
        if (node == null) {
            return new Node(data);
        }

        if (node.data < data) {
            node.right = insertRecursive(node.right, data);
        } else {
            node.left = insertRecursive(node.left, data);
        }

        return node;
    }

    public static boolean search(final int data) {
        return searchRecursive(root, data);
    }

    private static boolean searchRecursive(Node node, final int data) {
        if (node == null) {
            return false;
        }

        if (node.data == data) {
            return true;
        }

        if (node.data < data) {
            return searchRecursive(node.right, data);
        }

        return searchRecursive(node.left, data);
    }

    public static boolean delete(final int data) {
        boolean[] isDeleteNode = new boolean[] { false };

        root = deleteRecursive(isDeleteNode, root, data);

        return isDeleteNode[0];
    }

    private static Node deleteRecursive(boolean[] isDeleteNode, Node node, final int data) {
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
