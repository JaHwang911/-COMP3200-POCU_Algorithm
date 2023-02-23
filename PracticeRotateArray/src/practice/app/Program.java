package practice.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        testGetIndexOfSecondMax();
        testGetIndexOfSecondMin();
        testBasicBinarySearch();
        testGetIndexOf();
    }

    private static void testBasicBinarySearch() {
        Random random = new Random();
        final int DATA_SIZE = 100;
        HashSet<Integer> uniqueNums = new HashSet<>(DATA_SIZE);

        for (int i = 0; uniqueNums.size() < DATA_SIZE; ++i) {
            uniqueNums.add(random.nextInt(100000 - 1) + 1);
        }

        assert (uniqueNums.size() == DATA_SIZE);

        ArrayList<Integer> nums = new ArrayList<>(uniqueNums);
        Collections.sort(nums);

        final int MID = (DATA_SIZE - 1) / 2 - 1;
        int index = indexOfRecursive(nums, 0, nums.size(), nums.get(MID), 1);
        assert (index == MID);
    }

    private static int indexOfRecursive(final ArrayList<Integer> nums, final int left, final int right, final int target, int level) {
        if (left >= right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums.get(mid) == target) {
            System.out.println(level);
            return mid;
        }

        if (nums.get(mid) < target) {
            return indexOfRecursive(nums, mid + 1, right, target, level + 1);
        }

        return indexOfRecursive(nums, left, mid - 1, target, level + 1);
    }

    private static void rotateArray(int[] arr) {
        assert (arr.length > 1);
        int prev = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            int temp = arr[i];
            arr[i] = prev;
            prev = temp;
        }

        arr[0] = prev;
    }

    private static void testGetIndexOfSecondMax() {
        int[] arr = new int[] { 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19 };

        rotateArray(arr);
        int expected = 4;
        int actual = getIndexOfSecondMax(arr, 0, arr.length - 1);
        assert (expected == actual);

        for (int i = 0; i < arr.length - 1; ++i) {
            rotateArray(arr);

            ++expected;
            if (expected == arr.length) {
                expected = 0;
            }

            actual = getIndexOfSecondMax(arr, 0, arr.length - 1);
            assert (expected == actual);
        }
    }

    private static void testGetIndexOfSecondMin() {
        int[] arr = new int[] { 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19 };

        rotateArray(arr);
        int expected = 7;
        int actual = getIndexOfSecondMin(arr, 0, arr.length - 1);
        assert (expected == actual);

        for (int i = 0; i < arr.length; ++i) {
            rotateArray(arr);
            ++expected;

            if (expected == arr.length) {
                expected = 0;
            }

            actual = getIndexOfSecondMin(arr, 0, arr.length - 1);
            assert (expected == actual);
        }
    }

    private static void testGetIndexOf() {
        int[] arr = new int[] { 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19 };
        final int TARGET = 25;

        rotateArray(arr);
        int expected = 2;
        int actual = indexOfRotateArray(arr, 0, arr.length - 1, TARGET);
        assert (expected == actual);

        for (int i = 0; i < arr.length; ++i) {
            rotateArray(arr);
            ++expected;

            if (expected == arr.length) {
                expected = 0;
            }

            actual = indexOfRotateArray(arr, 0, arr.length - 1, TARGET);
            assert (expected == actual);
        }
    }

    private static int getIndexOfSecondMax(final int[] arr, int left, int right) {
        if (right - left <= 1) {
            int maxIndex = arr[left] > arr[right] ? left : right;

            if (maxIndex == 0) {
                return arr.length - 1;
            }

            return maxIndex - 1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
            return mid - 1;
        }

        if (arr[left] < arr[mid]) {
            return getIndexOfSecondMax(arr, mid + 1, right);
        }

        return getIndexOfSecondMax(arr, left, mid - 1);
    }

    private static int getIndexOfSecondMin(final int[] arr, int left, int right) {
        if (right - left <= 1) {
            int minIndex = arr[left] < arr[right] ? left : right;

            if (minIndex == arr.length - 1) {
                return 0;
            }

            return minIndex + 1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid - 1] > arr[mid] && arr[mid + 1] > arr[mid]) {
            return mid + 1;
        }

        if (arr[mid] < arr[right]) {
            return getIndexOfSecondMin(arr, left, mid - 1);
        }

        return getIndexOfSecondMin(arr, mid + 1, right);
    }

    private static int indexOfRotateArray(final int[] arr, int left, int right, final int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[left] <= arr[mid]) {
            if (target <= arr[mid]) {
                return indexOfRotateArray(arr, left, mid - 1, target);
            }

            return indexOfRotateArray(arr, mid + 1, right, target);
        }

        if (target > arr[mid] && target <= arr[right]) {
            return indexOfRotateArray(arr, mid + 1, right, target);
        }

        return indexOfRotateArray(arr, left, mid - 1, target);
    }
}
