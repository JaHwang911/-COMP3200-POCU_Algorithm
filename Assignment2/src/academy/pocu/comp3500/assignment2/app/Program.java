package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Logger;
import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) {
        log("hello1");
        log("world1");

        Logger.indent();
        {
            log("hello2");
            log("world2");
        }

        Logger.printTo();
    }
}
