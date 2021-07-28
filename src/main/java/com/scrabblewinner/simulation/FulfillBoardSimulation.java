package com.scrabblewinner.simulation;

import com.scrabblewinner.scrabble.board.Board;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.scrabble.holder.Holder;
import com.scrabblewinner.solver.Solver;
import com.scrabblewinner.utility.Timer;
import com.scrabblewinner.utility.exceptions.BoardIsFullException;
import com.scrabblewinner.utility.exceptions.NoGivenLetterInHolderException;

import static com.scrabblewinner.scrabble.Word.Direction.VERTICAL;


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
            Timer timer = new Timer();
            Word bestWord = Solver.getBestWord(board, holder);
            timer.stop();
            System.out.println("word " + bestWord + " with " + board.howManyPointsForWord(bestWord) + " points in " + timer.get() + " seconds");

            try {
                placeWord(bestWord);
                score += board.howManyPointsForWord(bestWord);
            } catch (BoardIsFullException e) {
                System.out.println(e.getMessage());
                return score;
            } catch (NoGivenLetterInHolderException e) {
                System.out.println(String.format("Try to add word %s and: %s", bestWord, e.getMessage()));
                return score;
            }
            numberOfMoves--;
        }
        return score;
    }

    private void placeWord(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            char letter = word.charAt(i);
            int x, y;
            if (word.getDirection() == VERTICAL) {
                x = word.getXBegin();
                y = word.getYBegin() + i;
            } else {
                x = word.getXBegin() + i;
                y = word.getYBegin();
            }


            if (board.getField(x, y).isEmpty()) {
                holder.get(letter);
                board.addLetter(letter, x, y);
            } else if (board.getField(x, y).getValue() != letter) {
                throw new RuntimeException("Try to add word, that does not fits board");
            }

            holder.fillInWithRandomLetters();
        }
    }
}
