package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class BinarySearchTree {
    private PlayerNode root;
    private int count;

    public BinarySearchTree() {

    }

    public BinarySearchTree(final PlayerNode root) {
        this.root = root;
        this.count = 1;
    }

    public PlayerNode search(final Player player) {
        if (this.root == null) {
            return null;
        }

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

    public boolean insert(final Player player) {
        if (search(player) != null) {
            return false;
        } else if (this.root == null) {
            this.root = new PlayerNode(player);
            ++this.count;

            return true;
        }

        insertRecursive(this.root, player);
        ++this.count;

        return true;
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

    public Player findMatchOrNull(final Player player) {
        PlayerNode target = search(player);

        if (target == null) {
            return null;
        } else if (target == this.root && this.root.getLeftOrNull() == null && this.root.getRightOrNull() == null) {
            return null;
        }

        PlayerNode[] out = new PlayerNode[1];
        int[] minDiff = new int[1];

        out[0] = this.root;
        minDiff[0] = Integer.MAX_VALUE;

        findMatchOrNullRecursive(this.root, target, minDiff, out);

        return out[0].getPlayer();
    }

    private void findMatchOrNullRecursive(final PlayerNode root, final PlayerNode targetPlayer, final int[] minDiff, final PlayerNode[] outPlayer) {
        if (root == null) {
            return;
        }

        int diff = root.getRating() - targetPlayer.getRating();

        if (diff != 0 && Math.abs(diff) < Math.abs(minDiff[0])) {
            minDiff[0] = diff;
            outPlayer[0] = root;
        } else if (Math.abs(diff) == Math.abs(minDiff[0])) {
            if (diff - minDiff[0] > 0) {
                minDiff[0] = diff;
                outPlayer[0] = root;
            }
        }

        if (root.getRating() == targetPlayer.getRating()) {
            findMatchOrNullRecursive(root.getRightOrNull(), targetPlayer, minDiff, outPlayer);
            findMatchOrNullRecursive(root.getLeftOrNull(), targetPlayer, minDiff, outPlayer);
        } else if (root.getRating() < targetPlayer.getRating()) {
            findMatchOrNullRecursive(root.getRightOrNull(), targetPlayer, minDiff, outPlayer);
        } else {
            findMatchOrNullRecursive(root.getLeftOrNull(), targetPlayer, minDiff, outPlayer);
        }
    }

    public Player[] getTop(final int count) {
        Player[] result;
        ArrayList<Player> out;
        int topCount;

        if (count > this.count) {
            result = new Player[this.count];
            out = new ArrayList<>(this.count);
            topCount = this.count;
        } else {
            result = new Player[count];
            out = new ArrayList<>(count);
            topCount = count;
        }

        getTopRecursive(this.root, topCount, out);

        return out.toArray(result);
    }

    private void getTopRecursive(final PlayerNode root, final int maxCount, final ArrayList<Player> out) {
        if (root == null) {
            return;
        }

        getTopRecursive(root.getRightOrNull(), maxCount, out);

        if (out.size() < maxCount) {
            out.add(root.getPlayer());
        } else if (out.size() == maxCount) {
            return;
        }

        getTopRecursive(root.getLeftOrNull(), maxCount, out);
    }

    public Player[] getBottom(final int count) {
        Player[] result;
        ArrayList<Player> out;
        int bottomCount;

        if (count > this.count) {
            result = new Player[this.count];
            out = new ArrayList<>(this.count);
            bottomCount = this.count;
        } else {
            result = new Player[count];
            out = new ArrayList<>(count);
            bottomCount = count;
        }

        getBottomRecursive(this.root, bottomCount, out);

        return out.toArray(result);
    }

    private void getBottomRecursive(final PlayerNode root, final int maxCount, final ArrayList<Player> out) {
        if (root == null) {
            return;
        }

        getBottomRecursive(root.getLeftOrNull(), maxCount, out);

        if (out.size() < maxCount) {
            out.add(root.getPlayer());
        } else if (out.size() == maxCount) {
            return;
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
                --this.count;

                return true;
            }

            if (parent.getRating() > deleteNode.getRating()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }

            deleteNode.setParent(null);
            --this.count;

            return true;
        }

        PlayerNode swapNode;

        if (deleteNode.getRightOrNull() == null) {
            swapNode = getSwapNodeRecursive(deleteNode.getLeftOrNull(), deleteNode);
        } else {
            swapNode = getSwapNodeRecursive(deleteNode.getRightOrNull(), deleteNode);
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
            swapNode.setParent(null);

            --this.count;

            return true;
        }

        PlayerNode parent = deleteNode.getParentOrNull();

        if (parent.getRating() < deleteNode.getRating()) {
            parent.setRight(swapNode);
        } else {
            parent.setLeft(swapNode);
        }

        swapNode.setParent(parent);
        deleteNode.setParent(null);

        --this.count;

        return true;
    }

    public PlayerNode getSwapNodeRecursive(final PlayerNode start, final PlayerNode deleteNode) {
        if (start.getRating() < deleteNode.getRating()) {
            if (start.getRightOrNull() == null) {
                PlayerNode parent = start.getParentOrNull();

                if (parent.getRating() < start.getRating()) {
                    parent.setRight(start.getLeftOrNull());
                } else {
                    parent.setLeft(start.getLeftOrNull());
                }

                if (start.getLeftOrNull() != null) {
                    start.getLeftOrNull().setParent(parent);
                }

                return start;
            }

            return getSwapNodeRecursive(start.getRightOrNull(), deleteNode);
        } else {
            if (start.getLeftOrNull() == null) {
                PlayerNode parent = start.getParentOrNull();

                if (parent.getRating() < start.getRating()) {
                    parent.setRight(start.getRightOrNull());
                } else {
                    parent.setLeft(start.getRightOrNull());
                }

                if (start.getRightOrNull() != null) {
                    start.getRightOrNull().setParent(parent);
                }

                return start;
            }

            return getSwapNodeRecursive(start.getLeftOrNull(), deleteNode);
        }
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
