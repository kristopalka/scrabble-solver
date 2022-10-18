package com.scrabble.backend.solving.scrabble.resources;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.util.StopWatch;

import java.util.*;

@Slf4j
public class Dictionary {
    private final List<String> dictionary;
    private final Map<String, List<String>> requiredLettersPossibleWordsMap = new HashMap<>(); // <required letters, list of possible words>
    private final List<char[]> sortedRequiredLetters = new FastList<>();
    private final HashMap<Integer, Integer> sizesWordsIndexes = new HashMap<>(); // <word size, index of first word with size>


    public Dictionary(String path) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        dictionary = FileResourceReader.read(path);
        processingRequiredLettersPossibleWordsMap();
        sortedRequiredLetters.sort(Comparator.comparingInt(array -> array.length));
        processingSizesWordsIndexes();


        stopWatch.stop();
        log.info("Loaded {} dictionary in {} [ms]", path, stopWatch.getTotalTimeMillis());
    }

    private void processingRequiredLettersPossibleWordsMap() {
        for (String word : dictionary) {
            String requiredLetters = sortString(word);

            List<String> possibleWords = requiredLettersPossibleWordsMap.get(requiredLetters);
            if (possibleWords == null) {
                possibleWords = new FastList<>();
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
        if (possibleWords != null) return possibleWords;
        else return new FastList<>();
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
