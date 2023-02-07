package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class PlayerNode {
    private final Player player;
    private PlayerNode parent;
    private PlayerNode left;
    private PlayerNode right;

    public PlayerNode(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerNode getParentOrNull() {
        return this.parent;
    }

    public void setParent(final PlayerNode parent) {
        this.parent = parent;
    }

    public PlayerNode getLeftOrNull() {
        return this.left;
    }

    public void setLeft(final PlayerNode leftOrNull) {
        this.left = leftOrNull;
    }

    public PlayerNode getRightOrNull() {
        return this.right;
    }

    public void setRight(final PlayerNode rightOrNull) {
        this.right = rightOrNull;
    }

    public int getRating() {
        return this.player.getRating();
    }

    public int getId() {
        return this.player.getId();
    }
}
