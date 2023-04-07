package scc.app;

import scc.Node;
import scc.SCC;

public class Program {
    public static void main(String[] args) {
        testPrintSCC();
    }

    private static void testPrintSCC() {
        Node nA = new Node("A");
        Node nB = new Node("B");
        Node nC = new Node("C");
        Node nD = new Node("D");
        Node nE = new Node("E");
        Node nF = new Node("F");
        Node nG = new Node("G");
        Node nH = new Node("H");
        Node nI = new Node("I");
        Node nJ = new Node("J");
        Node nK = new Node("K");

        nA.addNeighbor(nB, nC);
        nB.addNeighbor(nD, nF);
        nC.addNeighbor(nA, nB, nE, nJ);
        nD.addNeighbor(nH);
        nE.addNeighbor(nC, nD, nJ);
        nF.addNeighbor(nG);
        nG.addNeighbor(nD);
        nH.addNeighbor(nK, nI);
        nI.addNeighbor(nG);
        nJ.addNeighbor(nK);
        nK.addNeighbor(nI, nJ);

        Node[] nodes = new Node[] {
                nA,
                nB,
                nC,
                nD,
                nE,
                nF,
                nG,
                nH,
                nI,
                nJ,
                nK,
        };

        SCC.printSCCCountAndList(nodes);
    }
}
