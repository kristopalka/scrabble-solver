package com.scrabble.backend.resolving.algorithm.scrabble.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileResourceReader {
    private static final String resources = "/home/krist/Projects/Scrabble-Solver/backend/src/main/resources/scrabble/";

    public static List<String> read(String filename) {
        String file = resources + filename;

        try (Scanner sc = new Scanner(new File(file))) {
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            return lines;
        } catch (Exception e) {
            System.out.println("Can not load file: " + file);
            return new ArrayList<>();
        }
    }
}
