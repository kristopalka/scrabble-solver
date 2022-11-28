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
import static com.scrabble.backend.solving.scrabble.ScrabbleResources.rackSize;

@SpringBootTest
public class FillBoardSpeedTest {
    private BoardBuilder boardBuilder = new BoardBuilder();

    @Value("${config.scrabble_resources}")
    String scrabbleResourcesPath;

    @Test
    void fillBoardWithNWords() {
        System.out.println(getAlphabet("pl").getLetters().toString());
        ScrabbleResources.path = scrabbleResourcesPath;
        for (int i = 0; i < 6; i++) {
            boardBuilder = new BoardBuilder();
            fillBoard();
        }

    }


    private void fillBoard() {
        long totalTime = 0;
        RackFilling.pointer = -1;


        for (int movesCounter = 0; true; movesCounter++) {
            String rack = RackFilling.fulfillRack("");

            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            List<Word> bestWords = Solver.getWords(boardBuilder.toCharArray(), rack, "pl", 1, "score");
            stopWatch.stop();


            if (bestWords.size() == 0 || movesCounter == 20) {
                System.out.print(movesCounter + 1);
                System.out.print(" ");
                System.out.println(totalTime);

                System.out.println(boardBuilder);
                return;
            }


            totalTime += stopWatch.getTotalTimeMillis(); // not takes into account when not found word
            boardBuilder.addWord(bestWords.get(0));
            //System.out.println(bestWords.get(0).value);
        }
    }



    static class RackFilling {
        private static final List<Character> letters = getAlphabet("pl").getLetters();
        public static int pointer = -1;


        public static char getLetter() {
            pointer++;
            if (pointer >= letters.size()) pointer=0;
            return letters.get(pointer);
        }

        private String getRandomRack() {
            char[] rack = new char[rackSize];
            for (int i = 0; i < rack.length; i++) {
                rack[i] = letters.get(new Random().nextInt(letters.size()));
            }
            return new String(rack);
        }

        public static String fulfillRack(String rack) {
            StringBuilder rackBuilder = new StringBuilder(rack);
            while (rackBuilder.length() < 7) {
                rackBuilder.append(getLetter());
            }
            return rackBuilder.toString();
        }
    }
}
