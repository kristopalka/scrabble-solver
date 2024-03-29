package com.scrabble.backend.solving.scrabble;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.util.StopWatch;

import java.util.*;

@Slf4j
public class Dictionary {
    private final List<String> dictionary;
    private final Map<String, List<String>> requiredLettersToWordsMap = new HashMap<>(); // <required letters, list of possible words>
    private final List<char[]> sortedRequiredLetters = new ArrayList<>();
    private final HashMap<Integer, Integer> sizeToWordsIndex = new HashMap<>(); // <word size, index of first word with size>


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

            List<String> possibleWords = requiredLettersToWordsMap.get(requiredLetters);
            if (possibleWords == null) {
                possibleWords = new ArrayList<>();
                requiredLettersToWordsMap.put(requiredLetters, possibleWords);
                sortedRequiredLetters.add(requiredLetters.toCharArray());
            }
            possibleWords.add(word);
        }
    }

    private void processingSizesWordsIndexes() {
        for (int i = 1; i < sortedRequiredLetters.size(); i++) {
            if (sortedRequiredLetters.get(i).length > sortedRequiredLetters.get(i - 1).length) {
                sizeToWordsIndex.put(sortedRequiredLetters.get(i).length, i);
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
        List<String> possibleWords = requiredLettersToWordsMap.get(key);
        if (possibleWords != null) return possibleWords;
        else return new ArrayList<>();
    }

    public List<char[]> getAllRequiredLettersList() {
        return sortedRequiredLetters;
    }

    public int indexOfFirstWordWithLength(int length) {
        if (length == 0 || length == 1 || length == 2) return 0;
        if (length > 15) return sortedRequiredLetters.size();
        return sizeToWordsIndex.get(length);
    }

    public List<String> getWords() {
        return dictionary;
    }
}
