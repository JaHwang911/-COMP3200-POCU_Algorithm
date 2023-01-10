package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node root;
    private int size;

    public Stack() {
        this.size = 0;
    }

    public void push(final int data) {
        this.root = LinkedList.append(this.root, data);
        ++this.size;
    }

    public int peek() {
        assert (this.root != null);

        Node topNode = LinkedList.getOrNull(this.root, this.size - 1);
        assert (topNode != null);

        return topNode.getData();
    }

    public int pop() {
        assert (this.root != null);

        Node lastNode = LinkedList.getOrNull(this.root, this.size - 1);
        this.root = LinkedList.removeAt(this.root, this.size - 1);

        --this.size;

        return lastNode.getData();
    }

    public int getSize() {
        return this.size;
    }
}