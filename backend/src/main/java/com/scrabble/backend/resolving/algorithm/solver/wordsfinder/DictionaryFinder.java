package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.settings.DictionarySorted;
import org.apache.logging.log4j.util.Strings;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.settings.DictionarySorted.sortString;


public class DictionaryFinder {
    public static List<String> getPotentialWords(String blockLetters, String holderLetters) {
        char[] lettersToUse = mergeAndSort(blockLetters, holderLetters);

        List<String> possibleWords = new ArrayList<>();
        for(char[] requiredLetters : DictionarySorted.getAllRequiredLettersSet()){
            if(requiredLetters.length > lettersToUse.length) break;
            if(isSubsetOf(requiredLetters, lettersToUse)){
                possibleWords.addAll(DictionarySorted.getWordsByRequiredLetters(new String(requiredLetters)));
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
