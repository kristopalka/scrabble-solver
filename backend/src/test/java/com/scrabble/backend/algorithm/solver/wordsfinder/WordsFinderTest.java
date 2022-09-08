package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.api.resolving.algorithm.scrabble.BoardBuilder;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WordsFinderTest {

    @Test
    public void getAllPossibleWordsVisualTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));

        ArrayList<Word> all = WordsFinder.getAllPossibleWords(boardBuilder.toCharArray(), new char[]{'a', 'b', 'c', 'd', 'e'});

        for (Word word : all) {
            BoardBuilder newBoardBuilder = new BoardBuilder();
            newBoardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));
            newBoardBuilder.addWord(word);

            System.out.println(newBoardBuilder);
        }
    }
}
