package com.scrabble.backend.resolving.algorithm.settings;

import java.util.*;

public class DictionarySorted {
    private static final HashMap<String, ArrayList<String>> requiredLettersToWords; // <required letters, list of possible words>
    private static final List<char[]> requiredLetters;

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

        requiredLetters = requiredLettersToWords.keySet().stream().parallel()
                .sorted(Comparator.comparingInt(String::length))
                .map(String::toCharArray)
                .toList();
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

    public static List<char[]> getAllRequiredLettersSet() {
        return requiredLetters;
    }
}
