package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {

    public static void main(String[] args) {
        testFindDreamTeamSize();
    }

    private static void testFindDreamTeamSize() {
        Player[] players = new Player[] {
                new Player("Player 1", 2, 5, 10, 78),
                new Player("Player 2", 10, 4, 5, 66),
                new Player("Player 3", 3, 3, 2, 22),
                new Player("Player 4", 1, 9, 8, 12),
                new Player("Player 5", 11, 1, 12, 26),
                new Player("Player 6", 7, 2, 10, 15),
                new Player("Player 7", 8, 15, 3, 11),
                new Player("Player 8", 5, 7, 13, 5),
                new Player("Player 9", 8, 2, 7, 67),
                new Player("Player 10", 1, 11, 1, 29),
                new Player("Player 11", 2, 6, 9, 88)
        };

        Player[] scratch = new Player[players.length];

        int k = PocuBasketballAssociation.findDreamTeamSize(players, scratch);
        assert (k == 6);
    }
}
