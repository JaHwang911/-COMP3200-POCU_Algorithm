package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

public class Program {

    public static void main(String[] args) {
        testOfficial();
        testFindManMonth();
//        testFindMinDuration();

        System.out.println("No prob assignment 4");
    }

    private static void testOfficial() {
        Task[] tasks = createTasks();

        Project project = new Project(tasks);

        int manMonths1 = project.findTotalManMonths("ms1");
        assert (manMonths1 == 17);

        int manMonths2 = project.findTotalManMonths("ms2");
        assert (manMonths2 == 46);

        int minDuration1 = project.findMinDuration("ms1");
        assert (minDuration1 == 14);

        int minDuration2 = project.findMinDuration("ms2");
        assert (minDuration2 == 32);
    }

    private static void testFindManMonth() {
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);

            b.addPredecessor(a);
            d.addPredecessor(b, c);
            Task[] tasks = new Task[]{ a, b, c, d };
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 16);
        }
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);
            Task e = new Task("E", 5);
            Task f = new Task("F", 20);

            b.addPredecessor(a);
            d.addPredecessor(b, c, e);

            Task[] tasks = new Task[] { a, b, c, d, e, f };
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 21);
        }
        {
            Task t0 = new Task("0", 1);
            Task t1 = new Task("1", 5);
            Task t2 = new Task("2", 3);
            Task t3 = new Task("3", 2);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 12);
            Task t6 = new Task("6", 10);
            Task t7 = new Task("7", 4);
            Task t8 = new Task("8", 5);
            Task t9 = new Task("9", 2);
            Task t10 = new Task("10", 2);
            Task t11 = new Task("11", 6);
            Task t12 = new Task("12", 9);
            Task t13 = new Task("13", 7);
            Task t14 = new Task("14", 4);
            Task t15 = new Task("15", 3);
            Task t16 = new Task("16", 6);
            Task t17 = new Task("17", 2);
            Task t18 = new Task("18", 9);
            Task t19 = new Task("19", 12);

            t1.addPredecessor(t0);
            t2.addPredecessor(t1, t3);
            t3.addPredecessor(t4);
            t4.addPredecessor(t5);
            t5.addPredecessor(t2);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t13);
            t10.addPredecessor(t9);
            t11.addPredecessor(t10, t15);
            t12.addPredecessor(t11);
            t14.addPredecessor(t13);
            t15.addPredecessor(t14);
            t16.addPredecessor(t15, t18);
            t17.addPredecessor(t16);
            t18.addPredecessor(t17);
            t19.addPredecessor(t1, t8, t12);

            Task[] tasks = new Task[] {
                t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19};

            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("11");
            assert (manMonths == 24);
        }

        // Test A
        {
            Task t0 = new Task("0", 1);
            Task t1 = new Task("1", 5);
            Task t2 = new Task("2", 3);
            Task t3 = new Task("3", 2);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 12);
            Task t6 = new Task("6", 10);
            Task t7 = new Task("7", 4);
            t7.addPredecessor(t0, t1, t2, t3, t4, t5, t6);
            Task[] tasks = new Task[]{t0, t1, t2, t3, t4, t5, t6, t7};

            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("7");
            assert (manMonths == 41);
        }

        // Test C
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 34);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 9);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t11);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 45);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 20);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);
            Task t13 = new Task("13", 13);
            Task t14 = new Task("14", 14);
            Task t15 = new Task("15", 15);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);
            t13.addPredecessor(t5, t15);
            t14.addPredecessor(t13);
            t15.addPredecessor(t14);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t11);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 45);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 20);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);
            Task t13 = new Task("13", 13);

            t2.addPredecessor(t1);
            t10.addPredecessor(t2);
            t3.addPredecessor(t2, t5);
            t4.addPredecessor(t3, t8);
            t5.addPredecessor(t4);

            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t7);

            t12.addPredecessor(t11);
            t13.addPredecessor(t9, t10, t12);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("13");
            assert (manMonths == 71);
        }
    }

    private static void testFindMinDuration() {
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);

            b.addPredecessor(a);
            d.addPredecessor(b, c);
            Task[] tasks = new Task[]{ a, b, c, d };
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 14);
        }
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);
            Task e = new Task("E", 5);
            Task f = new Task("F", 20);

            b.addPredecessor(a);
            d.addPredecessor(b, c, e);

            Task[] tasks = new Task[] { a, b, c, d, e, f };
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 16);
        }
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
