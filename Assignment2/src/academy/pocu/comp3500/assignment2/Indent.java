package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public final class Indent {
    private final String data;
    private ArrayList<Indent> next;

    public Indent(String data) {
        this.data = data;
        this.next = new ArrayList<>(32);
    }

    public String getData() {
        return this.data;
    }

    public ArrayList<Indent> getNext() {
        return this.next;
    }

    public void discard() {
        this.next.clear();
    }
}