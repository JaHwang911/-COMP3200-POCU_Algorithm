package pratice.app;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        TestBubbleSort();
        TestSelectionSort();
        TestInsertionSort();
        TestQuickSort();
    }

    private static void checkSortResult(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            assert(nums[i] == i + 1);
        }
    }

    private static void shuffleNumbers(int[] nums) {
        Random random = new Random();

        for (int i = 0; i < nums.length; ++i) {
            int index = random.nextInt(1000) % nums.length;
            int swap = random.nextInt(1000) % nums.length;

            assert(index < nums.length);
            assert(swap < nums.length);

            int tmp = nums[index];
            nums[index] = nums[swap];
            nums[swap] = tmp;
        }
    }

    private static void TestBubbleSort() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };

        shuffleNumbers(nums);

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length - 1 - i; ++j) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }

        checkSortResult(nums);
    }

    private static void TestSelectionSort() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };

        shuffleNumbers(nums);

        for (int i = 0; i < nums.length; ++i) {
            int index = i;

            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[index] > nums[j]) {
                    index = j;
                }
            }

            int tmp = nums[i];
            nums[i] = nums[index];
            nums[index] = tmp;
        }

        checkSortResult(nums);
    }

    private static void TestInsertionSort() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        shuffleNumbers(nums);

        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j > 0; --j) {
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }

        checkSortResult(nums);
    }

    private static void TestQuickSort() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        shuffleNumbers(nums);

        quickSortRecursive(nums, 0, nums.length - 1);
    }

    private static void quickSortRecursive(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(nums, left, right);

        quickSortRecursive(nums, left, pivotPos - 1);
        quickSortRecursive(nums, pivotPos + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivotValue = nums[right];
        int i = left;

        for (int j = i; j < right; ++j) {
            if (nums[j] < pivotValue) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;

                ++i;
            }
        }

        int tmp = nums[right];
        nums[right] = nums[i];
        nums[i] = tmp;

        return i;
    }

    private static void TestMergeSort() {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int[] outNums = new int[nums.length];
        shuffleNumbers(nums);

        mergeSortReucursive(nums, 0, nums.length - 1, outNums);

        checkSortResult(nums);
    }

    private static void mergeSortReucursive(int[] nums, int left, int right, int[] outNums) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSortReucursive(nums, left, mid, outNums);
        mergeSortReucursive(nums, mid + 1, right, outNums);
    }
}
