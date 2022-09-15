package com.scrabble.backend.algorithm;

import com.scrabble.backend.resolving.algorithm.BoardBuilder;
import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;
import com.scrabble.backend.resolving.algorithm.solver.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

public class FillBoardSimulationTest {
    private BoardBuilder boardBuilder;
    private long totalTime = 0;

    @BeforeEach
    void prepare() {
        boardBuilder = new BoardBuilder();
        boardBuilder.addWord(new Word("a", 7, 7, Word.Direction.VERTICAL));
    }

    @Test
    void fillBoardWithNWords() {
        int moves = 10;
        run(moves);

        System.out.println("Average time: " + (totalTime / moves));
        System.out.println(boardBuilder);
    }


    private void run(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            try {
                final StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                Word bestWord = Solver.getSingleWordByBestScore(boardBuilder.toCharArray(), getRandomHolder());
                stopWatch.stop();

                System.out.printf("word %s in %s ms\n", bestWord, stopWatch.getTotalTimeMillis());
                totalTime += stopWatch.getTotalTimeMillis();

                boardBuilder.addWord(bestWord);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    private String getRandomHolder() {
        char[] holder = new char[ScrabbleSettings.getHolderSize()];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = Alphabet.getRandomLetter();
        }
        return new String(holder);
    }
}
