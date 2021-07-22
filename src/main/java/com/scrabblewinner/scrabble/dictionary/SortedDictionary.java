package com.scrabblewinner.scrabble.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortedDictionary {  // singleton
    private static SortedDictionary instance;
    private static HashMap<String, ArrayList<String>> sortedDictionary;

    private static void ifNoInstanceCreate() {
        if (instance == null) instance = new SortedDictionary();
    }

    private SortedDictionary() {
        initializeSortedDictionary();
    }

    private static void initializeSortedDictionary() {
        sortedDictionary = new HashMap<>();
        for (String word : Dictionary.getAllWords()) {
            String sortedWord = sortString(word);

            if (sortedDictionary.get(sortedWord) == null)
                sortedDictionary.put(sortedWord, new ArrayList<String>() {{
                    add(word);
                }});
            else sortedDictionary.get(sortedWord).add(word);
        }
    }

    public static String sortString(String input) {
        char array[] = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public static ArrayList<String> get(String key) {
        ifNoInstanceCreate();
        ArrayList<String> words = sortedDictionary.get(key);
        return words != null ? words : new ArrayList<>() ;
    }
}
