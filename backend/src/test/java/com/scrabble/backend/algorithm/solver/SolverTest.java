package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.Test;

public class SolverTest {

    @Test
    public void getBestWordTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));

        Word bestWord = Solver.getWordsByBestScore(boardBuilder.toCharArray(), "abcde", 1).get(0);
        boardBuilder.addWord(bestWord);
        System.out.println(boardBuilder);
    }

}
