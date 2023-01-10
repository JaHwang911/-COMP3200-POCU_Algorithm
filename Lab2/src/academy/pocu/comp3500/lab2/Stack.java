package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node root;
    private int size;

    public Stack() {
        this.size = 0;
    }

    public void push(final int data) {
        this.root = LinkedList.prepend(this.root, data);
        ++this.size;
    }

    public int peek() {
        assert (this.root != null);
        return this.root.getData();
    }

    public int pop() {
        assert (this.root != null);

        Node popNode = this.root;
        this.root = this.root.getNextOrNull();

        --this.size;

        return popNode.getData();
    }

    public int getSize() {
        return this.size;
    }
}