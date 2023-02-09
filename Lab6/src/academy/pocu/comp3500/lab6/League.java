package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class League {
    private BinarySearchTree playerTreeRoot;

    public League() {
        this.playerTreeRoot = new BinarySearchTree();
    }

    public League(Player[] players) {
        this.playerTreeRoot = new BinarySearchTree(new PlayerNode(players[0]));

        for (int i = 1; i < players.length; ++i) {
            this.playerTreeRoot.insert(players[i]);
        }
    }

    public Player findMatchOrNull(final Player player) {
        return this.playerTreeRoot.findMatchOrNull(player);
    }

    public Player[] getTop(final int count) {
        if (count == 0) {
            return new Player[0];
        }

        return this.playerTreeRoot.getTop(count);
    }

    public Player[] getBottom(final int count) {
        if (count == 0) {
            return new Player[0];
        }

        return this.playerTreeRoot.getBottom(count);
    }

    public boolean join(final Player player) {
        return this.playerTreeRoot.insert(player);
    }

    public boolean leave(final Player player) {
        boolean result = this.playerTreeRoot.deletePlayer(player);
        this.playerTreeRoot.print();

        return result;
    }

    public boolean checkPlayerJoined(Player player) {
        return (this.playerTreeRoot.search(player) != null);
    }

    public void printAllMember() {
        this.playerTreeRoot.print();
    }
}