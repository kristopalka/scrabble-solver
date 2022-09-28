package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.Static;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.scrabble.backend.solving.solver.finder.DictionaryFinder.getPotentialWords;
import static com.scrabble.backend.solving.solver.finder.Rotations.rotateVerticalToHorizontal;
import static com.scrabble.backend.solving.solver.finder.Rotations.transpose;


public class BoardFinder {
    public static List<Word> getAll(char[][] board, String holder, String lang) {
        if (isBoardClear(board)) return findForClearBoard(holder, lang);

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

    private static boolean isBoardClear(char[][] board) {
        for (char[] column : board) {
            for (char field : column) if (field != Alphabet.emptySymbol) return false;
        }
        return true;
    }

    private static List<Word> findForClearBoard(String holder, String lang) {
        return getPotentialWords("", holder, Static.getDictionary(lang))
                .stream()
                .map(potentialWord -> new Word(potentialWord, new Point(7, 7), Word.Direction.HORIZONTAL, new Point(7, 7), 0))
                .toList();
    }
}
