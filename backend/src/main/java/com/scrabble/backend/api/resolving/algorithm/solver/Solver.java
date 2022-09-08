package com.scrabble.backend.api.resolving.algorithm.solver;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.WordsFinder;

import java.util.ArrayList;

import static com.scrabble.backend.api.resolving.algorithm.solver.PointCalculator.calculatePoints;

public class Solver {
    public static Word getBestWord(char[][] board, char[] holder) {
        ArrayList<Word> possibleWords = WordsFinder.getAllPossibleWords(board, holder);
        if (possibleWords.size() == 0) throw new RuntimeException("Not found words to add");

        return getBestPointed(possibleWords, board);
    }

    public static Word getBestPointed(ArrayList<Word> words, char[][] board) {
        Word bestWord = null;
        int bestPoints = 0;
        for (Word currentWord : words) {
            int currentPoints = calculatePoints(currentWord, board);
            if (bestPoints < currentPoints) {
                bestWord = currentWord;
                bestPoints = currentPoints;
            }
        }
        return bestWord;
    }

}
