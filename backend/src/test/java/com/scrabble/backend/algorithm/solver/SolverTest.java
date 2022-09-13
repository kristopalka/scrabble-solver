package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SolverTest {

    @Test
    public void getBestWordTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama",  4, 4, Word.Direction.HORIZONTAL));

        Word bestWord = Solver.getBestWords(boardBuilder.toCharArray(), new char[]{'a', 'b', 'c', 'd', 'e'}, 1).get(0);
        boardBuilder.addWord(bestWord);
        System.out.println(boardBuilder);
    }

    @Test
    public void getBestPointedTest() {
        Word word1 = new Word("aąbcćdeę",  0, 0, Word.Direction.HORIZONTAL);
        Word word2 = new Word("aaaaaaaaaaaaaaa",  5, 0, Word.Direction.VERTICAL);
        Word word3 = new Word("bbbbbbbbbbbbbbb",  0, 5, Word.Direction.HORIZONTAL);

        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        words.add(word3);

        List<Word> bestWords = Solver.getNBestPointed(words, null, 10);

        System.out.println(bestWords);

        Assertions.assertEquals(bestWords.get(0), word1);
        Assertions.assertEquals(bestWords.size(), 3);
    }
}
