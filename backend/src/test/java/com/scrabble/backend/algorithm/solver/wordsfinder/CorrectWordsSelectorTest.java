package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector.CorrectWordsSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CorrectWordsSelectorTest extends CorrectWordsSelector {

    @Test
    public void test() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 0, 0, Word.Direction.HORIZONTAL));

        ArrayList<String> potentialWords = new ArrayList<>();
        potentialWords.add("adam");


        int howManyCorrect = CorrectWordsSelector.selectCorrectWords(
                boardBuilder.toCharArray(),
                new char[]{'d', 'a', 'm'},
                1,
                potentialWords).size();
        Assertions.assertEquals(1, howManyCorrect);

        int howManyCorrect2 = CorrectWordsSelector.selectCorrectWords(
                boardBuilder.toCharArray(),
                new char[]{},
                1,
                potentialWords).size();
        Assertions.assertEquals(0, howManyCorrect2);
    }

    @Test
    public void isEnoughLettersTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 2, 2, Word.Direction.HORIZONTAL));
        CorrectWordsSelector.board = boardBuilder.toCharArray();
        CorrectWordsSelector.columnNumber = 3;

        Word potentialWord = new Word("mama", 3, 1, Word.Direction.VERTICAL);

        CorrectWordsSelector.holder = new char[]{'m', 'm', 'a'};
        Assertions.assertTrue(isEnoughLetters(potentialWord));

        CorrectWordsSelector.holder = new char[]{'m', 'a', 'a'};
        Assertions.assertFalse(isEnoughLetters(potentialWord));
    }

}
