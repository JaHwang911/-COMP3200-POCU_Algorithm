package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.ProfitCalculator;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class Program {

    public static void main(String[] args) {
        testPyramid();
        testMaxProfit();
        testFindMinClipsCount();
    }

    private static void testPyramid() {
        int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5}, 10);
        assert (pyramidHeight == 0);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{6, 8}, 10);
        assert (pyramidHeight == 1);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 4, 4, 30, 12, 10, 10, 6}, 5);
        assert (pyramidHeight == 3);
    }

    private static void testMaxProfit() {
        Task[] tasks = new Task[]{
                new Task(20, 30),
                new Task(30, 40),
                new Task(10, 35)
        };

        int profit = ProfitCalculator.findMaxProfit(tasks, new int[]{10}); // 35
        assert (profit == 35);

        profit = ProfitCalculator.findMaxProfit(tasks, new int[]{20, 25}); // 70
        assert (profit == 70);

        profit = ProfitCalculator.findMaxProfit(tasks, new int[]{40, 15, 5}); // 75
        assert (profit == 75);

        profit = ProfitCalculator.findMaxProfit(tasks, new int[]{5, 4, 9});
        assert (profit == 0);
    }

    private static void testFindMinClipsCount() {
        {
            VideoClip[] clips = new VideoClip[]{
                    new VideoClip(0, 15),
                    new VideoClip(10, 20),
                    new VideoClip(30, 35)
            };

            int count = CodingMan.findMinClipsCount(clips, 10); // 1
            assert (count == 1);

            count = CodingMan.findMinClipsCount(clips, 20); // 2
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 25); // -1
            assert (count == -1);

            count = CodingMan.findMinClipsCount(clips, 35); // -1
            assert (count == -1);
        }

        {
            VideoClip[] clips = new VideoClip[] {
                    new VideoClip(0, 1),
                    new VideoClip(0, 2),
                    new VideoClip(0, 3),
                    new VideoClip(2, 6),
                    new VideoClip(3, 10)
            };

            int count = CodingMan.findMinClipsCount(clips, 10);
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 5);
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 8);
            assert (count == 2);
        }
    }
}
