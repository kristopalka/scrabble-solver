package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.scrabble.backend.resolving.algorithm.solver.PointsCalculator.calculatePoints;

public class Solver {
    public static List<Word> getBestWords(char[][] board, char[] holder, int number) {
        ArrayList<Word> possibleWords = WordsFinder.getAllPossibleWords(board, holder);
        if (possibleWords.size() == 0) throw new RuntimeException("Not found words to add");

        return getNBestPointed(possibleWords, board, number);
    }

    public static Word getBestWord(char[][] board, char[] holder) {
        return getBestWords(board, holder, 1).get(0);
    }

    public static List<Word> getNBestPointed(ArrayList<Word> words, char[][] board, int number) {
        List<Pair<Word, Integer>> wordsWithValues = new ArrayList<>();

        for(Word word : words) {
            Integer points = calculatePoints(word, board);
            wordsWithValues.add(new Pair<>(word, points));
        }

        return wordsWithValues.stream()
                .sorted(Comparator.comparing(Pair::getValue1))
                .limit(number)
                .map(Pair::getValue0)
                .toList();
    }

}
