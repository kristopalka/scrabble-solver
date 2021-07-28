package com.scrabblewinner.solver.wordsfinder.possibleselector;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.dictionary.SortedDictionary;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class PossibleWordsFinderInDict {
    public static ArrayList<String> getWordsPossibleToArrangeFromLetters(char[] column, char[] holder) {
        char[] lettersToUse = getLettersToUse(column, holder);
        Arrays.sort(lettersToUse);

        ArrayList<String> wordsKeys = SortedDictionary.getAllKeys();
        ArrayList<String> wordsKeysAbleToAlign = new ArrayList<>();

        for (String wordKey : wordsKeys) {
            if (isEnoughLettersToAlignWord(wordKey, lettersToUse)) wordsKeysAbleToAlign.add(wordKey);
        }


        return getAllWordsFromKeysList(wordsKeysAbleToAlign);
    }

    private static ArrayList<String> getAllWordsFromKeysList(ArrayList<String> wordsKeysAbleToAlign) {
        ArrayList<String> words = new ArrayList<>();
        for (String wordKey : wordsKeysAbleToAlign) {
            words.addAll(SortedDictionary.getWordsByKey(wordKey));
        }

        return words;
    }

    protected static char[] getLettersToUse(char[] column, char[] holder) {
        int number = countNumberOfLetters(column, holder);
        char lettersToUse[] = new char[number];
        int i = 0;

        for (char field : column) {
            if (field != Alphabet.getEmptySymbol()) lettersToUse[i++] = field;
        }

        for (char letters : holder) lettersToUse[i++] = letters;
        return lettersToUse;
    }

    private static int countNumberOfLetters(char[] column, char[] holder) {
        int number = 0;
        number += holder.length;

        for (char field : column) {
            if (field != Alphabet.getEmptySymbol()) number++;
        }
        return number;
    }


    protected static boolean isEnoughLettersToAlignWord(String wordKey, char[] letters) {
        int lettersPointer = 0;
        int lettersLength = letters.length;
        int wordPointer = 0;
        int wordLength = wordKey.length();
        if (wordLength == 0) throw new InvalidParameterException("Word cant be empty");


        while (wordPointer < wordLength) {
            if(lettersPointer >= lettersLength) return false;
            while (wordKey.charAt(wordPointer) > letters[lettersPointer]) {
                lettersPointer++;
                if (lettersPointer >= lettersLength) return false;
            }
            if (wordKey.charAt(wordPointer) == letters[lettersPointer]) {
                wordPointer++;
                lettersPointer++;
            } else return false;
        }
        return true;
    }
}
