package com.scrabble.backend.algo.solver.wordsfinder;

import com.scrabble.backend.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.holder.StandardHolder;
import com.scrabble.backend.algorithm.solver.wordsfinder.correctselector.CorrectWordsSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CorrectWordsSelectorTest extends CorrectWordsSelector {

    @Test
    public void test() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 0, 0, Word.Direction.HORIZONTAL));

        ArrayList<String> potentialWords = new ArrayList<>();
        potentialWords.add("adam");


        int howManyCorrect = CorrectWordsSelector.select(
                board.toCharArray(),
                new StandardHolder().add('d').add('a').add('m').toCharArray(),
                1,
                potentialWords).size();
        Assertions.assertEquals(1, howManyCorrect);

        int howManyCorrect2 = CorrectWordsSelector.select(
                board.toCharArray(),
                new StandardHolder().toCharArray(),
                1,
                potentialWords).size();
        Assertions.assertEquals(0, howManyCorrect2);
    }

    @Test
    public void isEnoughLettersTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 2, 2, Word.Direction.HORIZONTAL));
        CorrectWordsSelector.board = board.toCharArray();
        CorrectWordsSelector.columnNumber = 3;

        Word potentialWord = new Word("mama", 3, 1, Word.Direction.VERTICAL);

        CorrectWordsSelector.holder = new char[]{'m','m','a'};
        Assertions.assertTrue(isEnoughLetters(potentialWord));

        CorrectWordsSelector.holder = new char[]{'m','a','a'};
        Assertions.assertFalse(isEnoughLetters(potentialWord));
    }

}
