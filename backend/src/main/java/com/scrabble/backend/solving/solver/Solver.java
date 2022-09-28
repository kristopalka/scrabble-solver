package com.scrabble.backend.solving.solver;

import com.scrabble.backend.solving.scrabble.ScoreCalculator;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.solver.finder.BoardFinder;
import com.scrabble.backend.solving.solver.finder.Word;

import java.util.Comparator;
import java.util.List;

public class Solver {
    public static List<Word> getWords(char[][] board, String holder, String lang, int number, String mode) {
        ScoreCalculator calculator = new ScoreCalculator(board, lang);

        Comparator<Word> comparator = getComparator(mode);

        return BoardFinder.getAll(board, holder, lang)
                .stream().parallel()
                .peek(word -> word.usedLetters = usedLetters(board, word))
                .peek(word -> word.score = calculator.getScore(word))
                .sorted(comparator)
                .limit(number).toList();
    }

    private static Comparator<Word> getComparator(String mode) {
        return switch (mode) {
            case "score" -> Comparator.comparingInt(Word::getScore).reversed();
            case "length" -> Comparator.comparingInt(Word::length).thenComparingInt(Word::getScore).reversed();
            default -> throw new IllegalStateException("Unexpected mode: " + mode);
        };
    }


    private static String usedLetters(char[][] board, Word word) {
        StringBuilder usedLetters = new StringBuilder();

        if (word.direction == Word.Direction.VERTICAL) {
            for (int y = 0; y < word.length(); y++) {
                char charAtBoard = board[word.xBegin()][word.yBegin() + y];
                if (charAtBoard == Alphabet.emptySymbol) usedLetters.append(word.charAt(y));
            }
        } else {
            for (int x = 0; x < word.length(); x++) {
                char charAtBoard = board[x + word.xBegin()][word.yBegin()];
                if (charAtBoard == Alphabet.emptySymbol) usedLetters.append(word.charAt(x));
            }
        }
        return usedLetters.toString();
    }

}
