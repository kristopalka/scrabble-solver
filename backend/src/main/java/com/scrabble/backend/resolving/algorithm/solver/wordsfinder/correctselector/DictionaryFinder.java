package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.resolving.algorithm.settings.DictionarySorted;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.settings.DictionarySorted.sortString;


public class DictionaryFinder {
    public static ArrayList<String> getPotentialWords(String columnLetters, String holderLetters) {
        String lettersToUse = sortString(holderLetters + columnLetters);

        List<String> list = DictionarySorted.getAllRequiredLettersSet().stream()
                .filter(requiredLetters -> isSubsetOf(requiredLetters, lettersToUse))
                .map(DictionarySorted::getWordsByRequiredLetters)
                .flatMap(List::stream)
                .toList();

        return new ArrayList<>(list);
    }

    protected static boolean isSubsetOf(String requiredLetters, String lettersToUse) {
        int lettersPointer = 0;
        int wordPointer = 0;
        if (requiredLetters.length() == 0) throw new InvalidParameterException("Word can't be empty");


        while (wordPointer < requiredLetters.length()) {
            if (lettersPointer >= lettersToUse.length()) return false;
            while (requiredLetters.charAt(wordPointer) > lettersToUse.charAt(lettersPointer)) {
                lettersPointer++;
                if (lettersPointer >= lettersToUse.length()) return false;
            }
            if (requiredLetters.charAt(wordPointer) == lettersToUse.charAt(lettersPointer)) {
                wordPointer++;
                lettersPointer++;
            } else return false;
        }
        return true;
    }
}
