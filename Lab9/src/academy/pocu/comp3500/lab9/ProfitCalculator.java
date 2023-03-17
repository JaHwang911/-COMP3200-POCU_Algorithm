package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;

import java.util.HashMap;

public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        taskSortByDifficultyRecursive(tasks, 0, tasks.length - 1);

        int maxProfit = 0;

        for (int i = 0; i < skillLevels.length; ++i) {
            int tempProfit = 0;

            for (int j = 0; j < tasks.length; ++j) {
                if (skillLevels[i] >= tasks[j].getDifficulty()) {
                    if (tempProfit < tasks[j].getProfit()) {
                        tempProfit = tasks[j].getProfit();
                    }
                } else {
                    break;
                }
            }

            maxProfit += tempProfit;
        }

        return maxProfit;
    }

    private static void taskSortByDifficultyRecursive(final Task[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;

        for (int j = left; j < right; ++j) {
            if (tasks[j].getDifficulty() < tasks[right].getDifficulty()) {
                Task temp = tasks[j];
                tasks[j] = tasks[i];
                tasks[i] = temp;

                ++i;
            }
        }

        Task temp = tasks[i];
        tasks[i] = tasks[right];
        tasks[right] = temp;

        taskSortByDifficultyRecursive(tasks, left, i - 1);
        taskSortByDifficultyRecursive(tasks, i + 1, right);
    }
}