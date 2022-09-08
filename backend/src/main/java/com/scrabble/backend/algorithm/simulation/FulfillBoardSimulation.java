package com.scrabble.backend.algorithm.simulation;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.algorithm.solver.Solver;
import com.scrabble.backend.algorithm.utility.Timer;
import com.scrabble.backend.algorithm.utility.exceptions.BoardIsFullException;
import com.scrabble.backend.algorithm.utility.exceptions.NoGivenLetterInHolderException;


public class FulfillBoardSimulation {
    private final Board board;
    private final Holder holder;


    public FulfillBoardSimulation(Board board, Holder holder) {
        this.board = board;
        this.holder = holder;
    }


    public int run(int numberOfMoves) {
        int score = 0;
        while (numberOfMoves > 0) {
            Word bestWord;
            Timer timer = new Timer();
            try {
                bestWord = Solver.getBestWord(board, holder);
            } catch (BoardIsFullException e) {
                System.out.println(e.getMessage());
                return score;
            } catch (NoGivenLetterInHolderException e) {
                System.out.printf("Try to add word: %s%n", e.getMessage());
                return score;
            }
            timer.stop();
            System.out.println("word " + bestWord + " with " + board.howManyPointsForWord(bestWord) + " points in " + timer.get() + " seconds");


            placeWord(bestWord);
            score += board.howManyPointsForWord(bestWord);

            numberOfMoves--;
        }
        return score;
    }

    private void placeWord(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            char letter = word.charAt(i);
            int x, y;
            if (word.getDirection() == Word.Direction.VERTICAL) {
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
