package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.resources.Dictionary;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.Arrays;
import java.util.List;


public class DictionaryFinder {
    public static List<String> getPotentialWords(String blockLetters, String holderLetters, Dictionary dictionary) {
        char[] lettersToUse = mergeAndSort(blockLetters, holderLetters);
        List<String> possibleWords = new FastList<>();

        List<char[]> requiredLettersList = dictionary.getAllRequiredLettersList();
        int startIndex = dictionary.indexOfFirstWordWithLength(blockLetters.length());
        int endIndex = dictionary.indexOfFirstWordWithLength(lettersToUse.length + 1);

        for (int i = startIndex; i < endIndex; i++) {
            if (isSubsetOf(requiredLettersList.get(i), lettersToUse)) {
                possibleWords.addAll(dictionary.getWordsByRequiredLetters(new String(requiredLettersList.get(i))));
            }
        }
        return possibleWords;
    }

    protected static char[] mergeAndSort(String a, String b) {
        char[] out = Strings.concat(a, b).toCharArray();
        Arrays.sort(out);
        return out;
    }

    protected static boolean isSubsetOf(char[] requiredLetters, char[] ownedLetters) {
        int ownedPointer = 0;
        int requiredPointer = 0;

        while (requiredPointer < requiredLetters.length) {
            if (ownedPointer >= ownedLetters.length) return false;
            while (requiredLetters[requiredPointer] > ownedLetters[ownedPointer]) {
                ownedPointer++;
                if (ownedPointer >= ownedLetters.length) return false;
            }
            if (requiredLetters[requiredPointer] == ownedLetters[ownedPointer]) {
                requiredPointer++;
                ownedPointer++;
            } else return false;
        }
        return true;
    }

}
