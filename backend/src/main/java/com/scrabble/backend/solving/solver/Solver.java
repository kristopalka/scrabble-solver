package com.scrabble.backend.solving.solver;

import com.scrabble.backend.solving.scrabble.ScoreCalculator;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.solver.finder.BoardFinder;
import com.scrabble.backend.solving.solver.finder.Word;

import java.util.List;

public class Solver {
    public static List<Word> getWordsByBestScore(char[][] board, String holder, String lang, int number) {
        ScoreCalculator calculator = new ScoreCalculator(board, lang);

        return BoardFinder.getAll(board, holder, lang)
                .stream().parallel()
                .peek(word -> word.usedLetters = usedLetters(board, word))
                .peek(word -> word.score = calculator.getScore(word))
                .sorted((w1, w2) -> Integer.compare(w2.score, w1.score))
                .limit(number).toList();
    }

    public static List<Word> getWordsByLength(char[][] board, String holder, String lang, int number) {
        ScoreCalculator calculator = new ScoreCalculator(board, lang);

        return BoardFinder.getAll(board, holder, lang)
                .stream().parallel()
                .peek(word -> word.usedLetters = usedLetters(board, word))
                .peek(word -> word.score = calculator.getScore(word))
                .sorted((w1, w2) -> Integer.compare(w2.length(), w1.length()))
                .limit(number).toList();
    }

    public static String usedLetters(char[][] board, Word word) {
        StringBuilder usedLetters = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char charAtBoard = board[word.xBegin()][i + word.yBegin()];
            if (charAtBoard == Alphabet.emptySymbol) usedLetters.append(word.charAt(i));
        }
        return usedLetters.toString();
    }

}
