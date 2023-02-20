package practice.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        testBasicBinarySearch();
        testGetIndexOf();
        testGetSecondMax();
    }

    private static void testGetIndexOf() {
        int[] arr = new int[] { 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19 };

        int index = indexOfRotateArray(arr, 0, arr.length - 1, 20);
        assert (index == 0);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 25);
        assert (index == 1);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 26);
        assert (index == 2);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 29);
        assert (index == 3);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 33);
        assert (index == 4);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 1);
        assert (index == 5);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 3);
        assert (index == 6);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 5);
        assert (index == 7);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 6);
        assert (index == 8);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 10);
        assert (index == 9);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 11);
        assert (index == 10);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 19);
        assert (index == 11);

        index = indexOfRotateArray(arr, 0, arr.length - 1, 7);
        assert (index == -1);
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

    private static void testGetSecondMax() {
        int[] arr1 = new int[] { 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11, 19 };
        int[] arr2 = new int[] { 33, 1, 3, 5, 6, 10, 11, 19, 20, 25, 26, 29 };
        int[] arr3 = new int[] { 1, 3, 5, 6, 10, 11, 19, 20, 25, 26, 29, 33 };
        int[] arr4 = new int[] { 19, 20, 25, 26, 29, 33, 1, 3, 5, 6, 10, 11 };

        int index = getIndexOfSecondMax(arr1, 0, arr1.length - 1);
        assert (index == 3);

        index = getIndexOfSecondMax(arr2, 0, arr2.length - 1);
        assert (index == 11);

        index = getIndexOfSecondMax(arr3, 0, arr3.length - 1);
        assert (index == 10);

        index = getIndexOfSecondMax(arr4, 0, arr4.length - 1);
        assert (index == 4);
    }

    private static int getIndexOfSecondMax(final int[] arr, int left, int right) {
        if (right - left <= 1) {
            int maxIndex = arr[right] > arr[left] ? right : left;

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

    private static int indexOfRotateArray(final int[] arr, final int start, final int end, final int num) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (arr[mid] == num) {
            return mid;
        }

        if (arr[start] <= arr[mid]) {
            if (num <= arr[mid]) {
                return indexOfRotateArray(arr, start, mid - 1, num);
            }

            return indexOfRotateArray(arr, mid + 1, end, num);
        }

        if (num > arr[mid] && num <= arr[end]) {
            return indexOfRotateArray(arr, mid + 1, end, num);
        }

        return indexOfRotateArray(arr, start, mid - 1, num);
    }
}
