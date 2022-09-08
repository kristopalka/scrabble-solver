package com.scrabble.backend.algorithm;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.board.Board;
import com.scrabble.backend.api.resolving.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.api.resolving.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.api.resolving.algorithm.scrabble.holder.StandardHolder;
import com.scrabble.backend.api.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;

public class FillBoardSimulationTest {
    private Board board;
    private Holder holder;

    @BeforeEach
    void prepare() {
        board = new StandardBoard();
        board.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));

        holder = new StandardHolder().fillInWithRandomLetters();
    }

    @Test
    void fillBoardWithNWords() {
        int score = run(5);

        System.out.println("Score: " + score);
        System.out.println(board);
    }


    public int run(int numberOfMoves) {
        int score = 0;
        for (int i=0; i<numberOfMoves; i++) {
            try {
                long startTime = currentTimeMillis();
                Word bestWord = Solver.getBestWord(board, holder);
                int wordScore = board.howManyPointsForWord(bestWord);
                System.out.printf("word %s with %s points in %s ms\n", bestWord, wordScore, currentTimeMillis() - startTime);

                board.addWord(bestWord);
                holder.replaceAllLetters();
                score += wordScore;

                System.out.println(board);
            } catch (RuntimeException e) {
                return score;
            }
        }
        return score;
    }
}
