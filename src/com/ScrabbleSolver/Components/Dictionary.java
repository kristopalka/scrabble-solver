package com.ScrabbleSolver.Components;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary // singleton
{
    private static Dictionary instance;
    private static List<String> dictionary;

    private Dictionary()
    {
        String patch = "C:\\Users\\krist\\Desktop\\ScrabbleSolver\\src\\com\\ScrabbleSolver\\BoardComponents\\dictionary_pl.txt";
        try (Stream<String> lines = Files.lines(Paths.get(patch)))
        {
            dictionary = lines.collect(Collectors.toList());
        }
        catch (Exception e)
        {
            System.err.println("Cannot load dictionary from path: " + patch);
            dictionary = new ArrayList<>();
        }
    }

    private static void ifNoInstanceCreate()
    {
        if (instance == null) instance = new Dictionary();
    }

    public static List<String> getAll()
    {
        ifNoInstanceCreate();
        return dictionary;
    }

    public static boolean isWord(String word)
    {
        ifNoInstanceCreate();
        return dictionary.contains(word);
    }

}
