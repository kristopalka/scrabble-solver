package com.scrabble.backend.api.resolving.algorithm.scrabble.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortedDictionary {
    private static final HashMap<String, ArrayList<String>> sortedDictionary;
    private static final ArrayList<String> keysList;

    static {
        sortedDictionary = new HashMap<>();
        for (String word : Dictionary.getAllWords()) {
            String sortedWord = sortString(word);

            if (sortedDictionary.get(sortedWord) == null)
                sortedDictionary.put(sortedWord, new ArrayList<>() {{
                    add(word);
                }});
            else sortedDictionary.get(sortedWord).add(word);
        }

        keysList = new ArrayList<>(sortedDictionary.keySet());
    }


    public static String sortString(String input) {
        char[] array = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public static ArrayList<String> getWordsByKey(String key) {
        ArrayList<String> words = sortedDictionary.get(key);
        return words != null ? words : new ArrayList<>() ;
    }

    public static ArrayList<String> getAllKeys() {
        return keysList;
    }
}
