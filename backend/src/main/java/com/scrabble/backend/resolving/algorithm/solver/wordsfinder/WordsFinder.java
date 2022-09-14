package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class WordsFinder {
    private static final int size = ScrabbleSettings.getBoardSize();

    public static List<Word> getVerticalAndHorizontal(char[][] board, String holder) {
        List<Word> allWords = new ArrayList<>();
        allWords.addAll(getVertical(board, holder));
        allWords.addAll(getHorizontal(board, holder));

        return allWords;
    }


    public static List<Word> getVertical(char[][] board, String holder) {
        return IntStream.range(0, board.length).parallel()
                .mapToObj(colNum -> WordsFinderForColumn.find(board, colNum, holder))
                .flatMap(List::stream)
                .toList();
    }


    private static List<Word> getHorizontal(char[][] board, String holder) {
        List<Word> verticalToRotate = getVertical(transpose(board), holder);
        return rotateVerticalToHorizontal(verticalToRotate);
    }


    private static char[][] transpose(char[][] board) {
        char[][] transposedBoard = new char[WordsFinder.size][WordsFinder.size];
        for (int x = 0; x < WordsFinder.size; x++) {
            for (int y = 0; y < WordsFinder.size; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static List<Word> rotateVerticalToHorizontal(List<Word> verticalToRotate) {
        return verticalToRotate.stream()
                .map(word -> new Word(word.value, transposePoint(word.begin), Word.Direction.HORIZONTAL, transposePoint(word.entryBegin), word.entryLength))
                .toList();
    }

    public static Point transposePoint(Point point){
        return new Point(point.y, point.x);
    }
}
