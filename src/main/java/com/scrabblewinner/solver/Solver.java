package com.scrabblewinner.solver;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.solver.pointscalculator.PointsCalculatorForStandardBoard;
import com.scrabblewinner.solver.wordsfinder.WordsFinder;

import java.util.ArrayList;

public class Solver
{
    public static Word getBestWord(StandardBoard board, StandardHolder holder)
    {
        ArrayList<Word> possibleWords = WordsFinder.getAllPossibleWords(board, holder);
        return PointsCalculatorForStandardBoard.getBest(possibleWords);
    }

}
