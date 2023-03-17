package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.ProfitCalculator;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        testPyramid();
        testMaxProfit();
        testFindMinClipsCount();

        System.out.println("No prob lab9");
    }

    private static void testPyramid() {
        {
            int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5}, 10);
            assert (pyramidHeight == 0);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{6, 8}, 10);
            assert (pyramidHeight == 1);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 4, 4, 30, 12, 10, 10, 6}, 5);
            assert (pyramidHeight == 3);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{2, 2, 2, 2, 3, 3}, 1);
            assert (pyramidHeight == 2);
        }

        {
            int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{25}, 2);
            assert (pyramidHeight == 0);
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{1, 1, 1, 1, 1, 1}, 2);
            assert (pyramidHeight == 1);
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3}, 2);
            assert (pyramidHeight == 1);
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 3, 3}, 2);
            assert (pyramidHeight == 1);
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 3, 3, 3}, 2);
            assert (pyramidHeight == 2);
        }
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
                    new VideoClip(0, 7),
                    new VideoClip(0, 15),
                    new VideoClip(10, 20),
                    new VideoClip(15, 25),
                    new VideoClip(20, 26),
                    new VideoClip(23, 30),
                    new VideoClip(24, 31),
                    new VideoClip(25, 32),
                    new VideoClip(27, 32),
                    new VideoClip(28, 32),
                    new VideoClip(25, 33),
                    new VideoClip(29, 33),
                    new VideoClip(30, 35),
                    new VideoClip(33, 35),
                    new VideoClip(31, 40),
                    new VideoClip(40, 50),
            };

            int count = CodingMan.findMinClipsCount(clips, 35);
        }
    }
}
