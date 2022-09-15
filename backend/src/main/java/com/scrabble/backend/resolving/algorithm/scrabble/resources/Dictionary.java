package com.scrabble.backend.resolving.algorithm.scrabble.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.*;

@Slf4j
public class Dictionary {
    private final List<String> dictionary;
    private final HashMap<String, List<String>> requiredLettersPossibleWordsMap = new HashMap<>(); // <required letters, list of possible words>
    private final List<char[]> sortedRequiredLetters = new ArrayList<>();
    private final HashMap<Integer, Integer> sizesWordsIndexes = new HashMap<>(); // <word size, index of first word with size>


    public Dictionary(String lang) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        dictionary = FileResourceReader.read(lang + "_dictionary.txt");
        processingRequiredLettersPossibleWordsMap();
        sortedRequiredLetters.sort(Comparator.comparingInt(array -> array.length));
        processingSizesWordsIndexes();


        stopWatch.stop();
        log.info("Loaded {} dictionary in {} [ms]", lang, stopWatch.getTotalTimeMillis());
    }

    private void processingRequiredLettersPossibleWordsMap() {
        for (String word : dictionary) {
            String requiredLetters = sortString(word);

            List<String> possibleWords = requiredLettersPossibleWordsMap.get(requiredLetters);
            if (possibleWords == null) {
                possibleWords = new ArrayList<>();
                requiredLettersPossibleWordsMap.put(requiredLetters, possibleWords);
                sortedRequiredLetters.add(requiredLetters.toCharArray());
            }
            possibleWords.add(word);
        }
    }

    private void processingSizesWordsIndexes() {
        for (int i = 1; i < sortedRequiredLetters.size(); i++) {
            if (sortedRequiredLetters.get(i).length > sortedRequiredLetters.get(i - 1).length) {
                sizesWordsIndexes.put(sortedRequiredLetters.get(i).length, i);
            }
        }
    }


    public static String sortString(String input) {
        char[] array = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public boolean containsWord(String word) {
        return dictionary.contains(word);
    }

    public List<String> getWordsByRequiredLetters(String key) {
        List<String> possibleWords = requiredLettersPossibleWordsMap.get(key);
        return possibleWords != null ? possibleWords : new ArrayList<>();
    }

    public List<char[]> getAllRequiredLettersList() {
        return sortedRequiredLetters;
    }

    public int indexOfFirstWordWithLength(int length) {
        if (length == 0 || length == 1 || length == 2) return 0;
        if (length > 15) return sortedRequiredLetters.size();
        return sizesWordsIndexes.get(length);
    }

    public List<String> getWords() {
        return dictionary;
    }
}
