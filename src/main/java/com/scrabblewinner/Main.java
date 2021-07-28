package com.scrabblewinner;


import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.Holder;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.simulation.FulfillBoardSimulation;

public class Main {
    public static void main(String[] args) {
        Board board = new StandardBoard();
        board.addWord(new Word("a", 7, 7, Direction.VERTICAL));

        Holder holder = new StandardHolder().fillInWithRandomLetters();

        FulfillBoardSimulation simulation = new FulfillBoardSimulation(board, holder);
        int score = simulation.run(100000);

        System.out.println("Score: " + score);
        System.out.println(board);
    }
}
