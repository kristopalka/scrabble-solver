package com.scrabble.backend.resolving.algorithm.settings;

import java.util.*;

public class DictionarySorted {
    private static final HashMap<String, ArrayList<String>> requiredLettersToWords; // <required letters, list of possible words>
    private static final List<char[]> requiredLetters;
    private static final HashMap<Integer, Integer> sizesWordsIndexes; // <word size, index of first word with size>

    static {
        System.out.println("Processing sorted dictionary...");
        requiredLettersToWords = new HashMap<>();
        for (String word : Dictionary.getDictionary()) {
            String requiredLetters = sortString(word);

            if (requiredLettersToWords.get(requiredLetters) == null)
                requiredLettersToWords.put(requiredLetters, new ArrayList<>() {{
                    add(word);
                }});
            else requiredLettersToWords.get(requiredLetters).add(word);
        }

        System.out.println("Required letters...");

        requiredLetters = requiredLettersToWords.keySet().stream()
                .sorted(Comparator.comparingInt(String::length))
                .map(String::toCharArray)
                .toList();

        System.out.println("Sizes words indexes...");

        sizesWordsIndexes = new HashMap<>();
        for (int i = 1; i < requiredLetters.size(); i++) {
            if (requiredLetters.get(i).length > requiredLetters.get(i - 1).length) {
                sizesWordsIndexes.put(requiredLetters.get(i).length, i);
            }
        }
    }


    public static String sortString(String input) {
        char[] array = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public static ArrayList<String> getWordsByRequiredLetters(String key) {
        ArrayList<String> words = requiredLettersToWords.get(key);
        return words != null ? words : new ArrayList<>();
    }

    public static List<char[]> getAllRequiredLettersList() {
        return requiredLetters;
    }

    public static int indexOfFirstWordWithLength(int length) {
        if (length == 0 || length == 1 || length == 2) return 0;
        if (length > 15) return requiredLetters.size();
        return sizesWordsIndexes.get(length);
    }
}
