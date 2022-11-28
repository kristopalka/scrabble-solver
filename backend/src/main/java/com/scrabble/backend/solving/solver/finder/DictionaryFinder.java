package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.resources.Dictionary;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class DictionaryFinder {
    public static List<String> getPotentialWords(String columnLetters, String rackLetters, Dictionary dictionary, int minBlockLength) {
        char[] lettersToUse = mergeAndSort(columnLetters, rackLetters);

        List<char[]> requiredLettersList = dictionary.getAllRequiredLettersList();
        int startIndex = dictionary.indexOfFirstWordWithLength(minBlockLength + 1);
        int endIndex = dictionary.indexOfFirstWordWithLength(lettersToUse.length + 1);

        return IntStream.range(startIndex, endIndex)
                .parallel()
                .mapToObj(i -> isSubsetOf(requiredLettersList.get(i), lettersToUse)
                        ? dictionary.getWordsByRequiredLetters(new String(requiredLettersList.get(i)))
                        : new FastList<String>())
                .flatMap(List::stream)
                .collect(Collectors.toList());

//        List<String> possibleWords = new FastList<>();
//        for (int i = startIndex; i < endIndex; i++) {
//            if (isSubsetOf(requiredLettersList.get(i), lettersToUse)) {
//                possibleWords.addAll(dictionary.getWordsByRequiredLetters(new String(requiredLettersList.get(i))));
//            }
//        }
//        return possibleWords;
    }

    protected static char[] mergeAndSort(String a, String b) {
        char[] out = Strings.concat(a, b).toCharArray();
        Arrays.sort(out);
        return out;
    }

    protected static boolean isSubsetOf(char[] A, char[] B) {
        int pointerB = 0;
        int pointerA = 0;

        while (pointerA < A.length) {
            if (pointerB >= B.length) return false;
            while (A[pointerA] > B[pointerB]) {
                pointerB++;
                if (pointerB >= B.length) return false;
            }
            if (A[pointerA] == B[pointerB]) {
                pointerA++;
                pointerB++;
            } else return false;
        }
        return true;
    }

}
