package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.scrabble.backend.resolving.algorithm.solver.wordsfinder.Rotations.rotateVerticalToHorizontal;
import static com.scrabble.backend.resolving.algorithm.solver.wordsfinder.Rotations.transpose;


public class WordsFinder {
    public static List<Word> getAll(char[][] board, String holder) {
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
}
