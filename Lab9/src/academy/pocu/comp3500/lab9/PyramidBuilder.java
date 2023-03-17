package academy.pocu.comp3500.lab9;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        quickSortRecursive(widths, 0, widths.length - 1);

        int totalLevel = 0;
        int prevLevelStoneCount = 1;
        int currLevelStoneWidth = 0;
        int currLevelStoneCount = 0;

        int i = 0;

        for (; i < widths.length; ++i) {
            currLevelStoneWidth += widths[i];
            ++currLevelStoneCount;

            if (currLevelStoneWidth > statue && currLevelStoneCount > prevLevelStoneCount) {
                prevLevelStoneCount = currLevelStoneCount;
                currLevelStoneCount = 0;

                ++totalLevel;
                break;
            }
        }

        for (; i < widths.length; ++i) {
            ++currLevelStoneCount;

            if (prevLevelStoneCount < currLevelStoneCount) {
                prevLevelStoneCount = currLevelStoneCount;
                currLevelStoneCount = 0;

                ++totalLevel;
            }
        }

        return totalLevel;
    }

    private static void quickSortRecursive(final int[] widths, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (widths[j] < widths[right]) {
                int temp = widths[j];
                widths[j] = widths[i];
                widths[i] = temp;

                ++i;
            }
        }

        int temp = widths[i];
        widths[i] = widths[right];
        widths[right] = temp;

        quickSortRecursive(widths, left, i - 1);
        quickSortRecursive(widths, i + 1, right);
    }
}