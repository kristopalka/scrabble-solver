package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.scrabble.ScoreCalculator;
import com.scrabble.backend.resolving.algorithm.solver.finder.BoardFinder;
import com.scrabble.backend.resolving.algorithm.solver.finder.Word;

import java.util.List;

public class Solver {
    public static List<Word> getWordsByBestScore(char[][] board, String holder, String lang, int number) {
        ScoreCalculator calculator = new ScoreCalculator(board, lang);

        return BoardFinder.getAll(board, holder, lang)
                .stream().parallel()
                .peek(word -> word.score = calculator.getScore(word))
                .sorted((w1, w2) -> Integer.compare(w2.score, w1.score))
                .limit(number).toList();
    }

    public static List<Word> getWordsByLength(char[][] board, String holder, String lang, int number) {
        return BoardFinder.getAll(board, holder, lang)
                .stream().parallel()
                .sorted((w1, w2) -> Integer.compare(w2.length(), w1.length()))
                .limit(number).toList();
    }

}
