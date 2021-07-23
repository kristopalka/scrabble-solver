package com.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.scrabble.board.components.Word;

import java.util.ArrayList;

public class WordsFinder {
    public static ArrayList<Word> getAllPossibleWords(StandardBoard boardObject, StandardHolder holderObject) {
        int size = boardObject.getLength();
        char[][] board = boardObject.toCharArray();
        char[] holder = holderObject.toCharArray();

        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(VerticalWordsFinder.getVertical(board, holder, size));
        allWords.addAll(HorizontalWordFinder.getHorizontal(board, holder, size));
        return allWords;
    }
}
