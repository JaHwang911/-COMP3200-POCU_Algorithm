package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;
import academy.pocu.comp3500.assignment2.datastructure.Sort;

import static academy.pocu.comp3500.assignment2.Logger.log;
import static academy.pocu.comp3500.assignment2.Logger.unindent;

public class Program {

    public static void main(String[] args) {
        testBasic();
        Logger.clear();
        testQuickSortLog();
    }

    private static void testBasic() {
        log("hello1-1");

        Logger.indent();
        {
            log("hello2-1");

            Logger.indent();
            {
                log("hello3-1");
            }
            Logger.unindent();

            log("hello2-2");

            doMagic();

            log("hello2-3");
            Indent indent = Logger.indent();
            {
                log("hello3-3");
            }
            Logger.unindent();

            indent.discard();
        }
        unindent();

        log("hello1-2");

        Logger.printTo();
        Logger.clear();
        Logger.printTo();
    }

    private static void doMagic() {
        Logger.indent();
        {
            log("hello3-2");
        }
        Logger.unindent();
    }

    private static void testQuickSortLog() {
        int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

        Sort.quickSort(nums);
        Logger.printTo("90");
    }
}
