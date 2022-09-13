package com.scrabble.backend.resolving.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;

import static com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings.getBonusAt;

public class PointsCalculator {
    public static int calculatePoints(Word word, char[][] board) { //todo add advanced points (based on board)
        int totalPoints = 0;
        int multiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            ScrabbleSettings.Bonus bonus;
            if (word.direction == Word.Direction.VERTICAL) bonus = getBonusAt(word.xBegin(), word.yBegin() + i);
            else bonus = getBonusAt(word.xBegin() + i, word.yBegin());


            int letterPoints = Alphabet.valueOfLetter(word.charAt(i));

            switch (bonus) {
                case EMPTY -> totalPoints += letterPoints;
                case DOUBLE_LETTER -> totalPoints += 2 * letterPoints;
                case TRIPLE_LETTER -> totalPoints += 3 * letterPoints;
                case DOUBLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 2;
                }
                case TRIPLE_WORD -> {
                    totalPoints += letterPoints;
                    multiplier *= 3;
                }
            }
        }
        totalPoints *= multiplier;
        return totalPoints;
    }
}
