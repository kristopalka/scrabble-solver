package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.settings.DictionarySorted;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DictionaryFinder {
    public static List<String> getPotentialWords(String blockLetters, String holderLetters) {
        char[] lettersToUse = mergeAndSort(blockLetters, holderLetters);
        List<String> possibleWords = new ArrayList<>();

        List<char[]> requiredLettersList = DictionarySorted.getAllRequiredLettersList();
        int startIndex = DictionarySorted.indexOfFirstWordWithLength(blockLetters.length());
        int endIndex = DictionarySorted.indexOfFirstWordWithLength(lettersToUse.length + 1);

        for (int i = startIndex; i < endIndex; i++) {
            if (isSubsetOf(requiredLettersList.get(i), lettersToUse)) {
                possibleWords.addAll(DictionarySorted.getWordsByRequiredLetters(new String(requiredLettersList.get(i))));
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
