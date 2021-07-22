package com.scrabblewinner.scrabble;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary // singleton
{
    private static Dictionary instance;
    private static ArrayList<String> dictionary;

    private Dictionary() {
        String patch = "P:\\Projekty\\ScrabbleSolver\\resources\\dictionary_pl.txt";
        try (Stream<String> lines = Files.lines(Paths.get(patch))) {
            dictionary = (ArrayList<String>) lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Cannot load dictionary from path: " + patch);
            dictionary = new ArrayList<>();
        }
    }

    private static void ifNoInstanceCreate() {
        if (instance == null) instance = new Dictionary();
    }

    public static ArrayList<String> getAllWords() {
        ifNoInstanceCreate();
        return dictionary;
    }

    public static boolean containsWord(String word) {
        ifNoInstanceCreate();
        return dictionary.contains(word);
    }

    public static HashMap<String, String> generateHashMap() {
        ifNoInstanceCreate();
        HashMap<String, String> map = new HashMap<>();

        for (String word : dictionary) {
            String sortedWord = sortString(word);
            map.put(sortedWord, word);
        }
        return map;
    }


    private static String sortString(String input) {
        char array[] = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

}
