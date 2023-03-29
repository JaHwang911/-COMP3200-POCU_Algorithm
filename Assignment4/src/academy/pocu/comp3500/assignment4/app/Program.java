package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

public class Program {

    public static void main(String[] args) {
        testFindManMonth();
        testFindMinDuration();

        System.out.println("No prob");
    }

    private static void testFindManMonth() {
        {
            Task[] tasks = createTasksBasic();
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 16);
        }
        {
            Task[] tasks = createTasksBasic2();
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 21);
        }
    }

    private static void testFindMinDuration() {
        {
            Task[] tasks = createTasksBasic();
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 14);
        }
        {
            Task[] tasks = createTasksBasic2();
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 16);
        }
    }

    private static Task[] createTasksBasic() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 1);
        Task c = new Task("C", 2);
        Task d = new Task("D", 10);

        b.addPredecessor(a);
        d.addPredecessor(b, c);

        return new Task[] { a, b, c, d };
    }

    private static Task[] createTasksBasic2() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 1);
        Task c = new Task("C", 2);
        Task d = new Task("D", 10);
        Task e = new Task("E", 5);
        Task f = new Task("F", 20);

        b.addPredecessor(a);
        d.addPredecessor(b, c, e);

        return new Task[] { a, b, c, d, e, f };
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);
        Task f = new Task("F", 2);
        Task g = new Task("G", 6);
        Task h = new Task("H", 8);
        Task i = new Task("I", 2);
        Task j = new Task("J", 4);
        Task k = new Task("K", 2);
        Task l = new Task("L", 8);
        Task m = new Task("M", 7);
        Task n = new Task("N", 1);
        Task o = new Task("O", 1);
        Task p = new Task("P", 6);
        Task ms1 = new Task("ms1", 6);
        Task ms2 = new Task("ms2", 8);

        c.addPredecessor(b);
        d.addPredecessor(a);

        ms1.addPredecessor(a, c);

        e.addPredecessor(c);
        f.addPredecessor(g);
        g.addPredecessor(e);

        i.addPredecessor(h);
        j.addPredecessor(ms1);

        k.addPredecessor(j);
        n.addPredecessor(k);
        m.addPredecessor(n);
        l.addPredecessor(m);

        p.addPredecessor(i, j);
        o.addPredecessor(j);

        ms2.addPredecessor(o, p);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
        };

        return tasks;
    }
}
