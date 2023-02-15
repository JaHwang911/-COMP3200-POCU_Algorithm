package practice;

import practice.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BST {
    private Node root;
    private int count;

    public BST() { }

    public BST(final int data) {
        this.root = new Node(data);
        this.count = 1;
    }

    public boolean search(final int data) {
        return (searchOrNullRecursive(this.root, data) != null);
    }

    private Node searchOrNullRecursive(final Node root, final int data) {
        if (root == null) {
            return null;
        }

        if (root.getData() == data) {
            return root;
        }

        if (root.getData() < data) {
            return searchOrNullRecursive(root.getRight(), data);
        }

        return searchOrNullRecursive(root.getLeft(), data);
    }

    public void insert(final int data) {
        if (this.root == null) {
            this.root = new Node(data);
            ++this.count;

            return;
        }

        insertRecursive(this.root, data);
        ++this.count;
    }

    private void insertRecursive(final Node root, final int data) {
        if (root.getData() < data) {
            if (root.getRight() == null) {
                Node newNode = new Node(data);
                newNode.setParent(root);

                root.setRight(newNode);
                return;
            }

            insertRecursive(root.getRight(), data);

            return;
        }

        if (root.getLeft() == null) {
            Node newNode = new Node(data);
            newNode.setParent(root);

            root.setLeft(newNode);
            return;
        }

        insertRecursive(root.getLeft(), data);
    }

    public boolean delete(final int data) {
        Node deleteNode = searchOrNullRecursive(this.root, data);

        if (deleteNode == null) {
            return false;
        }

        --this.count;

        if (deleteNode.getLeft() == null && deleteNode.getRight() == null){
            Node parent = deleteNode.getParent();

            if (parent == null) {
                assert (this.root == deleteNode);
                this.root = null;

                return true;
            }

            if (parent.getData() < deleteNode.getData()) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }

            deleteNode.setParent(null);

            return true;
        }

        Node swapNode;

        if (deleteNode.getLeft() == null) {
            swapNode = getSwapNodeRecursive(deleteNode.getRight(), deleteNode);
        } else {
            swapNode = getSwapNodeRecursive(deleteNode.getLeft(), deleteNode);
        }

        swapNode.setParent(deleteNode.getParent());
        swapNode.setLeft(deleteNode.getLeft());
        swapNode.setRight(deleteNode.getRight());

        if (swapNode.getLeft() != null) {
            swapNode.getLeft().setParent(swapNode);
        }

        if (swapNode.getRight() != null) {
            swapNode.getRight().setParent(swapNode);
        }

        Node parent = swapNode.getParent();

        if (parent != null) {
            if (parent.getData() < swapNode.getData()) {
                parent.setRight(swapNode);
            } else {
                parent.setLeft(swapNode);
            }
        } else {
            assert (this.root == deleteNode);
            this.root = swapNode;
        }

        deleteNode.setParent(null);
        deleteNode.setRight(null);
        deleteNode.setRight(null);

        return true;
    }

    private Node getSwapNodeRecursive(final Node swapNode, final Node deleteNode) {
        if (swapNode.getData() < deleteNode.getData()) {
            if (swapNode.getRight() == null) {
                Node parent = swapNode.getParent();

                if (parent.getData() < swapNode.getData()) {
                    parent.setRight(swapNode.getLeft());
                } else {
                    parent.setLeft(swapNode.getLeft());
                }

                if (swapNode.getLeft() != null) {
                    swapNode.getLeft().setParent(parent);
                }

                return swapNode;
            }

            return getSwapNodeRecursive(swapNode.getRight(), deleteNode);
        }

        if (swapNode.getLeft() == null) {
            Node parent = swapNode.getParent();

            if (parent.getData() < swapNode.getData()) {
                parent.setRight(swapNode.getRight());
            } else {
                parent.setLeft(swapNode.getRight());
            }

            if (swapNode.getRight() != null) {
                swapNode.getRight().setParent(parent);
            }

            return swapNode;
        }

        return getSwapNodeRecursive(swapNode.getLeft(), deleteNode);
    }

    public int[] getTop(final int count) {
        final int SIZE;

        if (count > this.count) {
            SIZE = this.count;
        } else {
            SIZE = count;
        }

        int[] result = new int[SIZE];
        ArrayList<Integer> out = new ArrayList<>(SIZE);

        getTopRecursive(this.root, SIZE, out);
        assert (out.size() == SIZE);

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void getTopRecursive(final Node root, final int count, final ArrayList<Integer> out) {
        if (root == null) {
            return;
        }

        getTopRecursive(root.getRight(), count, out);

        if (out.size() < count) {
            out.add(root.getData());
        } else {
            return;
        }

        getTopRecursive(root.getLeft(), count, out);
    }

    public int[] getBottom(final int count) {
        final int SIZE;

        if (count > this.count) {
            SIZE = this.count;
        } else {
            SIZE = count;
        }

        int[] result = new int[SIZE];
        ArrayList<Integer> out = new ArrayList<>(SIZE);

        getBottomRecursive(this.root, SIZE, out);
        assert (out.size() == SIZE);

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void getBottomRecursive(final Node root, final int count, final ArrayList<Integer> out) {
        if (root == null) {
            return;
        }

        getBottomRecursive(root.getLeft(), count, out);

        if (out.size() < count) {
            out.add(root.getData());
        } else {
            return;
        }

        getBottomRecursive(root.getRight(), count, out);
    }

    public int[] traversalInOrder() {
        int[] result = new int[this.count];
        ArrayList<Integer> out = new ArrayList<>(this.count);
        traversalInOrderRecursive(this.root, out);

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void traversalInOrderRecursive(Node node, ArrayList<Integer> out) {
        if (node == null) {
            return;
        }

        traversalInOrderRecursive(node.getLeft(), out);
        out.add(node.getData());
        traversalInOrderRecursive(node.getRight(), out);
    }

    public int[] traversalPreOrder() {
        int[] result = new int[this.count];
        ArrayList<Integer> out = new ArrayList<>(this.count);

        traversalPreOrderRecursive(this.root, out);

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void traversalPreOrderRecursive(Node node, ArrayList<Integer> out) {
        if (node == null) {
            return;
        }

        out.add(node.getData());
        traversalPreOrderRecursive(node.getLeft(), out);
        traversalPreOrderRecursive(node.getRight(), out);
    }
}
