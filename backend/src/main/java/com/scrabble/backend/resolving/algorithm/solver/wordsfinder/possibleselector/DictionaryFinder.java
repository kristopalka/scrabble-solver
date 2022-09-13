package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.possibleselector;

import com.scrabble.backend.resolving.algorithm.settings.DictionarySorted;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.Utils.mergeArrays;
import static com.scrabble.backend.resolving.algorithm.settings.Alphabet.extractCorrectLetters;

public class DictionaryFinder {
    public static ArrayList<String> getPotentialWordsForColumn(char[] column, char[] holderLetters) {
        char[] columnLetters = extractCorrectLetters(column);
        if(columnLetters.length == 0 || columnLetters.length == column.length) return new ArrayList<>();

        char[] lettersToUse = mergeArrays(holderLetters, columnLetters);
        Arrays.sort(lettersToUse);

        List<String> list = DictionarySorted.getAllRequiredLettersSet().stream()
                .filter(requiredLetters -> isSubsetOf(requiredLetters, lettersToUse))
                .map(DictionarySorted::getWordsByRequiredLetters)
                .flatMap(List::stream)
                .toList();

        return new ArrayList<>(list);
    }

    protected static boolean isSubsetOf(String requiredLetters, char[] lettersToUse) {
        int lettersPointer = 0;
        int wordPointer = 0;
        if (requiredLetters.length() == 0) throw new InvalidParameterException("Word can't be empty");


        while (wordPointer < requiredLetters.length()) {
            if (lettersPointer >= lettersToUse.length) return false;
            while (requiredLetters.charAt(wordPointer) > lettersToUse[lettersPointer]) {
                lettersPointer++;
                if (lettersPointer >= lettersToUse.length) return false;
            }
            if (requiredLetters.charAt(wordPointer) == lettersToUse[lettersPointer]) {
                wordPointer++;
                lettersPointer++;
            } else return false;
        }
        return true;
    }
}
