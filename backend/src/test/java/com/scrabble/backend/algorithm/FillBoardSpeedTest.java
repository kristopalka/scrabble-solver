package com.scrabble.backend.algorithm;

import com.scrabble.backend.solving.scrabble.BoardBuilder;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.solver.Solver;
import com.scrabble.backend.solving.solver.finder.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;

import static com.scrabble.backend.solving.scrabble.ScrabbleResources.getAlphabet;
import static com.scrabble.backend.solving.scrabble.ScrabbleResources.holderSize;

@SpringBootTest
public class FillBoardSpeedTest {
    private BoardBuilder boardBuilder = new BoardBuilder();

    @Value("${config.scrabble_resources}")
    String scrabbleResourcesPath;

    @Test
    void fillBoardWithNWords() {
        ScrabbleResources.path = scrabbleResourcesPath;
        for (int i = 0; i < 1; i++) {
            boardBuilder = new BoardBuilder();
            fillBoard();
        }

    }


    private void fillBoard() {
        long totalTime = 0;

        for (int movesCounter = 0; true; movesCounter++) {
            // String holder = getRandomHolder();
            String holder = "lmptbhjc";


            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            List<Word> bestWords = Solver.getWords(boardBuilder.toCharArray(), holder, "pl", 1, "score");
            stopWatch.stop();

            if (bestWords.size() == 0) {
                System.out.print(movesCounter);
                System.out.print(" ");
                System.out.println(totalTime);

                System.out.println(boardBuilder);
                System.out.println(holder);

                return;
            }


            totalTime += stopWatch.getTotalTimeMillis(); // not takes into account when not found word
            boardBuilder.addWord(bestWords.get(0));
        }
    }

    private String getRandomHolder() {
        List<Character> letters = getAlphabet("pl").getLetters();

        char[] holder = new char[holderSize];
        for (int i = 0; i < holder.length; i++) {
            holder[i] = letters.get(new Random().nextInt(letters.size()));
        }
        return new String(holder);
    }
}
