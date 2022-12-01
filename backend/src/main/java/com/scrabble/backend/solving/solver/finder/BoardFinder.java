package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.Alphabet;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static com.scrabble.backend.solving.solver.finder.DictionaryFinder.getPotentialWords;
import static com.scrabble.backend.solving.solver.finder.Rotations.rotateVerticalToHorizontal;
import static com.scrabble.backend.solving.solver.finder.Rotations.transpose;


public class BoardFinder {
    public static List<Word> getAll(char[][] board, String rack, String lang) {
        if (isBoardClear(board)) return findForClearBoard(rack, lang);

        List<Word> allWords = new FastList<>();
        allWords.addAll(getVertical(board, rack, lang));
        allWords.addAll(getHorizontal(board, rack, lang));
        return allWords;
    }

    public static List<Word> getVertical(char[][] board, String rack, String lang) {
        ColumnFinder columnFinder = new ColumnFinder(board, rack, lang);

        return IntStream.range(0, board.length).parallel()
                .mapToObj(columnFinder::find)
                .flatMap(List::stream)
                .toList();
    }


    private static List<Word> getHorizontal(char[][] board, String rack, String lang) {
        List<Word> verticalToRotate = getVertical(transpose(board), rack, lang);
        return rotateVerticalToHorizontal(verticalToRotate);
    }

    private static boolean isBoardClear(char[][] board) {
        for (char[] column : board) {
            for (char field : column) if (field != Alphabet.emptySymbol) return false;
        }
        return true;
    }

    private static List<Word> findForClearBoard(String rack, String lang) {
        return getPotentialWords("", rack, ScrabbleResources.getDictionary(lang), 1)
                .stream().parallel()
                .map(potentialWord -> new Word(potentialWord, new Point(7, 7), Word.Direction.HORIZONTAL, new Point(7, 7), 0))
                .toList();
    }
}
