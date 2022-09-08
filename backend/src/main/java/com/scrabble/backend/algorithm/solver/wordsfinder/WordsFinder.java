package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.dictionary.Dictionary;
import com.scrabble.backend.algorithm.scrabble.dictionary.SortedDictionary;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;

import java.util.ArrayList;


public class WordsFinder {
    public static ArrayList<Word> getAllPossibleWords(Board boardObject, Holder holderObject) {
        char[][] board = boardObject.toCharArray();
        char[] holder = holderObject.toCharArray();

        return getAllPossibleWords(board, holder);
    }

    public static ArrayList<Word> getAllPossibleWords(char[][] board, char[] holder) {
        int size = board.length;

        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(VerticalWordsFinder.getVertical(board, holder, size));
        allWords.addAll(HorizontalWordFinder.getHorizontal(board, holder, size));

        return allWords;
    }
}
