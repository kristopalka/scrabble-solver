package com.scrabble.backend.solving.scrabble;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourceReader {
    public static List<String> read(String path) {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Can not load file: " + path);
        }
    }
}
