package com.scrabble.backend.solving.scrabble.resources;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourceReader {
    private static final String resources = "/home/krist/Projects/Scrabble-Solver/backend/src/main/resources/scrabble/";

    public static List<String> read(String filename) {
        String path = resources + filename;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Can not load file: " + path);
            return new ArrayList<>();
        }
    }
}
