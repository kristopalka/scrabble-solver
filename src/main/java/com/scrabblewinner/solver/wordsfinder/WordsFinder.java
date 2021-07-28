package com.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.scrabble.dictionary.Dictionary;
import com.scrabblewinner.scrabble.dictionary.SortedDictionary;
import com.scrabblewinner.scrabble.holder.Holder;

import java.util.ArrayList;


public class WordsFinder {
    public static ArrayList<Word> getAllPossibleWords(Board boardObject, Holder holderObject) {
        int size = boardObject.getLength();
        char[][] board = boardObject.toCharArray();
        char[] holder = holderObject.toCharArray();

        Dictionary.initialize();
        SortedDictionary.initialize();

        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(VerticalWordsFinder.getVertical(board, holder, size));
        allWords.addAll(HorizontalWordFinder.getHorizontal(board, holder, size));

        return allWords;
    }
}
