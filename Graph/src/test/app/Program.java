package test.app;

import test.Graph;

public class Program {
    public static void main(String[] args) {
        {
            Graph graph = new Graph(10);

            for (char i = 0; i < 10; ++i) {
                graph.addVertex(String.format("%c", 'A' + i));
            }

            graph.setEdge("A", 2, "B", "D");
            graph.setEdge("B", 3, "A", "C", "E");
            graph.setEdge("C", 2, "B", "F");
            graph.setEdge("D", 3, "A", "E", "G");
            graph.setEdge("E", 4, "B", "D", "F", "G");
            graph.setEdge("F", 3, "C", "E", "H");
            graph.setEdge("G", 3, "D", "E", "J");
            graph.setEdge("H", 3, "F", "G", "I");
            graph.setEdge("I", 1, "H");
            graph.setEdge("J", 1, "G");

            graph.setMatrixes();

            graph.printDfsPreOrder("J");

            graph.printDfsPostOrder("E");

            graph.printBfs("A");
        }
    }
}
