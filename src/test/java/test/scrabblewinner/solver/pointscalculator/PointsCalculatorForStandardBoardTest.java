package test.scrabblewinner.solver.pointscalculator;

import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.solver.pointscalculator.PointsCalculatorForStandardBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class PointsCalculatorForStandardBoardTest {

    @Test
    public void getPointsForWordTestVerticalHorizontal() {
        Word word1 = new Word("aąbcćdeę", 0, 0, Direction.HORIZONTAL);
        int points1 = 243;
        Word word2 = new Word( "aaaaaaaaaaaaaaa", 5, 0, Direction.VERTICAL);
        int points2 = 23;
        Word word3 = new Word( "bbbbbbbbbbbbbbb", 0, 5, Direction.HORIZONTAL);
        int points3 = 69;

        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        words.add(word3);

        Word bestWord = PointsCalculatorForStandardBoard.getBest(words);
        Assertions.assertEquals(bestWord, word1);
    }
}

