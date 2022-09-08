package com.scrabble.backend.algo;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.algorithm.scrabble.holder.StandardHolder;
import com.scrabble.backend.algorithm.simulation.FulfillBoardSimulation;
import org.junit.jupiter.api.Test;

public class Example {
    @Test
    void main() {
        Board board = new StandardBoard();
        board.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));

        Holder holder = new StandardHolder().fillInWithRandomLetters();

        FulfillBoardSimulation simulation = new FulfillBoardSimulation(board, holder);
        int score = simulation.run(100000);

        System.out.println("Score: " + score);
        System.out.println(board);
    }
}
