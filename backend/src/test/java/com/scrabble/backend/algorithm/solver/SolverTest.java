package com.scrabble.backend.algorithm.solver;

import com.scrabble.backend.solving.solver.BoardBuilder;
import com.scrabble.backend.solving.solver.Solver;
import com.scrabble.backend.solving.solver.finder.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolverTest {


    @Test
    public void getBestWordTest() {
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));

        Word bestWord = Solver.getWords(boardBuilder.toCharArray(), "abcde", "pl", 1, "score").get(0);
        Assertions.assertEquals("abace", bestWord.value);
    }


}
