package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {
    private LinkedList() { }

    public static Node append(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        }

        Node current = rootOrNull;

        while (current.getNextOrNull() != null) {
            current = current.getNextOrNull();
        }

        current.setNext(new Node(data));

        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        }

        Node newNode = new Node(data);
        newNode.setNext(rootOrNull);

        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        } else if (index == 0) {
            Node newNode = new Node(data);
            newNode.setNext(rootOrNull);
            return newNode;
        }

        return null;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        return null;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        return null;
    }

    public static Node reverse(final Node rootOrNull) {
        return null;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        return null;
    }
}