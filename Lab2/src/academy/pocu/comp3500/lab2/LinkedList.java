package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {
    private LinkedList() {

    }

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
            if (index == 0) {
                return new Node(data);
            }

            return null;
        } else if (index < 0) {
            return rootOrNull;
        } else if (index == 0) {
            Node newNode = new Node(data);
            newNode.setNext(rootOrNull);
            return newNode;
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
        if (index < 0) {
            return null;
        }

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
        Node prev = null;
        Node current = rootOrNull;

        while (current != null) {
            Node next = current.getNextOrNull();
            current.setNext(prev);

            prev = current;
            current = next;
        }

        return prev;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        if (root0OrNull == null) {
            return root1OrNull;
        } else if (root1OrNull == null) {
            return root0OrNull;
        }

        Node mainRoot = root0OrNull;
        Node next0 = root0OrNull.getNextOrNull();
        Node next1 = root1OrNull;

        while (next0 != null && next1 != null) {
            mainRoot.setNext(next1);
            next1 = next1.getNextOrNull();

            mainRoot = mainRoot.getNextOrNull();

            mainRoot.setNext(next0);
            next0 = next0.getNextOrNull();

            mainRoot = mainRoot.getNextOrNull();
        }

        if (next0 == null && next1 != null) {
            mainRoot.setNext(next1);
        }

        return root0OrNull;
    }

    private static int getNodeLength(final Node root) {
        int result = 0;
        Node current = root;

        while (current != null) {
            current = current.getNextOrNull();
            ++result;
        }

        return result;
    }
}