package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node root;
    private int size;

    public Queue() {
        this.size = 0;
    }

    public void enqueue(final int data) {
        this.root = LinkedList.append(this.root, data);
        ++this.size;
    }

    public int peek() {
        assert(this.root != null);
        return this.root.getData();
    }

    public int dequeue() {
        assert(this.root != null);

        int result = this.root.getData();
        this.root = this.root.getNextOrNull();

        --this.size;

        return result;
    }

    public int getSize() {
        return this.size;
    }
}