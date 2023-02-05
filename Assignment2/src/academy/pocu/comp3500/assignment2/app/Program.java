package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;
import academy.pocu.comp3500.assignment2.datastructure.Sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) {
        try {
            {
                System.out.println("##### Test official #####");
                testOfficial();
            }
            Logger.clear();

            {
                System.out.println("##### Test only level one #####");
                testOnlyLevelOneIndent();
            }
            Logger.clear();

            {
                System.out.println("##### Test only level two #####");
                testOnlyLevelTwoIndent();
            }
            Logger.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testOfficial() throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));

            log("hello");
            log("world");
            log("this is logging at the top level");

            Logger.indent();
            {
                log("using indent, you can indent to organize your logs");
                log("call unindent() to decrease the indentation level");
            }
            Logger.unindent();

            Indent indent = Logger.indent();
            {
                log("whatever I say here");
                log("is discarded!");
                log("too bad!");

            }
            Logger.unindent();

            Logger.indent();
            {
                log("this won't be discarded");
                log("it's true!");

                doMagic();
            }
            Logger.unindent();

            log("back to the top level!");
            log("and let's print the logs");

            indent.discard();
            Logger.printTo(writer);

            Logger.clear();

            log("log was just cleared");
            log("so you start logging from the top level again");

            Logger.printTo(writer);

            writer.close();
        }

        Logger.clear();

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));
            final BufferedWriter writer2 = new BufferedWriter(new FileWriter("quicksort2.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer1);

            Logger.printTo(writer2, "90");

            writer1.close();
            writer2.close();
        }
    }

    private static void doMagic() {
        Indent indent = Logger.indent();
        {
            log("you can also nest an indent");
            log("like this!");
        }
        Logger.unindent();
    }

    private static void testOnlyLevelOneIndent() throws java.io.IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("OnlyLevel1Indent.log"));

        Logger.indent();
        {
            log("level1-1");
        }
        Logger.unindent();

        Logger.indent();
        {
            log("level1-2");
        }
        Logger.unindent();

        Indent indent = Logger.indent();
        {
            log("level1-3");
            indent.discard();
        }
        Logger.unindent();

        Logger.printTo(writer);

        writer.close();
    }

    private static void testOnlyLevelTwoIndent() throws java.io.IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("OnlyLevel1Indent.log"));

        Logger.indent();

        Logger.indent();
        {
            log("level2-1");
        }
        Logger.unindent();

        Logger.indent();
        {
            log("level2-2");
        }
        Logger.unindent();

        Indent indent = Logger.indent();
        {
            log("level2-3");
            indent.discard();
        }
        Logger.unindent();

        Logger.printTo(writer);

        writer.close();
    }
}
