package com.scrabblewinner.solver.finder;

import com.scrabblewinner.scrabble.board.components.Word;

import java.util.ArrayList;

public class VerticalWordsFinder {
    public static ArrayList<Word> getVertical(char[][] board, char[] holder, int size) {
        ArrayList<Word> words = new ArrayList<>();
        for (int colNum = 0; colNum < size; colNum++) {
            ArrayList<String> potentialWords = PotentialWordsFinder.getAll(board[colNum], holder);
            ArrayList<Word> correctWords = CorrectWordsSelector.select(potentialWords);

            words.addAll(correctWords);
        }
        return words;
    }


}
