package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WordsFinderTest {

    @Test
    public void getAllPossibleWordsVisualTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama",  4, 4, Word.Direction.HORIZONTAL));

        ArrayList<Word> all = WordsFinder.getVerticalAndHorizontal(boardBuilder.toCharArray(), new char[]{'a', 'b', 'c', 'd', 'e'});

        Assertions.assertEquals(66, all.size());
    }
}
