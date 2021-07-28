package com.scrabblewinner.simulation;

import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.Holder;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.Solver;
import com.scrabblewinner.utility.Timer;
import com.scrabblewinner.utility.exceptions.NoGivenLetterInHolder;

import java.sql.SQLOutput;


public class FulfillBoardSimulation {
    private Board board;
    private Holder holder;


    public FulfillBoardSimulation(Board board, Holder holder) {
        this.board = board;
        this.holder = holder;
    }


    public int run(int numberOfMoves) {
        int score = 0;
        while (numberOfMoves > 0) {
            try {
                Timer timer = new Timer();
                Word bestWord = Solver.getBestWord(board, holder);
                placeWord(bestWord);
                timer.stop();

                score += bestWord.getPoints(board);
                System.out.println("Add word \"" + bestWord + "\" with " + bestWord.getPoints(board) + " points in " + timer.get() + " seconds");
            } catch (NoGivenLetterInHolder e) {
                System.out.println(e.getMessage());
                return score;
            }
            numberOfMoves--;
        }
        return score;
    }

    private void placeWord(Word word) {
        holder.selectLettersForWord(word);
        holder.fillInWithRandomLetters();
        board.addWord(word);
    }

}
