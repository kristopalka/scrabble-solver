package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.api.resolving.algorithm.scrabble.BoardBuilder;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SolverTest {

    @Test
    public void getBestWordTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));

        Word bestWord = Solver.getBestWord(boardBuilder.toCharArray(), new char[]{'a', 'b', 'c', 'd', 'e'});
        boardBuilder.addWord(bestWord);
        System.out.println(boardBuilder);
    }

    @Test
    public void getBestPointedTest() {
        Word word1 = new Word("aąbcćdeę", 0, 0, Word.Direction.HORIZONTAL);
        Word word2 = new Word("aaaaaaaaaaaaaaa", 5, 0, Word.Direction.VERTICAL);
        Word word3 = new Word("bbbbbbbbbbbbbbb", 0, 5, Word.Direction.HORIZONTAL);

        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        words.add(word3);

        Word bestWord = Solver.getBestPointed(words, null);
        Assertions.assertEquals(bestWord, word1);
    }
}
