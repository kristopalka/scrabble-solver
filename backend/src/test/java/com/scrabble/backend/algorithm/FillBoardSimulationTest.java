package com.scrabble.backend.algorithm;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;
import com.scrabble.backend.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.scrabble.backend.resolving.algorithm.solver.PointsCalculator.calculatePoints;
import static java.lang.System.currentTimeMillis;

public class FillBoardSimulationTest {
    private BoardBuilder boardBuilder;

    @BeforeEach
    void prepare() {
        boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));
    }

    @Test
    void fillBoardWithNWords() {
        int score = run(5);

        System.out.println("Score: " + score);
        System.out.println(boardBuilder);
    }


    private int run(int numberOfMoves) {
        int score = 0;
        for (int i = 0; i < numberOfMoves; i++) {
            try {
                long startTime = currentTimeMillis();
                Word bestWord = Solver.getBestWord(boardBuilder.toCharArray(), getRandomHolder());
                int wordScore = calculatePoints(bestWord, null);
                System.out.printf("word %s with %s points in %s ms\n", bestWord, wordScore, currentTimeMillis() - startTime);

                boardBuilder.addWord(bestWord);
                score += wordScore;
            } catch (RuntimeException e) {
                return score;
            }
        }
        return score;
    }

    private char[] getRandomHolder() {
        char[] holder = new char[ScrabbleSettings.getHolderSize()];
        for (int i=0; i<holder.length; i++) {
            holder[i] = Alphabet.getRandomLetter();
        }
        return holder;
    }
}