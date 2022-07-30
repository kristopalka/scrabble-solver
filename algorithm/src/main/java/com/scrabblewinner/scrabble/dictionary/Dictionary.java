package com.scrabblewinner.scrabble.dictionary;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary { // singleton
    private static Dictionary instance;
    private static ArrayList<String> dictionary;

    public static void initialize() {
        ifNoInstanceCreate();
    }

    private static void ifNoInstanceCreate() {
        if (instance == null) instance = new Dictionary();
    }

    private Dictionary() {
        loadWordsFromDictionary(".\\src\\main\\resources\\dictionary_pl.txt");
    }

    private void loadWordsFromDictionary(String patch) {
        try (Stream<String> lines = Files.lines(Paths.get(patch))) {
            dictionary = (ArrayList<String>) lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Cannot load dictionary from path: " + patch);
            dictionary = new ArrayList<>();
        }
    }

    public static ArrayList<String> getAllWords() {
        ifNoInstanceCreate();
        return dictionary;
    }

    public static boolean containsWord(String word) {
        ifNoInstanceCreate();
        return dictionary.contains(word);
    }
}
