package com.scrabble.backend.resolving.algorithm.settings;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
    private static ArrayList<String> dictionary;

    static {
        System.out.println("Processing dictionary...");
        String path = "/home/krist/Projects/Scrabble-Solver/backend/src/main/resources/dictionaries/pl_dictionary.txt"; //todo replace absolute path

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            dictionary = (ArrayList<String>) lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Cannot load dictionary from path: " + path);
        }
    }


    public static ArrayList<String> getDictionary() {
        return dictionary;
    }

    public static boolean containsWord(String word) {
        return dictionary.contains(word);
    }
}
