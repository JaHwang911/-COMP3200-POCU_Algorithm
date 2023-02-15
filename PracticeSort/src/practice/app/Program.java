package practice.app;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        System.out.println("##### Start bubble sort test #####");
        for (int i = 0; i < 100; ++i) {
            TestBubbleSort();
        }

        System.out.println("##### Start selection sort test #####");
        for (int i = 0; i < 100; ++i) {
            TestSelectionSort();
        }

        System.out.println("##### Start insertion sort test #####");
        for (int i = 0; i < 100; ++i) {
            TestInsertionSort();
        }

        System.out.println("##### Start quick sort test #####");
        for (int i = 0; i < 100; ++i) {
            TestQuickSort();
        }

//        TestInsertionSort();
//        TestQuickSort();
    }

    private static void checkSortResult(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            assert(nums[i] <= nums[i + 1]);
        }
    }

    private static int[] generateNumbers() {
        Random random = new Random();
        final int SIZE = random.nextInt(100 - 50) + 50;
        int[] result = new int[SIZE];

        for (int i = 0; i < SIZE; ++i) {
            result[i] = random.nextInt(1000 - 1) + 1;
        }

        return result;
    }

    private static void TestBubbleSort() {
        int[] nums = generateNumbers();

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length - i - 1; ++j) {
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
        int[] nums = generateNumbers();

        for (int i = 0; i < nums.length; ++i) {
            int minIndex = i;

            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }

            int tmp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tmp;
        }

        checkSortResult(nums);
    }

    private static void TestInsertionSort() {
        int[] nums = generateNumbers();

        for (int i = 1; i < nums.length; ++i) {
            for (int j = i; j > 0; --j) {
                if (nums[j - 1] > nums[j]) {
                    int tmp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        checkSortResult(nums);
    }

    private static void TestQuickSort() {
        int[] nums = generateNumbers();

        quickSortRecursive(nums, 0, nums.length - 1);

        checkSortResult(nums);
    }

    private static void quickSortRecursive(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;

        for (int j = left; j < right; ++j) {
            if (arr[j] < arr[right]) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;

                ++i;
            }
        }

        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;

        quickSortRecursive(arr, left, i - 1);
        quickSortRecursive(arr, i + 1, right);
    }

    private static void TestMergeSort() {
        int[] nums = generateNumbers();



        checkSortResult(nums);
    }
}
