package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class BinarySearchTree {
    private PlayerNode root;

    public BinarySearchTree(PlayerNode root) {
        this.root = root;
    }

    public PlayerNode search(final Player player) {
        return searchOrNullRecursive(this.root, player);
    }

    private PlayerNode searchOrNullRecursive(final PlayerNode root, final Player player) {
        if (root == null) {
            return null;
        }

        if (root.getPlayer().getId() == player.getId()) {
            return root;
        } else if (root.getPlayer().getRating() >= player.getRating()) {
            return searchOrNullRecursive(root.getLeftOrNull(), player);
        } else {
            return searchOrNullRecursive(root.getRightOrNull(), player);
        }
    }

    public void insert(final Player player) {
        insertRecursive(this.root, player);
    }

    private void insertRecursive(final PlayerNode root, final Player player) {
        if (root.getRating() >= player.getRating()) {
            if (root.getLeftOrNull() == null) {
                PlayerNode node = new PlayerNode(player);
                root.setLeft(node);
                node.setParent(root);

                return;
            }

            insertRecursive(root.getLeftOrNull(), player);
        } else {
            if (root.getRightOrNull() == null) {
                PlayerNode node = new PlayerNode(player);
                root.setRight(node);
                node.setParent(root);

                return;
            }

            insertRecursive(root.getRightOrNull(), player);
        }
    }

    public Player findHigherRatingPlayerOrNull(Player delete) {
        PlayerNode target = searchOrNullRecursive(this.root, delete);

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

    private void findHigherRatedPlayerRecursive(final PlayerNode root, final PlayerNode targetPlayer, int minDiff, final PlayerNode[] outPlayer) {
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

    public boolean deletePlayer(final Player player) {
        PlayerNode deleteNode = search(player);

        if (deleteNode == null) {
            return false;
        }

        if (deleteNode.getLeftOrNull() == null && deleteNode.getRightOrNull() == null) {
            PlayerNode parent = deleteNode.getParentOrNull();

            if (parent == null) {
                assert (this.root.getId() == deleteNode.getId());

                this.root = null;
                return true;
            }

            if (parent.getRating() > deleteNode.getRating()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }

            deleteNode.setParent(null);

            return true;
        }

        PlayerNode swapNode;

        if (deleteNode.getRightOrNull() == null) {
            swapNode = getLeftSwapNodeRecursive(deleteNode.getLeftOrNull());
        } else {
            swapNode = getRightSwapNodeRecursive(deleteNode.getRightOrNull());
        }

        swapNode.setLeft(deleteNode.getLeftOrNull());
        swapNode.setRight(deleteNode.getRightOrNull());

        if (swapNode.getLeftOrNull() != null) {
            swapNode.getLeftOrNull().setParent(swapNode);
        }

        if (swapNode.getRightOrNull() != null) {
            swapNode.getRightOrNull().setParent(swapNode);
        }

        deleteNode.setLeft(null);
        deleteNode.setRight(null);

        if (deleteNode.getParentOrNull() == null) {
            this.root = swapNode;
        }

        PlayerNode parent = deleteNode.getParentOrNull();

        if (parent.getRating() < deleteNode.getRating()) {
            parent.setRight(swapNode);
        } else {
            parent.setLeft(swapNode);
        }

        swapNode.setParent(parent);
        deleteNode.setParent(null);

        return true;
    }

    public PlayerNode getLeftSwapNodeRecursive(final PlayerNode start) {
        if (start.getRightOrNull() == null) {
            PlayerNode parent = start.getParentOrNull();

            if (parent.getRating() < start.getRating()) {
                parent.setRight(start.getLeftOrNull());
            } else {
                parent.setLeft(start.getLeftOrNull());
            }

            return start;
        }

        return getLeftSwapNodeRecursive(start.getRightOrNull());
    }

    public PlayerNode getRightSwapNodeRecursive(final PlayerNode start) {
        if (start.getLeftOrNull() == null) {
            PlayerNode parent = start.getParentOrNull();

            if (parent.getRating() < start.getRating()) {
                parent.setRight(start.getRightOrNull());
            } else {
                parent.setLeft(start.getRightOrNull());
            }

            return start;
        }

        return getRightSwapNodeRecursive(start.getLeftOrNull());
    }

    public void print() {
        traversalRecursive(this.root);
        System.out.println();
    }

    public void traversalRecursive(PlayerNode root) {
        if (root == null) {
            return;
        }

        traversalRecursive(root.getLeftOrNull());
        System.out.printf("%d, ", root.getRating());
        traversalRecursive(root.getRightOrNull());
    }
}
