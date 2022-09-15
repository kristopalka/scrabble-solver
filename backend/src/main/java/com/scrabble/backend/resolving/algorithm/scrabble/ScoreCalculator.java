package com.scrabble.backend.resolving.algorithm.scrabble;

import com.scrabble.backend.resolving.algorithm.scrabble.resources.Alphabet;
import com.scrabble.backend.resolving.algorithm.scrabble.resources.Bonuses;
import com.scrabble.backend.resolving.algorithm.solver.finder.Word;

import java.awt.*;
import java.util.Arrays;

import static com.scrabble.backend.resolving.algorithm.scrabble.resources.Alphabet.emptySymbol;
import static com.scrabble.backend.resolving.algorithm.scrabble.resources.Bonuses.getBonusAt;

public class ScoreCalculator {
    protected final char[][] board;
    protected final Alphabet alphabet;

    public ScoreCalculator(char[][] board, String lang) {
        this.board = board;
        this.alphabet = Static.getAlphabet(lang);
    }

    public int getScore(Word word) {
        int totalScore = getScoreForWord(word);

        for (Word additionalWord : word.additionalWords) totalScore += getScoreForWord(additionalWord);
        return totalScore;
    }

    private int getScoreForWord(Word word) {
        int totalScore = 0;
        int multiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            Point point = word.pointAt(i);

            if (boardAt(point) != emptySymbol && word.charAt(i) != boardAt(point))
                throw new RuntimeException(String.format("Word %s dont match board %s", word, Arrays.deepToString(board)));

            int score = alphabet.valueOfLetter(word.charAt(i));
            switch (bonusIfBoardEmpty(point)) {
                case EMPTY -> totalScore += score;
                case DOUBLE_LETTER -> totalScore += 2 * score;
                case TRIPLE_LETTER -> totalScore += 3 * score;
                case DOUBLE_WORD -> {
                    totalScore += score;
                    multiplier *= 2;
                }
                case TRIPLE_WORD -> {
                    totalScore += score;
                    multiplier *= 3;
                }
            }
        }
        totalScore *= multiplier;
        return totalScore;
    }

    private char boardAt(Point point) {
        return board[point.x][point.y];
    }

    private Bonuses.Bonus bonusIfBoardEmpty(Point point) {
        if (boardAt(point) == emptySymbol) {
            return getBonusAt(point);
        }
        return Bonuses.Bonus.EMPTY;
    }
}
