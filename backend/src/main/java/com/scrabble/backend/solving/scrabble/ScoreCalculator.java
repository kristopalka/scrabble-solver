package com.scrabble.backend.solving.scrabble;

import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.scrabble.resources.Bonuses;
import com.scrabble.backend.solving.solver.finder.Word;

import java.awt.*;
import java.util.Arrays;

import static com.scrabble.backend.solving.scrabble.ScrabbleResources.holderSize;
import static com.scrabble.backend.solving.scrabble.resources.Bonuses.getBonusAt;

public class ScoreCalculator {
    private static final int bonusForUsingAllLetters = 50;
    protected final char[][] board;
    protected final Alphabet alphabet;

    public ScoreCalculator(char[][] board, String lang) {
        this.board = board;
        this.alphabet = ScrabbleResources.getAlphabet(lang);
    }

    public int getScore(Word word) {
        int totalScore = getScoreForWord(word);
        if (word.usedLetters != null && word.usedLetters.length() == holderSize) totalScore += bonusForUsingAllLetters;

        for (Word additionalWord : word.additionalWords) totalScore += getScoreForWord(additionalWord);

        return totalScore;
    }

    private int getScoreForWord(Word word) {
        int totalScore = 0;
        int multiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            Point point = word.pointAt(i);

            if (boardAt(point) != Alphabet.emptySymbol && word.charAt(i) != boardAt(point))
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
        if (boardAt(point) == Alphabet.emptySymbol) {
            return getBonusAt(point);
        }
        return Bonuses.Bonus.EMPTY;
    }
}
