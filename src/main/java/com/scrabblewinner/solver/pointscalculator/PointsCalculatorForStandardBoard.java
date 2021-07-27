package com.scrabblewinner.solver.pointscalculator;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Bonus;
import com.scrabblewinner.scrabble.board.components.Word;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;


public class PointsCalculatorForStandardBoard {

    public static Word getBest(ArrayList<Word> words) {
        if(words.size() == 0) throw new RuntimeException("List of words cannot be empty");

        Word bestWord = null;
        int bestPoints = 0;
        for (Word currentWord : words) {
            int currentPoints = currentWord.getPoints(new StandardBoard());
            if (bestPoints < currentPoints) {
                bestWord = currentWord;
                bestPoints = currentPoints;
            }
        }
        return bestWord;
    }
}
