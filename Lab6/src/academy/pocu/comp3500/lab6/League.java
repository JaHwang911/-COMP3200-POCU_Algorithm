package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class League {
    private BinarySearchTree playerTree;

    public League() {

    }

    public League(Player[] players) {
        this.playerTree = new BinarySearchTree(new PlayerNode(players[0]));

        for (int i = 1; i < players.length; ++i) {
            this.playerTree.insert(new PlayerNode(players[i]));
        }
    }

    public Player findMatchOrNull(final Player player) {
        return null;
    }

    public Player[] getTop(final int count) {
        return null;
    }

    public Player[] getBottom(final int count) {
        return null;
    }

    public boolean join(final Player player) {
        return false;
    }

    public boolean leave(final Player player) {
        return false;
    }
}