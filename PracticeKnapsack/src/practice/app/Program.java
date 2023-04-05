package practice.app;

import practice.Item;

public class Program {
    public static void main(String[] args) {
        Item[] items1 = new Item[] {
                new Item(5, 5),
                new Item(2, 4),
                new Item(6, 11)
        };

        int maxValue = getMaxValue(15, items1);
        assert (maxValue == 8);

        Item[] items2 = new Item[] {
                new Item(3, 5),
                new Item(9, 12),
                new Item(1, 2),
                new Item(5, 4),
                new Item(7, 9),
        };

        maxValue = getMaxValue(15, items2);
        assert (maxValue == 13);
    }

    private static int getMaxValue(final int numSpace, Item[] items) {
        int[][] knapsack = new int[items.length][numSpace + 1];

        for (int i = 1; i <= numSpace; ++i) {
            if (i < items[0].space) {
                continue;
            }

            knapsack[0][i] = items[0].value;
        }

        for (int i = 1; i < items.length; ++i) {
            for (int j = 1; j <= numSpace; ++j) {
                if (j < items[i].space) {
                    knapsack[i][j] = knapsack[i - 1][j];
                    continue;
                }

                int remainingSpace = j - items[i].space;
                int maxValue = items[i].value + knapsack[i - 1][remainingSpace];

                int resultValue = Math.max(knapsack[i - 1][j], maxValue);
                knapsack[i][j] = resultValue;
            }
        }

        return knapsack[knapsack.length - 1][numSpace];
    }
}
