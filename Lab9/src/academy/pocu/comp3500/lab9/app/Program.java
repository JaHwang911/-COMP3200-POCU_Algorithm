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
            VideoClip[] clips = new VideoClip[]{
                    new VideoClip(0, 7),
                    new VideoClip(8, 13),
                    new VideoClip(9, 20)
            };

            int count = CodingMan.findMinClipsCount(clips, 7);
            assert (count == 1);

            count = CodingMan.findMinClipsCount(clips, 13);
            assert (count == -1);

            count = CodingMan.findMinClipsCount(clips, 20);
            assert (count == -1);
        }

        {
            VideoClip[] clips = new VideoClip[] {
                    new VideoClip(0, 5),
                    new VideoClip(1, 6),
                    new VideoClip(2, 7),
                    new VideoClip(3, 8),
                    new VideoClip(4, 9)
            };

            int count = CodingMan.findMinClipsCount(clips, 6);
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 7);
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 8);
            assert (count == 2);

            count = CodingMan.findMinClipsCount(clips, 9);
            assert (count == 2);
        }

        // Wiki Test
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

            int airTime = 35;

            int count = CodingMan.findMinClipsCount(clips, 35);
            assert (count == 4);

            clips = new VideoClip[]{
                    new VideoClip(0, 7),
                    new VideoClip(7, 15),
                    new VideoClip(15, 20),
                    new VideoClip(20, 25),
                    new VideoClip(25, 35)
            };

            count = CodingMan.findMinClipsCount(clips, airTime);
            assert (count == 5);

            clips = new VideoClip[]{
                    new VideoClip(0, 7),
                    new VideoClip(4, 8),
                    new VideoClip(5, 15),
                    new VideoClip(13, 16),
                    new VideoClip(15, 34),
                    new VideoClip(20, 35),
                    new VideoClip(23, 37),
                    new VideoClip(35, 60),
                    new VideoClip(38, 62)
            };

            airTime = 61;
            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 6);

            clips = new VideoClip[]{
                    new VideoClip(0, 3),
                    new VideoClip(2, 4),
                    new VideoClip(3, 5),
                    new VideoClip(5, 12),
                    new VideoClip(10, 17)
            };

            airTime = 13;
            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 4);

            clips = new VideoClip[]{
                    new VideoClip(0, 3),
                    new VideoClip(2, 5),
                    new VideoClip(4, 8),
                    new VideoClip(1, 3),
                    new VideoClip(2, 3),
            };

            airTime = 7;
            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 3);

            clips = new VideoClip[]{
                    new VideoClip(0, 3),
                    new VideoClip(0, 3),
                    new VideoClip(0, 4),
                    new VideoClip(1, 4),
                    new VideoClip(2, 3),
            };

            airTime = 4;
            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 1);
        }

        {
            VideoClip[] clips1 = new VideoClip[]{
                    new VideoClip(0, 7),
                    new VideoClip(7, 15)
            };

            int count1 = CodingMan.findMinClipsCount(clips1, 35);
            assert (count1 == -1);
        }

        {
            VideoClip[] clips1 = new VideoClip[]{
                    new VideoClip(0, 7),
                    new VideoClip(8, 15),
                    new VideoClip(15, 20),
                    new VideoClip(20, 25),
                    new VideoClip(25, 35)
            };
            int count1 = CodingMan.findMinClipsCount(clips1, 35);
            assert (count1 == -1);

        }

        {
            VideoClip[] clips1 = new VideoClip[]{};
            int count1 = CodingMan.findMinClipsCount(clips1, 35);
            assert (count1 == -1);
        }
    }
}
