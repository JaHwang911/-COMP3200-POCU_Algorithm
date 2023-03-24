package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.project.Task;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        testOfficial();
//        testSlack();

        System.out.println("No prob lab 10");
    }

    private static void testOfficial() {
        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.get(3).equals("E"));
            assert (schedule.get(4).equals("F"));
            assert (schedule.get(5).equals("I"));
        }

        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == 9);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("E"));
            assert (schedule.indexOf("E") < schedule.indexOf("F"));
            assert (schedule.indexOf("F") < schedule.indexOf("I"));

            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("D") < schedule.indexOf("G"));
            assert (schedule.indexOf("G") < schedule.indexOf("H"));
        }
    }

    private static void testSlack() {
        {
            Task[] tasks = createTasks2();

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.get(3).equals("E"));
            assert (schedule.get(4).equals("F"));
            assert (schedule.get(5).equals("I"));

            schedule = Project.findSchedule(tasks, true);
            assert (schedule.size() == 14);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));

            assert (schedule.indexOf("C") < schedule.indexOf("E"));
            assert (schedule.indexOf("E") < schedule.indexOf("F"));
            assert (schedule.indexOf("F") < schedule.indexOf("I"));

            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("D") < schedule.indexOf("G"));
            assert (schedule.indexOf("G") < schedule.indexOf("H"));
            assert (schedule.indexOf("H") < schedule.indexOf("J"));
            assert (schedule.indexOf("J") < schedule.indexOf("K"));

            assert (schedule.indexOf("C") < schedule.indexOf("N"));
            assert (schedule.indexOf("N") < schedule.indexOf("L"));
            assert (schedule.indexOf("L") < schedule.indexOf("M"));
        }
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);

        return new Task[]{
                a, b, c, d, e, f, g, h, i
        };
    }

    private static Task[] createTasks2() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);
        Task j = new Task("J", 6);
        Task k = new Task("K", 6);
        Task l = new Task("L", 6);
        Task m = new Task("M", 6);
        Task n = new Task("N", 6);
        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, k);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);
        j.addPredecessor(h);
        k.addPredecessor(j);
        l.addPredecessor(n);
        m.addPredecessor(l);
        n.addPredecessor(m, c);
        return new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n
        };
    }

    private static Task[] createTaskXellos() {
        Task a = new Task("A", 1);
        Task b = new Task("B", 2);
        Task c = new Task("C", 3);
        Task d = new Task("D", 4);
        Task e = new Task("E", 5);
        Task f = new Task("F", 6);
        Task g = new Task("G", 7);
        Task h = new Task("H", 8);
        Task i = new Task("I", 9);
        Task j = new Task("J", 10);
        Task k = new Task("K", 11);
        Task l = new Task("L", 12);
        Task m = new Task("M", 13);
        Task n = new Task("N", 14);

        b.addPredecessor(a);
        e.addPredecessor(a);
        f.addPredecessor(b, e);
        c.addPredecessor(b);
        d.addPredecessor(c, f);
        g.addPredecessor(d);
        i.addPredecessor(d);
        h.addPredecessor(g, i);
        j.addPredecessor(d, l, n);
        k.addPredecessor(j);
        m.addPredecessor(j);

        return new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n
        };
    }
}