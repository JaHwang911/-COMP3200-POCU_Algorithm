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
        assert (players.length > 0);
        assert (targetPoints > 0);

        int index = indexOfTargetPointRecursive(players, 0, players.length - 1, targetPoints);
        return players[index];
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        assert (players.length > 0);
        assert (targetShootingPercentage > 0);

        int index = indexOfTargetShootingRecursive(players, 0, players.length - 1, targetShootingPercentage);
        return players[index];
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

        String pivot = gameStats[right].getPlayerName();
        int i = left;

        for (int j = i; j < right; ++j) {
            if (gameStats[j].getPlayerName().compareTo(pivot) < 0) {
                GameStat tmp = gameStats[i];
                gameStats[i] = gameStats[j];
                gameStats[j] = tmp;

                ++i;
            }
        }

        GameStat tmp = gameStats[i];
        gameStats[i] = gameStats[right];
        gameStats[right] = tmp;

        int pivotPos = i;

        sortByPlayersNameRecursive(gameStats, left, pivotPos - 1);
        sortByPlayersNameRecursive(gameStats, pivotPos + 1, right);
    }

    public static void sortByAssistRecursive(Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;

        for (int j = i; j < right; ++j) {
            if (players[j].getAssistsPerGame() < players[right].getAssistsPerGame()) {
                Player tmp = players[j];
                players[j] = players[i];
                players[i] = tmp;

                ++i;
            }
        }

        Player tmp = players[i];
        players[i] = players[right];
        players[right] = tmp;

        int pivotPos = i;

        sortByAssistRecursive(players, left, pivotPos - 1);
        sortByAssistRecursive(players, pivotPos + 1, right);
    }

    private static int indexOfTargetPointRecursive(Player[] players, int start, int end, int targetValue) {
        if (start >= end) {
            if (players.length == 1) {
                return start;
            }

            int index = start;

            if (index - 1 < 0) {
                int currDiff = Math.abs(targetValue - players[index].getPointsPerGame());
                int nextDiff = Math.abs(targetValue - players[index + 1].getPointsPerGame());

                index = currDiff < nextDiff ? index : index + 1;
            } else if (index + 1 >= players.length) {
                int prevDiff = Math.abs(targetValue - players[index - 1].getPointsPerGame());
                int currDiff = Math.abs(targetValue - players[index].getPointsPerGame());

                index = prevDiff < currDiff ? index - 1 : index;
            } else {
                int prevDiff = Math.abs(targetValue - players[index - 1].getPointsPerGame());
                int currDiff = Math.abs(targetValue - players[index].getPointsPerGame());
                int nextDiff = Math.abs(targetValue - players[index + 1].getPointsPerGame());

                if (prevDiff < currDiff) {
                    index = index - 1;
                } else if (nextDiff <= currDiff) {
                    index = index + 1;
                }
            }

            return index;
        }

        int mid = (start + end) / 2;

        if (players[mid].getPointsPerGame() == targetValue) {
            return mid;
        }

        if (players[mid].getPointsPerGame() > targetValue) {
            return indexOfTargetPointRecursive(players, start, mid - 1, targetValue);
        } else {
            return indexOfTargetPointRecursive(players, mid + 1, end, targetValue);
        }
    }

    private static int indexOfTargetShootingRecursive(Player[] players, int start, int end, int targetValue) {
        if (start >= end) {
            if (players.length == 1) {
                return start;
            }

            int index = start;

            if (index - 1 < 0) {
                int currDiff = Math.abs(targetValue - players[index].getShootingPercentage());
                int nextDiff = Math.abs(targetValue - players[index + 1].getShootingPercentage());

                index = currDiff < nextDiff ? index : index + 1;
            } else if (index + 1 >= players.length) {
                int prevDiff = Math.abs(targetValue - players[index - 1].getShootingPercentage());
                int currDiff = Math.abs(targetValue - players[index].getShootingPercentage());

                index = prevDiff < currDiff ? index - 1 : index;
            } else {
                int prevDiff = Math.abs(targetValue - players[index - 1].getShootingPercentage());
                int currDiff = Math.abs(targetValue - players[index].getShootingPercentage());
                int nextDiff = Math.abs(targetValue - players[index + 1].getShootingPercentage());

                if (prevDiff < currDiff) {
                    index = index - 1;
                } else if (nextDiff <= currDiff) {
                    index = index + 1;
                }
            }

            return index;
        }

        int mid = (start + end) / 2;

        if (players[mid].getShootingPercentage() == targetValue) {
            return mid;
        }

        if (players[mid].getShootingPercentage() > targetValue) {
            return indexOfTargetShootingRecursive(players, start, mid - 1, targetValue);
        } else {
            return indexOfTargetShootingRecursive(players, mid + 1, end, targetValue);
        }
    }
}