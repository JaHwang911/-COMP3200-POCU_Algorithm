package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;

import java.util.HashMap;

public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        skillLevelsSortRecursive(skillLevels, 0, skillLevels.length - 1);
        taskSortRecursive(tasks, 0, tasks.length - 1);

        int maxProfit = 0;

        for (int i = 0; i < tasks.length; ++i) {
            for (int j = 0; j < skillLevels.length; ++j) {
                if (skillLevels[j] >= tasks[i].getDifficulty()) {
                    maxProfit += tasks[i].getProfit();

                    skillLevels[j] = -1;
                }
            }

            if (skillLevels[0] == -1) {
                break;
            }
        }

        return maxProfit;
    }

    private static void skillLevelsSortRecursive(final int[] skillLevels, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (skillLevels[j] < skillLevels[right]) {
                int temp = skillLevels[j];
                skillLevels[j] = skillLevels[i];
                skillLevels[i] = temp;

                ++i;
            }
        }

        int temp = skillLevels[i];
        skillLevels[i] = skillLevels[right];
        skillLevels[right] = temp;

        skillLevelsSortRecursive(skillLevels, left, i - 1);
        skillLevelsSortRecursive(skillLevels, i + 1, right);
    }

    private static void taskSortRecursive(final Task[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        int rightProfitPerDifficult = tasks[right].getProfit() / tasks[right].getDifficulty();

        for (int j = left; j < right; ++j) {
            int tempProfitPerDifficult = tasks[i].getProfit() / tasks[i].getDifficulty();

            if (tempProfitPerDifficult >= rightProfitPerDifficult) {
                Task temp = tasks[j];
                tasks[j] = tasks[i];
                tasks[i] = temp;

                ++i;
            }
        }

        Task temp = tasks[i];
        tasks[i] = tasks[right];
        tasks[right] = temp;

        taskSortRecursive(tasks, left, i - 1);
        taskSortRecursive(tasks, i + 1, right);
    }
}