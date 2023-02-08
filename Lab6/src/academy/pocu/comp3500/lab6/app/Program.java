package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Program {

    public static void main(String[] args) {
	    testFindMatch();
        testGetTop();
        testGetBottom();
        testJoin();
        testLeave();

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
}
