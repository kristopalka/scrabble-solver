package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.SurroundingFiltering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SurroundingFilteringTest extends SurroundingFiltering {


    @Test
    public void doWordFitsTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("abcdaabc", 1, 1, Word.Direction.VERTICAL));
        boardBuilder.addWord(new Word("aabcnab", 5, 1, Word.Direction.VERTICAL));
        boardBuilder.addWord(new Word("ab", 1, 5, Word.Direction.HORIZONTAL));
        boardBuilder.addWord(new Word("ynt", 4, 5, Word.Direction.HORIZONTAL));


        Assertions.assertTrue(SurroundingFiltering.doFits(
                new Word("abszmak", 3, 3, Word.Direction.VERTICAL), boardBuilder.toCharArray()));
        Assertions.assertFalse(SurroundingFiltering.doFits(
                new Word("abszmak", 7, 3, Word.Direction.VERTICAL), boardBuilder.toCharArray()));
    }


    @Test
    public void doLettersAgreeTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", 0, 0, Word.Direction.VERTICAL));

        SurroundingFiltering.board = boardBuilder.toCharArray();

        Assertions.assertTrue(SurroundingFiltering.doLettersAgree(
                new Word("absyda", 0, 0, Word.Direction.VERTICAL)));
        Assertions.assertFalse(SurroundingFiltering.doLettersAgree(
                new Word("abdyda", 0, 0, Word.Direction.VERTICAL)));
    }

    @Test
    public void doWordDisturbNeighborhoodTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", 0, 3, Word.Direction.HORIZONTAL));

        SurroundingFiltering.board = boardBuilder.toCharArray();

        Assertions.assertTrue(SurroundingFiltering.doNotDisturbNeighborhood(
                new Word("absyd", 5, 3, Word.Direction.VERTICAL)));
        Assertions.assertFalse(SurroundingFiltering.doNotDisturbNeighborhood(
                new Word("absyd", 5, 2, Word.Direction.VERTICAL)));
    }

    @Test
    public void wordDisturbButStillFitsTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("absyd", 0, 0, Word.Direction.HORIZONTAL));

        SurroundingFiltering.board = boardBuilder.toCharArray();

        Assertions.assertTrue(SurroundingFiltering.wordDisturbButStillFits(
                new Word("absyd", 5, 0, Word.Direction.VERTICAL), 0));

        Assertions.assertFalse(SurroundingFiltering.wordDisturbButStillFits(
                new Word("xbsyd", 5, 0, Word.Direction.VERTICAL), 0));

    }

}
