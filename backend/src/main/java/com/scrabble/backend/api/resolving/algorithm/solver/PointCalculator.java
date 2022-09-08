package com.scrabble.backend.api.resolving.algorithm.solver;

import com.scrabble.backend.api.resolving.algorithm.scrabble.util.Alphabet;
import com.scrabble.backend.api.resolving.algorithm.scrabble.util.StandardScrabbleSettings;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;

import static com.scrabble.backend.api.resolving.algorithm.scrabble.util.StandardScrabbleSettings.getBonusAt;

public class PointCalculator {
    public static int calculatePoints(Word word, char[][] board) { //todo add advanced points (based on board)
        int totalPoints = 0;
        int multiplier = 1;

        for (int i = 0; i < word.getLength(); i++) {
            StandardScrabbleSettings.Bonus bonus;
            if (word.getDirection() == Word.Direction.VERTICAL) bonus = getBonusAt(word.getXBegin(), word.getYBegin() + i);
            else bonus = getBonusAt(word.getXBegin() + i, word.getYBegin());


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
