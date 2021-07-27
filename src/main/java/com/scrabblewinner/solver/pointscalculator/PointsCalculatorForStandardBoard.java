package com.scrabblewinner.solver.pointscalculator;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Bonus;
import com.scrabblewinner.scrabble.board.components.Field;
import com.scrabblewinner.scrabble.board.components.Word;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;

import static com.scrabblewinner.scrabble.board.components.Direction.VERTICAL;


public class PointsCalculatorForStandardBoard {
    private static Board board = new StandardBoard();

    public static Word getBest(ArrayList<Word> words) {
        throw new NotImplementedException("Not implemented yet");
    }


    public static HashMap<Word, Integer> getWordsWithPoints(ArrayList<Word> words) {
        throw new NotImplementedException("Not implemented yet");
    }

    public static Integer getPointsForWord(Word word) {
        int totalPoints = 0;
        int multiplier = 1;

        for (int i = 0; i < word.getLength(); i++) {
            Bonus bonus;
            if(word.direction == VERTICAL) bonus = board.getField(word.xStart, word.yStart + i).getBonus();
            else bonus = board.getField(word.xStart + i, word.yStart).getBonus();


            int letterPoints = Alphabet.valueOfLetter(word.charAt(i));

            switch (bonus) {
                case EMPTY:
                    totalPoints += letterPoints;
                    break;
                case DOUBLE_LETTER:
                    totalPoints += 2 * letterPoints;
                    break;
                case TRIPLE_LETTER:
                    totalPoints += 3 * letterPoints;
                    break;
                case DOUBLE_WORD:
                    totalPoints += letterPoints;
                    multiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    totalPoints += letterPoints;
                    multiplier *= 3;
                    break;
            }
        }

        totalPoints *= multiplier;

        return totalPoints;
    }


}
