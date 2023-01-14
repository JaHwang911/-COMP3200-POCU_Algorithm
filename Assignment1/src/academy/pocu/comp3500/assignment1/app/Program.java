package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {

    public static void main(String[] args) {
        testFindPoints();
        testFindShooting();
    }

    private static void testFindPoints() {
        {
            Player[] players = new Player[] {
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 5", 11, 12, 6, 77),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12); // player: Player 5
            assert (player.getName().equals("Player 5"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5); // player: Player 2
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13); // player: Player 6
            assert (player.getName().equals("Player 6"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 2, 5, 1, 60),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 5", 11, 12, 6, 77),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12);
            assert (player.getName().equals("Player 5"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13);
            assert (player.getName().equals("Player 6"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 1);
            assert (player.getName().equals("Player 1"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 5, 5, 1, 60)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 1);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 10);
            assert (player.getName().equals("Player 1"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 5, 5, 1, 60),
                    new Player("Player 2", 10, 5, 1, 60)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 10);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 7);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 6);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 9);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 1);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 15);
            assert (player.getName().equals("Player 2"));
        }
    }

    private static void testFindShooting() {
        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70),
                    new Player("Player 5", 11, 12, 6, 77)
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28); // player: Player 2

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58); // player: Player 1
            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72); // player: Player 7
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25)
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 25);
            assert (player.getName().equals("Player 4"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70),
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 71);
            assert (player.getName().equals("Player 7"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 20);
            assert (player.getName().equals("Player 4"));
        }
    }

    private static void temp() {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 12, 14, 50),
                new Player("Player 6", 15, 2, 5, 40),
                new Player("Player 5", 11, 1, 11, 54),
                new Player("Player 4", 10, 3, 51, 88),
                new Player("Player 7", 16, 8, 5, 77),
                new Player("Player 1", 1, 15, 2, 22),
                new Player("Player 3", 7, 5, 8, 66)
        };

        PocuBasketballAssociation.sortByAssistRecursive(players, 0, players.length - 1);

        Player[] players2 = new Player[] {
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 9", 42, 15, 4, 56),
                new Player("Player 8", 33, 11, 3, 72),
        };

        PocuBasketballAssociation.sortByAssistRecursive(players2, 0, players2.length - 1);

        Player[] players3 = new Player[] {
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

        PocuBasketballAssociation.sortByAssistRecursive(players3, 0, players3.length - 1);
    }

    private static void TestFind3ManDreamTeam() {
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

        long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
    }
}
