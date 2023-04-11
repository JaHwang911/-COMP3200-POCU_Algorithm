package practice.app;

import practice.DFS;
import practice.Node;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        Node nA = new Node("A");
        Node nB = new Node("B");
        Node nC = new Node("C");
        Node nD = new Node("D");
        Node nE = new Node("E");
        Node nF = new Node("F");
        Node nG = new Node("G");

        nA.addNeighbor(nB, nC, nE);
        nB.addNeighbor(nA, nD);
        nC.addNeighbor(nA, nF);
        nD.addNeighbor(nB, nG);
        nE.addNeighbor(nA, nG);
        nF.addNeighbor(nC, nG);
        nG.addNeighbor(nD, nE, nF);

        ArrayList<Node> postorder = DFS.depthFirstSearchByPostorder(nA);
        for (Node n : postorder) {
            System.out.printf("%s -> ", n.name);
        }

        System.out.println();

        ArrayList<Node> preorder = DFS.depthFirstSearchByPreorder(nA);
        for (Node n : preorder) {
            System.out.printf("%s -> ", n.name);
        }
        System.out.println();
    }
}
