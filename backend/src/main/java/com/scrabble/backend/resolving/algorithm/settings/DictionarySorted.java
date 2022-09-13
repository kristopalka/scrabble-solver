package com.scrabble.backend.resolving.algorithm.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class DictionarySorted {
    private static final HashMap<String, ArrayList<String>> sortedDictionary; // <required letters, list of possible words>
    private static final Set<String> requiredLettersSet;

    static {
        System.out.println("Processing sorted dictionary...");
        sortedDictionary = new HashMap<>();
        for (String word : Dictionary.getDictionary()) {
            String requiredLetters = sortString(word);

            if (sortedDictionary.get(requiredLetters) == null)
                sortedDictionary.put(requiredLetters, new ArrayList<>() {{
                    add(word);
                }});
            else sortedDictionary.get(requiredLetters).add(word);
        }

        requiredLettersSet = sortedDictionary.keySet();
    }


    public static String sortString(String input) {
        char[] array = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public static ArrayList<String> getWordsByRequiredLetters(String key) {
        ArrayList<String> words = sortedDictionary.get(key);
        return words != null ? words : new ArrayList<>() ;
    }

    public static Set<String> getAllRequiredLettersSet() {
        return requiredLettersSet;
    }
}
