package practice;

import java.util.Stack;

public final class BST {
    private Node root;

    public void insert(final int value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        insertRecursive(this.root, value);
    }

    public Node insertRecursive(final Node node, final int value) {
        if (node == null) {
            return new Node(value);
        }

        if (node.getValue() < value) {
            node.setRight(insertRecursive(node.getRight(), value));
        } else {
            node.setLeft(insertRecursive(node.getLeft(), value));
        }

        return node;
    }

    public boolean searchDepthFirst(final int value) {
        Stack<Node> stacks = new Stack<>();

        stacks.push(this.root);

        while (!stacks.empty()) {
            Node node = stacks.pop();

            if (node == null) {
                continue;
            } else if (node.getValue() == value) {
                return true;
            }

            stacks.push(node.getRight());
            stacks.push(node.getLeft());
        }

        return false;
    }
}
