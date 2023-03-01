package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;

public class Decryptor {
    private final HashMap<String, ArrayList<String>> words;

    public Decryptor(final String[] codeWords) {
        words = new HashMap<>(64);

        for (String word : codeWords) {
            char[] temp = word.toCharArray();

            quickSortRecursive(temp, 0, temp.length - 1);

            String sorted = new String(temp);

            if (words.containsKey(sorted)) {
                var list = words.get(sorted);
                list.add(word);

                continue;
            }

            var list = new ArrayList<String>(32);
            list.add(word);

            words.put(sorted, list);
        }
    }

    private void quickSortRecursive(char[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int mask = 32;
        arr[right] |= mask;

        for (int j = left; j < right; ++j) {
            arr[j] |= mask;

            if (arr[j] < arr[right]) {
                char tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;

                ++i;
            }
        }

        char tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;

        quickSortRecursive(arr, left, i - 1);
        quickSortRecursive(arr, i + 1, right);
    }

    public String[] findCandidates(final String word) {
        char[] temp = word.toCharArray();
        quickSortRecursive(temp, 0, temp.length - 1);

        String key = new String(temp);

        ArrayList<String> list = words.get(key);

        if (list == null) {
            return new String[0];
        }

        String[] result = new String[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i).toLowerCase();
        }

        return result;
    }
}