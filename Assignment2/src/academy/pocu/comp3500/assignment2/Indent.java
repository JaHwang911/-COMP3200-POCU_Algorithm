package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public final class Indent {
    private String data;
    private int index;
    private Indent parent;
    private ArrayList<Indent> next;

    public Indent(String data, int index, Indent parent) {
        this.data = data;
        this.parent = parent;
        this.index = index;

        if (data.equals("")) {
            this.next = new ArrayList<>(32);
        }
    }

    public String getData() {
        return this.data;
    }

    public ArrayList<Indent> getNextIndents() {
        return this.next;
    }

    public void discard() {
        this.parent.getNextIndents().remove(this.index);

        this.data = null;
        this.parent = null;
        this.next = null;
    }
}