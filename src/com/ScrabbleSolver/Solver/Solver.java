package com.ScrabbleSolver.Solver;

import com.ScrabbleSolver.Scrabble.Board.StandardBoard;
import com.ScrabbleSolver.Scrabble.Holder.StandardHolder;
import com.ScrabbleSolver.Scrabble.Board.Components.Word;

import java.util.ArrayList;

public class Solver
{
    public static void getBestWords(StandardBoard board, StandardHolder holder, int numberOfWordsToGet)
    {
        ArrayList<Word> possibleWords = WordsFinder.getAll(board, holder);
        //todo calculate points for each word
        //todo sort and return
    }







}
