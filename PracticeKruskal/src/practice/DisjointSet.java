package practice;

import java.util.HashMap;

public final class DisjointSet {
    private class SetNode {
        private String parent;
        private int size;

        public SetNode(String parent, final int size) {
            this.parent = parent;
            this.size = size;
        }
    }

    private HashMap<String, SetNode> sets = new HashMap<>();

    public DisjointSet(String[] nodes) {
        for (String s : nodes) {
            SetNode setNode = new SetNode(s, 1);
            this.sets.put(s, setNode);
        }
    }

    public String find(String node) {
        SetNode setNode = this.sets.get(node);

        if (setNode.parent.equals(node)) {
            return node;
        }

        return find(setNode.parent);
    }

    public void union(String node1, String node2) {
        String parent1 = find(node1);
        String parent2 = find(node2);

        if (parent1.equals(parent2)) {
            return;
        }

        SetNode parent = this.sets.get(node1);
        SetNode child = this.sets.get(node2);

        if (parent.size < child.size) {
            SetNode temp = parent;

            parent = child;
            child = temp;
        }

        child.parent = parent.parent;
        parent.size = parent.size + child.size;
    }
}
