package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
	    Node root = LinkedList.append(null, 10);

        root = LinkedList.prepend(root, 11);
        root = LinkedList.prepend(root, 12);
    }
}
