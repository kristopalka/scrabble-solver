package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;

import java.util.List;

public class Solver {
    public static List<Word> getBestPointedWords(char[][] board, String holder, int number) {
        ScoreCalculator calculator = new ScoreCalculator(board);

        return WordsFinder.getAll(board, holder)
                .stream().parallel()
                .peek(word -> word.score = calculator.getScore(word))
                .sorted((w1, w2) -> Integer.compare(w2.score, w1.score))
                .limit(number)
                .toList();
    }

    public static Word getBestPointedWord(char[][] board, String holder) {
        List<Word> bestWords = getBestPointedWords(board, holder, 1);
        if (bestWords.size() == 1) return bestWords.get(0);
        return null;
    }


    public static List<Word> getLongestWords(char[][] board, String holder, int number) {
        return WordsFinder.getAll(board, holder)
                .stream().parallel()
                .sorted((w1, w2) -> Integer.compare(w2.length(), w1.length()))
                .limit(number)
                .toList();
    }

}
