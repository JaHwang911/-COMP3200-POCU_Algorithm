package practice.app;

import practice.BST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        for (int i = 0; i < 50; ++i) {
            testInsert();
        }

        for (int i = 0; i < 50; ++i) {
            testDelete();
        }

        System.out.println("No Prob");
    }

    private static void testInsert() {
        System.out.println("##### Start insert test #####");
        Random random = new Random();
        final int SIZE = random.nextInt(1000 - 50) + 50;
        HashSet<Integer> numbers = new HashSet<>(SIZE);

        for (int i = 0; i < SIZE; ++i) {
            numbers.add(random.nextInt(1000 - 1) + 1);
        }

        BST bst = new BST();

        for (int num : numbers) {
            bst.insert(num);
        }

        for (int num : numbers) {
            assert (bst.search(num));
        }
    }

    private static void testDelete() {
        System.out.println("##### Start delete test #####");
        Random random = new Random();
        final int SIZE = random.nextInt(10 - 5) + 5;
        HashSet<Integer> numbers = new HashSet<>(SIZE);

        while (numbers.size() < SIZE) {
            numbers.add(random.nextInt(1000 - 1) + 1);
        }

        BST bst = new BST();

        for (int num : numbers) {
            bst.insert(num);
        }

        boolean bIsDelete;

        for (int num : numbers) {
            bIsDelete = bst.delete(num);

            if (!bIsDelete) {
                assert (bIsDelete);
            }
        }
    }
}
