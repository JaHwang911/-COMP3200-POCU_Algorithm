package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class BinarySearchTree {
    private PlayerNode root;

    public BinarySearchTree(PlayerNode root) {
        this.root = root;
    }

    public PlayerNode search(Player player) {
        return searchOrNullRecursive(this.root, player);
    }

    private PlayerNode searchOrNullRecursive(PlayerNode root, Player player) {
        if (root == null) {
            return null;
        }

        if (root.getPlayer() == player) {
            return root;
        } else if (root.getPlayer().getRating() >= player.getRating()) {
            return searchOrNullRecursive(root.getLeftOrNull(), player);
        } else {
            return searchOrNullRecursive(root.getRightOrNull(), player);
        }
    }

    public void insert(PlayerNode playerNode) {
        insertRecursive(this.root, playerNode);
    }

    private void insertRecursive(PlayerNode root, PlayerNode playerNode) {
        if (root.getRating() >= playerNode.getRating()) {
            if (root.getLeftOrNull() == null) {
                root.setLeft(playerNode);

                return;
            }

            insertRecursive(root.getLeftOrNull(), playerNode);
        } else {
            if (root.getRightOrNull() == null) {
                root.setRight(playerNode);

                return;
            }

            insertRecursive(root.getRightOrNull(), playerNode);
        }
    }

    public Player findMatch(PlayerNode start) {
        return getGreaterRecursive(start);
    }

    private Player getGreaterRecursive(PlayerNode start) {
        return null;
    }
}
