package academy.pocu.comp3500.assignment2;

import java.io.BufferedWriter;

public final class Logger {
    private static final Indent ROOT = new Indent("", 0, null);
    private static int currentIndentLevel = 0;

    public static void log(final String text) {
        Indent current = getCurrentLevelIndentRecursive(ROOT, 0);

        int logIndex = current.getNextIndents().getSize();
        current.getNextIndents().add(new Indent(text, logIndex, current));
    }

    private static Indent getCurrentLevelIndentRecursive(Indent root, int level) {
        if (level == currentIndentLevel) {
            return root;
        }

        int lastIndent = root.getNextIndents().getSize() - 1;

        return getCurrentLevelIndentRecursive(root.getNextIndents().get(lastIndent), level + 1);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            printLogRecursive(ROOT, "", writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        try {
            printLogRecursive(ROOT, "", filter, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printLogRecursive(final Indent root, final String indent, final BufferedWriter writer) throws java.io.IOException {
        if (root.getNextIndents() == null) {
            return;
        }

        for (int i = 0; i < root.getNextIndents().getSize(); ++i) {
            Indent nextIndent = root.getNextIndents().get(i);

            if (!nextIndent.getData().equals("")) {
                writer.write(indent);
                writer.write(nextIndent.getData());
                writer.newLine();
                System.out.printf("%s%s\n", indent, nextIndent.getData());
            }

            printLogRecursive(nextIndent, indent + "  ", writer);
        }
    }

    private static void printLogRecursive(Indent root, String indent, final String filter, final BufferedWriter writer) throws java.io.IOException {
        if (root.getNextIndents() == null) {
            return;
        }

        for (int i = 0; i < root.getNextIndents().getSize(); ++i) {
            Indent nextIndent = root.getNextIndents().get(i);

            if (nextIndent.getData().contains(filter) && !nextIndent.getData().equals("")) {
                writer.write(indent);
                writer.write(nextIndent.getData());
                writer.newLine();

                System.out.printf("%s%s\n", indent, nextIndent.getData());
            }

            printLogRecursive(nextIndent, indent + "  ", filter, writer);
        }
    }

    public static void clear() {
        ROOT.getNextIndents().clear();
        currentIndentLevel = 0;
    }

    public static Indent indent() {
        Indent current = getCurrentLevelIndentRecursive(ROOT, 0);
        int index = current.getNextIndents().getSize();

        current.getNextIndents().add(new Indent("", index, current));

        ++currentIndentLevel;

        return current.getNextIndents().get(current.getNextIndents().getSize() - 1);
    }

    public static void unindent() {
        if (currentIndentLevel == 0) {
            return;
        }

        --currentIndentLevel;
    }
}