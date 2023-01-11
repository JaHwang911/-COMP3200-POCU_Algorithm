package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node front;
    private Node back;
    private int size;

    public Queue() {
        this.size = 0;
    }

    public void enqueue(final int data) {
        Node newNode = new Node(data);

        if (this.size == 0) {
            this.front = newNode;
            this.back = newNode;
        } else {
            this.back.setNext(newNode);
            this.back = this.back.getNextOrNull();
        }

        ++this.size;
    }

    public int peek() {
        assert (this.front != null);
        assert (this.back != null);

        return this.front.getData();
    }

    public int dequeue() {
        assert (this.front != null);
        assert (this.back != null);

        int result = this.front.getData();
        this.front = this.front.getNextOrNull();

        --this.size;

        return result;
    }

    public int getSize() {
        return this.size;
    }
}