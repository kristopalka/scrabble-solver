package com.scrabble.backend.solving.scrabble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrabbleResources {
    public static final int rackSize = 7;
    public static final int boardSize = 15;
    public static final List<String> supportedLanguages = List.of(new String[]{"en", "pl"});

    private static final Map<String, Alphabet> loadedAlphabets = new HashMap<>();
    private static final Map<String, Dictionary> loadedDictionaries = new HashMap<>();

    public static final Bonuses bonuses = new Bonuses();

    public static String path = "./scrabble_resources/";


    public static Alphabet getAlphabet(String lang) {
        throwIfUnsupported(lang);
        return loadedAlphabets.computeIfAbsent(lang, a -> new Alphabet(path + lang + "_letters.txt"));
    }

    public static Dictionary getDictionary(String lang) {
        throwIfUnsupported(lang);
        return loadedDictionaries.computeIfAbsent(lang, d -> new Dictionary(path + lang + "_dictionary.txt"));
    }

    private static void throwIfUnsupported(String lang) {
        if (!supportedLanguages.contains(lang)) throw new IllegalArgumentException("Unsupported language " + lang);
    }

}
