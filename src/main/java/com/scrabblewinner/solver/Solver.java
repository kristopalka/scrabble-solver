package com.scrabblewinner.solver;

import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.Holder;
import com.scrabblewinner.solver.pointscalculator.PointsCalculatorForStandardBoard;
import com.scrabblewinner.solver.wordsfinder.WordsFinder;
import com.scrabblewinner.utility.exceptions.BoardIsFullException;

import java.util.ArrayList;

public class Solver {
    public static Word getBestWord(Board board, Holder holder) {
        ArrayList<Word> possibleWords = WordsFinder.getAllPossibleWords(board, holder);
        if (possibleWords.size() == 0) throw new BoardIsFullException("Cannot add any new word with existing stack of letters: " + holder);

        return PointsCalculatorForStandardBoard.getBest(possibleWords);
    }

}
