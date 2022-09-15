package com.scrabble.backend.resolving.algorithm.solver.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.scrabble.backend.resolving.algorithm.solver.finder.Rotations.rotateVerticalToHorizontal;
import static com.scrabble.backend.resolving.algorithm.solver.finder.Rotations.transpose;


public class BoardFinder {
    public static List<Word> getAll(char[][] board, String holder, String lang) {
        List<Word> allWords = new ArrayList<>();
        allWords.addAll(getVertical(board, holder, lang));
        allWords.addAll(getHorizontal(board, holder, lang));
        return allWords;
    }


    public static List<Word> getVertical(char[][] board, String holder, String lang) {
        ColumnFinder columnFinder = new ColumnFinder(board, holder, lang);

        return IntStream.range(0, board.length).parallel()
                .mapToObj(columnFinder::find)
                .flatMap(List::stream)
                .toList();
    }


    private static List<Word> getHorizontal(char[][] board, String holder, String lang) {
        List<Word> verticalToRotate = getVertical(transpose(board), holder, lang);
        return rotateVerticalToHorizontal(verticalToRotate);
    }
}
