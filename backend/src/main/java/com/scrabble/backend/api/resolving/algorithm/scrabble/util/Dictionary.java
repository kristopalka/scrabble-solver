package com.scrabble.backend.api.resolving.algorithm.scrabble.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
    private static ArrayList<String> dictionary;

    static {
        String path = "/home/krist/Projects/Scrabble-Solver/backend/src/main/resources/dictionaries/dictionary_pl.txt"; //todo replace absolute path

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            dictionary = (ArrayList<String>) lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Cannot load dictionary from path: " + path);
        }
    }


    public static ArrayList<String> getAllWords() {
        return dictionary;
    }

    public static boolean containsWord(String word) {
        return dictionary.contains(word);
    }
}
