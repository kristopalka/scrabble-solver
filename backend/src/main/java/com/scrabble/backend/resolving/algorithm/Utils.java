package com.scrabble.backend.resolving.algorithm;

public class Utils {
    public static char[] mergeArrays(char[] arrayA, char[] arrayB) {
        char[] mergedArray = new char[arrayA.length + arrayB.length];
        int i = 0, j = 0;

        while (i < arrayA.length) {
            mergedArray[i] = arrayA[i];
            i++;
        }

        while (j < arrayB.length) {
            mergedArray[i + j] = arrayB[j];
            j++;
        }

        return mergedArray;
    }
}
