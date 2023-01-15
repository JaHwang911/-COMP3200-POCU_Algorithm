package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {

    public static void main(String[] args) {
        testFindDreamTeam();
    }

    private static void testFindDreamTeam() {
        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 12, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 1, 11, 54),
                    new Player("Player 4", 10, 3, 51, 88),
                    new Player("Player 7", 16, 8, 5, 77),
                    new Player("Player 1", 1, 15, 2, 22),
                    new Player("Player 3", 7, 5, 8, 66)
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
            assert (maxTeamwork == 219);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 5, 17, 50),
                    new Player("Player 6", 15, 4, 10, 40),
                    new Player("Player 5", 11, 3, 25, 54),
                    new Player("Player 4", 10, 9, 1, 88),
                    new Player("Player 7", 16, 7, 5, 77),
                    new Player("Player 1", 1, 2, 8, 22),
                    new Player("Player 9", 42, 15, 4, 56),
                    new Player("Player 8", 33, 11, 3, 72),
            };

            int k = 4;
            Player[] outPlayers = new Player[4];
            Player[] scratch = new Player[k];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers, scratch);
            assert (maxTeamwork == 171);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 2, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 2, 11, 54),
                    new Player("Player 4", 10, 2, 51, 88),
                    new Player("Player 7", 16, 2, 5, 77),
                    new Player("Player 1", 1, 2, 2, 22),
                    new Player("Player 3", 7, 2, 8, 66)
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

            assert (maxTeamwork == 152);

            Player player = getPlayerOrNull(outPlayers, "Player 4");
            assert (player != null);

            player = getPlayerOrNull(outPlayers, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(outPlayers, "Player 5");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 12, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 1, 11, 54),
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
            assert (maxTeamwork == 30);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", -1, 99999999, 2, -1),
                    new Player("Player 2", -1, 99999999, 1, -1),
                    new Player("Player 3", -1, 100, 0, -1),
                    new Player("Player 4", -1, 2, 102, -1),
                    new Player("Player 5", -1, 1, 296, -1),
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
            assert maxTeamwork == 400;

        }
    }

    private static Player getPlayerOrNull(Player[] players, String name) {
        Player result = null;

        for (int i = 0; i < players.length; ++i) {
            if (players[i].getName().equals(name)) {
                result = players[i];
                break;
            }
        }

        return result;
    }
}
