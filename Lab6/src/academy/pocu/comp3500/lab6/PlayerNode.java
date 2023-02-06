package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class PlayerNode {
    private final Player player;
    private PlayerNode left;
    private PlayerNode right;

    public PlayerNode(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerNode getLeftOrNull() {
        return this.left;
    }

    public void setLeft(PlayerNode left) {
        this.left = left;
    }

    public PlayerNode getRightOrNull() {
        return this.right;
    }

    public void setRight(PlayerNode right) {
        this.right = right;
    }

    public int getRating() {
        return this.player.getRating();
    }
}
