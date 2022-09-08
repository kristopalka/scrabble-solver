package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.api.resolving.algorithm.scrabble.BoardBuilder;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;

public class WordsFinderTest {

    @Test
    public void getAllPossibleWordsVisualTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));

        ArrayList<Word> all = WordsFinder.getAllPossibleWords(boardBuilder.toCharArray(), new char[]{'a', 'b', 'c', 'd', 'e'});

        Assertions.assertEquals(66, all.size());
    }
}
