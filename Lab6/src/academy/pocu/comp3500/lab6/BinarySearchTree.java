package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

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
                playerNode.setParent(root);

                return;
            }

            insertRecursive(root.getLeftOrNull(), playerNode);
        } else {
            if (root.getRightOrNull() == null) {
                root.setRight(playerNode);
                playerNode.setParent(root);

                return;
            }

            insertRecursive(root.getRightOrNull(), playerNode);
        }
    }

    public Player findHigherRatingPlayerOrNull(Player start) {
        PlayerNode target = searchOrNullRecursive(this.root, start);

        if (target == null) {
            return null;
        }

        PlayerNode[] out = new PlayerNode[] {this.root};

        if (target == this.root) {
            if (this.root.getLeftOrNull() == null && this.root.getRightOrNull() == null) {
                return null;
            } else if (this.root.getLeftOrNull() == null) {
                out[0] = this.root.getRightOrNull();
                findHigherRatedPlayerRecursive(this.root.getRightOrNull(), target, Integer.MAX_VALUE, out);
            } else {
                out[0] = this.root.getLeftOrNull();
                findHigherRatedPlayerRecursive(this.root.getLeftOrNull(), target, Integer.MAX_VALUE, out);
            }
        } else {
            findHigherRatedPlayerRecursive(this.root, target, Integer.MAX_VALUE, out);
        }

        return out[0].getPlayer();
    }

    private void findHigherRatedPlayerRecursive(PlayerNode root, PlayerNode targetPlayer, int minDiff, PlayerNode[] outPlayer) {
        if (root == null) {
            return;
        }

        int diff = root.getRating() - targetPlayer.getRating();

        if (diff != 0 && Math.abs(diff) < Math.abs(minDiff)) {
            minDiff = diff;
            outPlayer[0] = root;
        } else if (Math.abs(diff) == Math.abs(minDiff)) {
            if (diff > minDiff) {
                minDiff = diff;
                outPlayer[0] = root;
            }
        }

        if (root.getRating() >= targetPlayer.getRating()) {
            findHigherRatedPlayerRecursive(root.getLeftOrNull(), targetPlayer, minDiff, outPlayer);
        } else {
            findHigherRatedPlayerRecursive(root.getRightOrNull(), targetPlayer, minDiff, outPlayer);
        }
    }

    public Player[] getTop(final int count) {
        Player[] result = new Player[count];
        ArrayList<Player> out = new ArrayList<>(count);

        getTopRecursive(this.root, count, out);

        assert (result.length == out.size());

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void getTopRecursive(final PlayerNode root, final int maxCount, final ArrayList<Player> out) {
        if (root == null) {
            return;
        }

        getTopRecursive(root.getRightOrNull(), maxCount, out);

        if (out.size() < maxCount) {
            out.add(root.getPlayer());
        }

        getTopRecursive(root.getLeftOrNull(), maxCount, out);
    }

    public Player[] getBottom(final int count) {
        Player[] result = new Player[count];
        ArrayList<Player> out = new ArrayList<>(count);

        getBottomRecursive(this.root, count, out);

        assert (result.length == out.size());

        for (int i = 0; i < out.size(); ++i) {
            result[i] = out.get(i);
        }

        return result;
    }

    private void getBottomRecursive(final PlayerNode root, final int maxCount, final ArrayList<Player> out) {
        if (root == null) {
            return;
        }

        getBottomRecursive(root.getLeftOrNull(), maxCount, out);

        if (out.size() < maxCount) {
            out.add(root.getPlayer());
        }

        getBottomRecursive(root.getRightOrNull(), maxCount, out);
    }
}
