package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.algorithm.solver.wordsfinder.WordsFinder;
import com.scrabble.backend.algorithm.utility.exceptions.BoardIsFullException;

import java.util.ArrayList;

public class Solver {
    public static Word getBestWord(Board board, Holder holder) {
        ArrayList<Word> possibleWords = WordsFinder.getAllPossibleWords(board, holder);
        if (possibleWords.size() == 0) throw new BoardIsFullException("Cannot add any new word with existing stack of letters: " + holder);

        return getBestPointedWord(possibleWords, board);
    }

    public static Word getBestPointedWord(ArrayList<Word> words, Board board) {
        if(words.size() == 0) throw new RuntimeException("List of words cannot be empty");

        Word bestWord = null;
        int bestPoints = 0;
        for (Word currentWord : words) {
            int currentPoints = board.howManyPointsForWord(currentWord);
            if (bestPoints < currentPoints) {
                bestWord = currentWord;
                bestPoints = currentPoints;
            }
        }
        return bestWord;
    }

}
