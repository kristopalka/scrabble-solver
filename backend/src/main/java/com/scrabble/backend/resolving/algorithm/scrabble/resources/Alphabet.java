package com.scrabble.backend.resolving.algorithm.scrabble.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Alphabet {
    public static final char emptySymbol = ' ';

    private final Map<Character, Integer> letterValueMap = new HashMap<>();
    private final List<Character> letters = new ArrayList<>();


    public Alphabet(String lang) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (String line : FileResourceReader.read(lang + "_letters.txt")) {
            Character letter = line.charAt(0);
            Integer value = Integer.parseInt(line.substring(2));

            letterValueMap.put(letter, value);
            letters.add(letter);
        }

        stopWatch.stop();
        log.info("Loaded {} alphabet in {} [ms]", lang, stopWatch.getTotalTimeMillis());
    }

    public int valueOfLetter(char letter) {
        if (!isLetter(letter)) throw new IllegalArgumentException("Parameter is not a letter: " + letter);
        return letterValueMap.get(letter);
    }

    public boolean isLetterOrEmptySymbol(char symbol) {
        return isEmptySymbol(symbol) || isLetter(symbol);
    }

    public boolean isLetter(char symbol) {
        return letters.contains(symbol);
    }

    public boolean isEmptySymbol(char symbol) {
        return symbol == emptySymbol;
    }

    public List<Character> getLetters() {
        return letters;
    }
}
