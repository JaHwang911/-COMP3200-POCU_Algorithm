package practice.app;

public class Program {
    public static void main(String[] args) {
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
