package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {

    public static void main(String[] args) {
        TestProcessGameStats();
        TestFindPlayerPointsPerGame();
        TestFindPlayerShootingPercentage();
    }

    private static void TestProcessGameStats() {
        GameStat[] gameStats = new GameStat[] {
                new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
        };

        Player[] players = new Player[] {
                new Player(),
                new Player(),
                new Player(),
                new Player()
        };

        PocuBasketballAssociation.processGameStats(gameStats, players);

        for (int i = 0;  i < players.length; ++i) {
            String pName = players[i].getName();

            switch (pName) {
                case "Player 1":
                    assert(players[i].getPointsPerGame() == 11);
                    assert(players[i].getAssistsPerGame() == 8);
                    assert(players[i].getPassesPerGame() == 3);
                    assert(players[i].getShootingPercentage() == 55);
                    break;
                case "Player 2":
                    assert(players[i].getPointsPerGame() == 5);
                    assert(players[i].getAssistsPerGame() == 3);
                    assert(players[i].getPassesPerGame() == 8);
                    assert(players[i].getShootingPercentage() == 50);
                    break;
                case "Player 3":
                    assert(players[i].getPointsPerGame() == 31);
                    assert(players[i].getAssistsPerGame() == 4);
                    assert(players[i].getPassesPerGame() == 2);
                    assert(players[i].getShootingPercentage() == 32);
                    break;
                case "Player 4":
                    assert(players[i].getPointsPerGame() == 44);
                    assert(players[i].getAssistsPerGame() == 1);
                    assert(players[i].getPassesPerGame() == 1);
                    assert(players[i].getShootingPercentage() == 48);
                    break;
                default:
                    assert(false);
                    break;
            }
        }
    }

    private static void TestFindPlayerPointsPerGame() {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 2, 11, 31),
                new Player("Player 5", 11, 12, 6, 77),
                new Player("Player 3", 7, 4, 7, 44),
                new Player("Player 1", 1, 5, 1, 60),
                new Player("Player 4", 10, 10, 15, 25),
                new Player("Player 7", 16, 8, 2, 70),
                new Player("Player 6", 15, 0, 12, 61),
        };

        Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12); // player: Player 5
        assert(player.getName().equals("Player 5"));

        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5); // player: Player 2
        assert(player.getName().equals("Player 2"));
        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13); // player: Player 6
        assert(player.getName().equals("Player 6"));
    }

    private static void TestFindPlayerShootingPercentage() {
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
        assert(player.getName().equals("Player 2"));

        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58); // player: Player 1
        assert(player.getName().equals("Player 1"));
        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72); // player: Player 7
        assert(player.getName().equals("Player 7"));
    }
}
