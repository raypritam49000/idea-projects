package com.problem.sloving.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public int[] findTwoNumber(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            int num1 = array[i];

            for (int j = i + 1; j < array.length; j++) {
                int num2 = array[j];

                if (num1 + num2 == target) {
                    return new int[]{num1, num2};
                }

            }
        }
        return null;
    }

    public int[] findTwoNumberMap(int[] array, int target) {
        // Create a hash map to store the numbers and their indices
        Map<Integer, Integer> numIndices = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int num1 = array[i];
            int complement = target - num1;

            // Check if the complement is in the map
            if (numIndices.containsKey(complement)) {
                // If found, return the pair of numbers
                return new int[]{complement, num1};
            }

            // If not found, add the current number and its index to the map
            numIndices.put(num1, i);
        }

        // If no pair is found, return null
        return null;
    }

    public int[] ascendingOrder(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] <= array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public int[] descendingOrder(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] >= array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 7, 11, 5, 1, 2, 20, 11, 6, 3};
        int target = 9;
        System.out.println(Arrays.toString(new Application().findTwoNumber(array, target)));
        System.out.println(Arrays.toString(new Application().findTwoNumberMap(array, target)));
        System.out.println(Arrays.toString(new Application().ascendingOrder(array)));
        System.out.println(Arrays.toString(new Application().descendingOrder(array)));
    }
}
