package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
	    testFindMatch();
        testGetTop();
        testGetBottom();
        testJoin();
        testLeave();

        findMatchTest();
        joinTest();
        leaveTest();

        System.out.println("No prob lab 6");
    }

    private static void testFindMatch() {
        {
            Player player1 = new Player(1, "player1", 9);
            Player player2 = new Player(2, "player2", 12);
            Player player3 = new Player(3, "player3", 17);
            Player player4 = new Player(4, "player4", 14);

            League notLeague = new League();
            Player notLeagueResult = notLeague.findMatchOrNull(player3);
            assert (notLeagueResult == null);

            League league1 = new League(new Player[]{player1, player2, player3, player4});

            Player result = league1.findMatchOrNull(player3);
            assert (result == player4);

            result = league1.findMatchOrNull(player4);
            assert (result == player2);

            result = league1.findMatchOrNull(player1);
            assert (result == player2);
        }

        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(12);
            Player player4 = new Player(5);
            Player player5 = new Player(8);
            Player player6 = new Player(10);
            Player player7 = new Player(17);
            Player player8 = new Player(3);
            Player player9 = new Player(11);
            Player player10 = new Player(14);
            Player player11 = new Player(20);
            Player player12 = new Player(2);
            Player player13 = new Player(4);
            Player player14 = new Player(1);

            League league = new League(new Player[]{
                    player1, player2, player3, player4, player5, player6, player7,
                    player8, player9, player10, player11, player12, player13, player14});

            Player result = league.findMatchOrNull(player13);
            assert (result == player4);

            result = league.findMatchOrNull(player9);
            assert (result == player3);

            result = league.findMatchOrNull(player1);
            assert (result == player5);

            result = league.findMatchOrNull(player14);
            assert (result == player12);

            result = league.findMatchOrNull(player11);
            assert (result == player7);
        }

        {
            Player player1 = new Player(9);
            Player player2 = new Player(12);
            Player player3 = new Player(10);
            Player player4 = new Player(17);
            Player player5 = new Player(11);
            Player player6 = new Player(14);
            Player player7 = new Player(20);

            League league = new League(new Player[]{player1, player2, player3, player4, player5, player6, player7});

            Player result = league.findMatchOrNull(player1);
            assert (result == player3);
        }

        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(5);
            Player player4 = new Player(8);
            Player player5 = new Player(3);
            Player player6 = new Player(2);
            Player player7 = new Player(4);

            League league = new League(new Player[]{player1, player2, player3, player4, player5, player6, player7});

            Player result = league.findMatchOrNull(player1);
            assert (result == player4);
        }
    }

    private static void testGetTop() {
        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(12);
            Player player4 = new Player(5);
            Player player5 = new Player(8);
            Player player6 = new Player(10);
            Player player7 = new Player(17);
            Player player8 = new Player(3);
            Player player9 = new Player(11);
            Player player10 = new Player(14);
            Player player11 = new Player(20);
            Player player12 = new Player(2);
            Player player13 = new Player(4);
            Player player14 = new Player(1);

            League league = new League(new Player[]{
                    player1, player2, player3, player4, player5, player6, player7,
                    player8, player9, player10, player11, player12, player13, player14});

            Player[] result = league.getTop(1);
            assert (result[0] == player11);

            result = league.getTop(2);
            assert (result[0] == player11);
            assert (result[1] == player7);

            result = league.getTop(3);
            assert (result[0] == player11);
            assert (result[1] == player7);
            assert (result[2] == player10);

            result = league.getTop(4);
            assert (result[0] == player11);
            assert (result[1] == player7);
            assert (result[2] == player10);
            assert (result[3] == player3);
        }

        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(5);
            Player player4 = new Player(8);
            Player player5 = new Player(3);
            Player player6 = new Player(2);
            Player player7 = new Player(4);

            League league = new League(new Player[]{player1, player2, player3, player4, player5, player6, player7});

            Player[] result = league.getTop(1);
            assert (result[0] == player1);

            result = league.getTop(2);
            assert (result[0] == player1);
            assert (result[1] == player4);

            result = league.getTop(3);
            assert (result[0] == player1);
            assert (result[1] == player4);
            assert (result[2] == player2);
        }
    }

    private static void testGetBottom() {
        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(12);
            Player player4 = new Player(5);
            Player player5 = new Player(8);
            Player player6 = new Player(10);
            Player player7 = new Player(17);
            Player player8 = new Player(3);
            Player player9 = new Player(11);
            Player player10 = new Player(14);
            Player player11 = new Player(20);
            Player player12 = new Player(2);
            Player player13 = new Player(4);
            Player player14 = new Player(1);

            League league = new League(new Player[]{
                    player1, player2, player3, player4, player5, player6, player7,
                    player8, player9, player10, player11, player12, player13, player14});

            Player[] result = league.getBottom(1);
            assert (result[0] == player14);

            result = league.getBottom(2);
            assert (result[0] == player14);
            assert (result[1] == player12);

            result = league.getBottom(3);
            assert (result[0] == player14);
            assert (result[1] == player12);
            assert (result[2] == player8);

            result = league.getBottom(4);
            assert (result[0] == player14);
            assert (result[1] == player12);
            assert (result[2] == player8);
            assert (result[3] == player13);
        }

        {
            Player player1 = new Player(9);
            Player player2 = new Player(12);
            Player player3 = new Player(10);
            Player player4 = new Player(17);
            Player player5 = new Player(11);
            Player player6 = new Player(14);
            Player player7 = new Player(20);

            League league = new League(new Player[]{player1, player2, player3, player4, player5, player6, player7});

            Player[] result = league.getBottom(1);
            assert (result[0] == player1);

            result = league.getBottom(2);
            assert (result[0] == player1);
            assert (result[1] == player3);

            result = league.getBottom(3);
            assert (result[0] == player1);
            assert (result[1] == player3);
            assert (result[2] == player5);

            result = league.getBottom(4);
            assert (result[0] == player1);
            assert (result[1] == player3);
            assert (result[2] == player5);
            assert (result[3] == player2);
        }
    }

    private static void testJoin() {
        {
            Player player1 = new Player(9);
            Player player2 = new Player(12);
            Player player3 = new Player(17);
            Player player4 = new Player(14);

            Player player5 = new Player(7);
            Player player6 = new Player(5);
            Player player7 = new Player(8);

            League league = new League(new Player[]{player1, player2, player3, player4});

            assert (league.checkPlayerJoined(player1));
            assert (league.checkPlayerJoined(player2));
            assert (league.checkPlayerJoined(player3));
            assert (league.checkPlayerJoined(player4));

            assert (!league.checkPlayerJoined(player5));
            assert (!league.checkPlayerJoined(player6));
            assert (!league.checkPlayerJoined(player7));

            assert (!league.join(player1));
            assert (!league.join(player2));
            assert (!league.join(player3));
            assert (!league.join(player4));

            assert (league.join(player5));
            assert (league.join(player6));
            assert (league.join(player7));

            assert (league.checkPlayerJoined(player5));
            assert (league.checkPlayerJoined(player6));
            assert (league.checkPlayerJoined(player7));

            league.printAllMember();
        }
    }

    private static void testLeave() {
        {
            Player player1 = new Player(9);
            Player player2 = new Player(7);
            Player player3 = new Player(12);
            Player player4 = new Player(5);
            Player player5 = new Player(8);
            Player player6 = new Player(10);
            Player player7 = new Player(17);
            Player player8 = new Player(3);
            Player player9 = new Player(11);
            Player player10 = new Player(14);
            Player player11 = new Player(20);
            Player player12 = new Player(2);
            Player player13 = new Player(4);
            Player player14 = new Player(1);

            Player player15 = new Player(-2);

            League league = new League(new Player[]{
                    player1, player2, player3, player4, player5, player6, player7,
                    player8, player9, player10, player11, player12, player13, player14});

            assert (!league.leave(player15));
            assert (!league.checkPlayerJoined(player15));

            assert (league.leave(player14));
            assert (!league.checkPlayerJoined(player14));

            assert (league.leave(player11));
            assert (!league.checkPlayerJoined(player11));

            assert (league.leave(player2));
            assert (!league.checkPlayerJoined(player2));

            assert (league.leave(player3));
            assert (!league.checkPlayerJoined(player3));

            league.printAllMember();
        }
    }

    public static void joinTest() {
        final int playerCount = 100;
        Player[] players = new Player[playerCount];

        HashSet<Integer> uniqueRatings = new HashSet<>();
        Random generator = new Random();

        while (uniqueRatings.size() < playerCount) {
            int low = 10;
            int high = 1000;
            uniqueRatings.add(generator.nextInt(high - low) + low);
        }

        ArrayList<Integer> randomN = new ArrayList<>(uniqueRatings);
        for (int i = 0; i < playerCount; ++i) {
            players[i] = new Player(i, "", randomN.get(i));
        }

        League league = new League(players);
        ArrayList<Integer> expected = new ArrayList<>(uniqueRatings);
        Collections.sort(expected);

        Player[] actual = league.getBottom(playerCount); // higher rating -> top
        for (int i = 0; i < playerCount; ++i) {
            assert (expected.get(i) == actual[i].getRating());
        }

        ArrayList<Integer> expected2 = new ArrayList<>(uniqueRatings);
        Collections.sort(expected2, Collections.reverseOrder());
        actual = league.getTop(playerCount);

        for (int i = 0; i < playerCount; ++i) {
            assert (expected2.get(i) == actual[i].getRating());
        }
    }

    public static void leaveTest() { // delete from head
        final int playerCount = 50;
        Player[] players = new Player[playerCount];

        HashSet<Integer> uniqueRatings = new HashSet<>();
        Random generator = new Random();

        while (uniqueRatings.size() < playerCount) {
            int low = 10;
            int high = 1000;
            uniqueRatings.add(generator.nextInt(high - low) + low);
        }

        ArrayList<Integer> randomN = new ArrayList<>(uniqueRatings);
        ArrayList<Integer> p = new ArrayList<>(1024);

        for (int i = 0; i < playerCount; ++i) {
            players[i] = new Player(i, "", randomN.get(i));
            p.add(players[i].getRating());
        }

        League league = new League(players);
        for (int i = 0; i < playerCount; ++i) {
            league.leave(players[i]);
            p.remove(0);

            // test equality
            ArrayList<Integer> expected = new ArrayList<>(p);
            Collections.sort(expected);

            Player[] actual = league.getBottom(p.size()); // higher rating -> top
            for (int j = 0; j < p.size(); ++j) {
                assert (expected.get(j) == actual[j].getRating());
            }
        }
    }

    public static void findMatchTest() {
        final int playerCount = 100;
        Player[] players = new Player[playerCount];

        HashSet<Integer> uniqueRatings = new HashSet<>();
        Random generator = new Random();

        while (uniqueRatings.size() < playerCount) {
            int low = 10;
            int high = 1000;
            uniqueRatings.add(generator.nextInt(high - low) + low);
        }

        ArrayList<Integer> randomN = new ArrayList<>(uniqueRatings);

        for (int i = 0; i < playerCount; ++i) {
            players[i] = new Player(i, "", randomN.get(i));
        }

        League league = new League(players);

        ArrayList<Integer> sorted = new ArrayList<>(randomN);
        Collections.sort(sorted);

        for (int i = 0; i < playerCount; ++i) {
            int rating = players[i].getRating();

            int expectedRating = getMatch(sorted, rating);
            assert (expectedRating != -1);

            Player match = league.findMatchOrNull(players[i]);
            int actualRating = match.getRating();

            assert (expectedRating == actualRating);
        }
    }

    public static int getMatch(ArrayList<Integer> sorted, int targetRating) {
        // targetRating이 항상 존재한다고 가정

        int left = 0, right = sorted.size() - 1;

        int targetIndex = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sorted.get(mid) == targetRating) {
                targetIndex = mid;
                break;
            }

            if (targetRating > sorted.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        assert (targetIndex != -1);

        if (targetIndex == 0) {
            return sorted.get(1);
        }

        if (targetIndex == sorted.size() - 1) {
            return sorted.get(sorted.size() - 2);
        }
        int distanceToLeft = Math.abs(sorted.get(targetIndex - 1) - targetRating);
        int distanceToRight = Math.abs(sorted.get(targetIndex + 1) - targetRating);
        return distanceToLeft < distanceToRight ? sorted.get(targetIndex - 1) : sorted.get(targetIndex + 1);
    }
}
