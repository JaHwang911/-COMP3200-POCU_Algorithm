package practice.app;

import practice.Node;
import practice.ShortestPath;

import java.util.LinkedList;

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

        LinkedList<Node> path = ShortestPath.Run(nA, nG);

        assert (path.get(0).getTitle().equals("A"));
        assert (path.get(1).getTitle().equals("E"));
        assert (path.get(2).getTitle().equals("G"));
    }
}
