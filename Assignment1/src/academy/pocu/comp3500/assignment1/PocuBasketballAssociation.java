package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        sortByPlayersNameRecursive(gameStats, 0, gameStats.length - 1);

        String playerName = gameStats[0].getPlayerName();
        int numPlayer = 0;
        int totalGoals = 0;
        int totalGoalAttempts = 0;
        int totalPoint = 0;
        int totalAssist = 0;
        int totalPasses = 0;
        int gameCount = 0;

        for (int i = 0; i < gameStats.length; ++i) {
            if (gameStats[i].getPlayerName().compareTo(playerName) != 0) {
                outPlayers[numPlayer].setName(playerName);
                outPlayers[numPlayer].setShootingPercentage(100 * totalGoals / totalGoalAttempts);
                outPlayers[numPlayer].setPointsPerGame(totalPoint / gameCount);
                outPlayers[numPlayer].setAssistsPerGame(totalAssist / gameCount);
                outPlayers[numPlayer].setPassesPerGame(totalPasses / gameCount);

                playerName = gameStats[i].getPlayerName();
                ++numPlayer;
                totalGoals = 0;
                totalGoalAttempts = 0;
                totalPoint = 0;
                totalAssist = 0;
                totalPasses = 0;
                gameCount = 0;
            }

            totalGoals += gameStats[i].getGoals();
            totalGoalAttempts += gameStats[i].getGoalAttempts();
            totalPoint += gameStats[i].getPoints();
            totalAssist += gameStats[i].getAssists();
            totalPasses += gameStats[i].getNumPasses();
            ++gameCount;
        }

        outPlayers[numPlayer].setName(playerName);
        outPlayers[numPlayer].setShootingPercentage(100 * totalGoals / totalGoalAttempts);
        outPlayers[numPlayer].setPointsPerGame(totalPoint / gameCount);
        outPlayers[numPlayer].setAssistsPerGame(totalAssist / gameCount);
        outPlayers[numPlayer].setPassesPerGame(totalPasses / gameCount);
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        assert(players.length > 0);
        assert(targetPoints > 0);

        sortByPointsPerGameRecursive(players, 0, players.length - 1);

        int index = searchTargetPointsPerGame(players, 0, players.length - 1, targetPoints);
        
        return players[index];
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        return null;
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }

    private static void sortByPlayersNameRecursive(final GameStat[] gameStats, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(gameStats, left, right);

        sortByPlayersNameRecursive(gameStats, left, pivotPos - 1);
        sortByPlayersNameRecursive(gameStats, pivotPos + 1, right);
    }

    private static void sortByPointsPerGameRecursive(final Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(players, left, right);

        sortByPointsPerGameRecursive(players, left, pivotPos - 1);
        sortByPointsPerGameRecursive(players, pivotPos + 1, right);
    }

    private static int partition(final GameStat[] gameStats, int left, int right) {
        String pivot = gameStats[right].getPlayerName();

        int i = left;

        for (int j = i; j < right; ++j) {
            if (gameStats[j].getPlayerName().compareTo(pivot) < 0) {
                swapGameStats(gameStats, i, j);
                ++i;
            }
        }

        swapGameStats(gameStats, i, right);

        return i;
    }

    private static int partition(final Player[] players, int left, int right) {
        int pivotValue = players[right].getPointsPerGame();
        int i = left;

        for (int j = i; j < right; ++j) {
            if (players[j].getPointsPerGame() < pivotValue) {
                swapPlayers(players, i, j);

                ++i;
            }
        }

        swapPlayers(players, i, right);

        return i;
    }

    private static void swapGameStats(final GameStat[] gameStats, int i, int j) {
        GameStat tmp = gameStats[i];
        gameStats[i] = gameStats[j];
        gameStats[j] = tmp;
    }

    private static void swapPlayers(final Player[] players, int i, int j) {
        Player tmp = players[i];
        players[i] = players[j];
        players[j] = tmp;
    }

    private static int searchTargetPointsPerGame(Player[] players, int low, int high, int targetPoints) {
        if (low >= high) {
            return low;
        }

        int mid = (low + high) / 2;

        if (players[mid].getPointsPerGame() == targetPoints) {
            return mid;
        } else if (players[mid].getPointsPerGame() < targetPoints) {
            return searchTargetPointsPerGame(players, mid + 1, high, targetPoints);
        } else {
            return searchTargetPointsPerGame(players, low, mid - 1, targetPoints);
        }
    }
}