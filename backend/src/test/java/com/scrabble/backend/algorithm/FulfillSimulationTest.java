package com.scrabble.backend.algorithm;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.board.Board;
import com.scrabble.backend.algorithm.scrabble.board.StandardBoard;
import com.scrabble.backend.algorithm.scrabble.holder.Holder;
import com.scrabble.backend.algorithm.scrabble.holder.StandardHolder;
import com.scrabble.backend.algorithm.solver.Solver;
import com.scrabble.backend.algorithm.utility.Timer;
import com.scrabble.backend.algorithm.utility.exceptions.BoardIsFullException;
import com.scrabble.backend.algorithm.utility.exceptions.NoGivenLetterInHolderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FulfillSimulationTest {
    private Board board;
    private Holder holder;

    @BeforeEach
    void prepare() {
        board = new StandardBoard();
        board.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));

        holder = new StandardHolder().fillInWithRandomLetters();
    }

    @Test
    void main() {
        int score = run(5);

        System.out.println("Score: " + score);
        System.out.println(board);
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
