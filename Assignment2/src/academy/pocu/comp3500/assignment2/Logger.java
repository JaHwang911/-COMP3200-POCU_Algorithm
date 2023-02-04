package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

import java.io.BufferedWriter;

public final class Logger {
    private static final Indent ROOT = new Indent("", null);
    private static int currentIndentLevel = 0;

    public static void log(final String text) {
        Indent current = getCurrentLevelIndentRecursive(ROOT, 0);
        current.getNext().add(new Indent(text, current));
    }

    private static Indent getCurrentLevelIndentRecursive(Indent root, int level) {
        if (level == currentIndentLevel) {
            return root;
        }

        int lastIndent = root.getNext().getSize() - 1;

        return getCurrentLevelIndentRecursive(root.getNext().get(lastIndent), level + 1);
    }

    public static void printTo() {
        printLogRecursive(ROOT, "");
    }

    public static void printTo(final BufferedWriter writer) {

    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    private static void printLogRecursive(Indent root, String indent) {
        if (root.getNext().getSize() == 0) {
            return;
        }

        for (int i = 0; i < root.getNext().getSize(); ++i) {
            Indent nextIndent = root.getNext().get(i);

            System.out.printf("%s%s\n", indent, nextIndent.getData());
            printLogRecursive(nextIndent, indent + "  ");
        }
    }

    public static void clear() {

    }

    public static Indent indent() {
        ++currentIndentLevel;

        return getCurrentLevelIndentRecursive(ROOT, currentIndentLevel);
    }

    public static void unindent() {

    }
}