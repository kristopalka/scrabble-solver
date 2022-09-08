package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.dictionary.Dictionary;
import com.scrabble.backend.algorithm.scrabble.dictionary.SortedDictionary;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.algorithm.solver.wordsfinder.correctselector.CorrectWordsSelector;
import com.scrabble.backend.algorithm.solver.wordsfinder.possibleselector.PossibleWordsFinderInDict;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class WordsFinder {
    public static ArrayList<Word> getAllPossibleWords(Board boardObject, Holder holderObject) {
        char[][] board = boardObject.toCharArray();
        char[] holder = holderObject.toCharArray();

        int size = boardObject.getLength();

        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(getVertical(board, holder, size));
        allWords.addAll(getHorizontal(board, holder, size));

        return allWords;
    }


    public static ArrayList<Word> getVertical(char[][] board, char[] holder, int size) {
        ArrayList<Word> words = new ArrayList<>();
        for (int colNum = 0; colNum < size; colNum++) {
            ArrayList<String> potentialWords = PossibleWordsFinderInDict.getWordsPossibleToArrangeFromLetters(board[colNum], holder);
            ArrayList<Word> correctWords = CorrectWordsSelector.select(board, holder, colNum, potentialWords);
            words.addAll(correctWords);
        }
        return words;
    }

    public static ArrayList<Word> getHorizontal(char[][] board, char[] holder, int size) {
        ArrayList<Word> verticalToRotate = getVertical(transpose(board, size), holder, size);
        return rotateVerticalToHorizontal(verticalToRotate);
    }


    private static char[][] transpose(char[][] board, int size) {
        char[][] transposedBoard = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static ArrayList<Word> rotateVerticalToHorizontal(ArrayList<Word> verticalToRotate) {
        return verticalToRotate.stream()
                .map(word -> new Word(word.getValue(), word.getYBegin(), word.getXBegin(), Word.Direction.HORIZONTAL))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
