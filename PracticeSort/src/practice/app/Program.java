package practice.app;

import java.util.Random;

public class Program {
    public static void main(String[] args) {
        System.out.println("##### Start bubble sort test #####");
        for (int i = 0; i < 100; ++i) {
            testBubbleSort();
        }

        System.out.println("##### Start selection sort test #####");
        for (int i = 0; i < 100; ++i) {
            testSelectionSort();
        }

        System.out.println("##### Start insertion sort test #####");
        for (int i = 0; i < 100; ++i) {
            testInsertionSort();
        }

        System.out.println("##### Start quick sort test #####");
        for (int i = 0; i < 100; ++i) {
            testQuickSort();
        }

        System.out.println("##### Start merge sort test #####");
        for (int i = 0; i < 100; ++i) {
            testMergeSort();
        }
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

    private static void testBubbleSort() {
        int[] nums = generateNumbers();

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

    private static void testSelectionSort() {
        int[] nums = generateNumbers();

        for (int i = 0; i < nums.length; ++i) {
            int minIndex = i;

            for (int j = i; j < nums.length; ++j) {
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

    private static void testInsertionSort() {
        int[] nums = generateNumbers();

        for (int i = 1; i < nums.length; ++i) {
            int j = i;

            while (j > 0 && nums[j - 1] > nums[j]) {
                int tmp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = tmp;

                --j;
            }
        }

        checkSortResult(nums);
    }

    private static void testQuickSort() {
        int[] nums = generateNumbers();

        quickSortRecursive(nums, 0, nums.length - 1);

        checkSortResult(nums);
    }

    private static void quickSortRecursive(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;

        for (int j = i; j < right; ++j) {
            if (arr[j] < arr[right]) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                ++i;
            }
        }

        int tmp = arr[right];
        arr[right] = arr[i];
        arr[i] = tmp;

        quickSortRecursive(arr, left, i - 1);
        quickSortRecursive(arr, i + 1, right);
    }

    private static void testMergeSort() {
        int[] nums = generateNumbers();

        mergeSortRecursive(nums, 0, nums.length - 1);

        checkSortResult(nums);
    }

    private static void mergeSortRecursive(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSortRecursive(arr, left, mid);
        mergeSortRecursive(arr, mid + 1, right);
        sort(arr, left, mid, right);
    }

    private static void sort(int[] arr, int left, int mid, int right) {
        final int TEMP_ARRAY_1_SIZE = mid - left + 1;
        final int TEMP_ARRAY_2_SIZE = right - mid;

        int[] temp1 = new int[TEMP_ARRAY_1_SIZE];
        int[] temp2 = new int[TEMP_ARRAY_2_SIZE];

        for (int i = 0; i < temp1.length; ++i) {
            temp1[i] = arr[left + i];
        }

        for (int j = 0; j < temp2.length; ++j) {
            temp2[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < temp1.length && j < temp2.length) {
            if (temp1[i] < temp2[j]) {
                arr[k++] = temp1[i++];
            } else {
                arr[k++] = temp2[j++];
            }
        }

        while (i < temp1.length) {
            arr[k++] = temp1[i++];
        }

        while (j < temp2.length) {
            arr[k++] = temp2[j++];
        }
    }
}
