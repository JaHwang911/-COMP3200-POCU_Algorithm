package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class League {
    private BinarySearchTree playerTreeRoot;

    public League() {
    }

    public League(Player[] players) {
        this.playerTreeRoot = new BinarySearchTree(new PlayerNode(players[0]));

        for (int i = 1; i < players.length; ++i) {
            this.playerTreeRoot.insert(players[i]);
        }
    }

    public boolean checkPlayerJoined(Player player) {
        return (this.playerTreeRoot.search(player) != null);
    }

    public Player findMatchOrNull(final Player player) {
        if (this.playerTreeRoot == null) {
            return null;
        }

        return this.playerTreeRoot.findHigherRatingPlayerOrNull(player);
    }

    public Player[] getTop(final int count) {
        if (this.playerTreeRoot == null) {
            return null;
        }

        return this.playerTreeRoot.getTop(count);
    }

    public Player[] getBottom(final int count) {
        if (this.playerTreeRoot == null) {
            return null;
        }

        return this.playerTreeRoot.getBottom(count);
    }

    public boolean join(final Player player) {
        if (this.playerTreeRoot.search(player) != null) {
            return false;
        }

        this.playerTreeRoot.insert(player);
        return true;
    }

    public boolean leave(final Player player) {
        return this.playerTreeRoot.deletePlayer(player);
    }

    public void printAllMember() {
        this.playerTreeRoot.print();
    }
}