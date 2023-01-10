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
        } else if (index < 0) {
            return rootOrNull;
        }

        Node current = rootOrNull;

        for (int i = 1; i < index; ++i) {
            if (current.getNextOrNull() == null) {
                return rootOrNull;
            }

            current = current.getNextOrNull();
        }

        Node newNode = new Node(data);
        newNode.setNext(current.getNextOrNull());
        current.setNext(newNode);

        return rootOrNull;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (rootOrNull == null || index < 0) {
            return null;
        } else if (index == 0) {
            return rootOrNull.getNextOrNull();
        }

        Node current = rootOrNull;

        for (int i = 1; i < index; ++i) {
            if (current.getNextOrNull() == null) {
                return rootOrNull;
            }

            current = current.getNextOrNull();
        }

        if (current.getNextOrNull() == null) {
            return rootOrNull;
        }

        current.setNext(current.getNextOrNull().getNextOrNull());

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        int result = -1;
        int i = 0;
        Node current = rootOrNull;

        while (current != null) {
            if (current.getData() == data) {
                result = i;
                break;
            }

            current = current.getNextOrNull();
            ++i;
        }

        return result;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        Node current = rootOrNull;

        for (int i = 0; i < index; ++i) {
            if (current == null) {
                break;
            }

            current = current.getNextOrNull();
        }

        return current;
    }

    public static Node reverse(final Node rootOrNull) {
        if (rootOrNull == null) {
            return null;
        }

        Node lastNode = rootOrNull;

        while (lastNode.getNextOrNull() != null) {
            lastNode = lastNode.getNextOrNull();
        }

        Node reverseListRoot = null;
        Node current = rootOrNull;

        while (current != lastNode) {
            reverseListRoot = prepend(reverseListRoot, current.getData());
            current = current.getNextOrNull();
        }

        assert(lastNode.getNextOrNull() == null);
        lastNode.setNext(reverseListRoot);

        return lastNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node current0 = root0OrNull;
        Node current1 = root1OrNull;
        Node newNode = null;

        final int MIN_LENGTH = Math.min(getListLength(root0OrNull), getListLength(root1OrNull));

        for (int i = 0; i < MIN_LENGTH; ++i) {
            newNode = append(newNode, current0.getData());
            newNode = append(newNode, current1.getData());

            current0 = current0.getNextOrNull();
            current1 = current1.getNextOrNull();
        }

        assert(current0 == null || current1 == null);

        while (current0 != null) {
            newNode = append(newNode, current0.getData());
            current0 = current0.getNextOrNull();
        }

        while (current1 != null) {
            newNode = append(newNode, current1.getData());
            current1 = current1.getNextOrNull();
        }

        return newNode;
    }

    private static int getListLength(final Node rootOrNull) {
        int i = 0;

        Node current = rootOrNull;

        while (current != null) {
            current = current.getNextOrNull();
            ++i;
        }

        return i;
    }
}