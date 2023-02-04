package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public class Node {
    private String data;
    private Node prev;
    private ArrayList<Node> next;

    public Node(String data, Node prev) {
        this.data = data;
        this.prev = prev;
        this.next = new ArrayList<>(32);
    }

    public String getData() {
        return this.data;
    }

    public Node getPrev() {
        return this.prev;
    }

    public ArrayList<Node> getNext() {
        return this.next;
    }
}
