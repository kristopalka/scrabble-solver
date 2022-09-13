package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.SurroundingFiltering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SurroundingFilteringTest {
    SurroundingFiltering nullBoard = new SurroundingFiltering(null);

    @Test
    public void doWordFitsTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("abcdaabc", 1, 1, Word.Direction.VERTICAL));
        boardBuilder.addWord(new Word("aabcnab", 5, 1, Word.Direction.VERTICAL));
        boardBuilder.addWord(new Word("ab", 1, 5, Word.Direction.HORIZONTAL));
        boardBuilder.addWord(new Word("ynt", 4, 5, Word.Direction.HORIZONTAL));

        SurroundingFiltering filtering = new SurroundingFiltering(boardBuilder.toCharArray());


        Assertions.assertTrue(filtering.doFits(new Word("abszmak", new Point(3, 3), Word.Direction.VERTICAL, new Point(0,0), 0)));
        Assertions.assertFalse(filtering.doFits(new Word("abszmak", new Point(7, 3), Word.Direction.VERTICAL, new Point(0,0), 0)));
    }


    @Test
    public void doLettersAgreeTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", 0, 0, Word.Direction.VERTICAL));

        SurroundingFiltering filtering = new SurroundingFiltering(boardBuilder.toCharArray());

        Assertions.assertTrue(filtering.doLettersAgree(new Word("absyda", new Point(0, 0), Word.Direction.VERTICAL, new Point(0,0), 0)));
        Assertions.assertFalse(filtering.doLettersAgree(new Word("abdyda", new Point(0, 0), Word.Direction.VERTICAL, new Point(0,0), 0)));
    }

    @Test
    public void doWordDisturbNeighborhoodTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", new Point(0, 3), Word.Direction.HORIZONTAL, new Point(0,0), 0));

        SurroundingFiltering filtering = new SurroundingFiltering(boardBuilder.toCharArray());

        Assertions.assertTrue(filtering.doNotDisturbNeighborhood(new Word("absyd", new Point(5, 3), Word.Direction.VERTICAL, new Point(0,0), 0)));
        Assertions.assertFalse(filtering.doNotDisturbNeighborhood(new Word("absyd", new Point(5, 2), Word.Direction.VERTICAL, new Point(0,0), 0)));
    }

    @Test
    public void wordDisturbButStillFitsTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", new Point(0, 0), Word.Direction.HORIZONTAL, new Point(0,0), 0));

        SurroundingFiltering filtering = new SurroundingFiltering(boardBuilder.toCharArray());

        Assertions.assertTrue(filtering.wordDisturbButStillFits(new Word("absyd", new Point(5, 0), Word.Direction.VERTICAL, new Point(0,0), 0), 0));
        Assertions.assertFalse(filtering.wordDisturbButStillFits(new Word("xbsyd", new Point(5, 0), Word.Direction.VERTICAL, new Point(0,0), 0), 0));
    }

}
