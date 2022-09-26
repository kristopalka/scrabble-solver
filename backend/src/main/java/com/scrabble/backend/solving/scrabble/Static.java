package com.scrabble.backend.solving.scrabble;

import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.scrabble.resources.Dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Static {
    public static final int holderSize = 7;
    public static final int boardSize = 15;
    public static final List<String> supportedLanguages = List.of(new String[]{"pl", "en"});

    private static final Map<String, Alphabet> loadedAlphabets = new HashMap<>();
    private static final Map<String, Dictionary> loadedDictionaries = new HashMap<>();


    public static Alphabet getAlphabet(String lang) {
        throwIfUnsupported(lang);
        return loadedAlphabets.computeIfAbsent(lang, a -> new Alphabet(lang));
    }

    public static Dictionary getDictionary(String lang) {
        throwIfUnsupported(lang);
        return loadedDictionaries.computeIfAbsent(lang, d -> new Dictionary(lang));
    }

    private static void throwIfUnsupported(String lang) {
        if (!supportedLanguages.contains(lang)) throw new IllegalArgumentException("Unsupported language " + lang);
    }

}
