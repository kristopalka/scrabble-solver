package test.scrabblewinner.solver.pointscalculator;

import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.solver.pointscalculator.PointsCalculatorForStandardBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointsCalculatorForStandardBoardTest {

    @Test
    public void getPointsForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę", 0, 0, Direction.HORIZONTAL);

        int points = PointsCalculatorForStandardBoard.getPointsForWord(word);
        Assertions.assertEquals(points, 243);
    }

    @Test
    public void getPointsForWordTestVerticalHorizontal() {
        Word wordVert = new Word("aaaaaaaaaaaaaaa", 5, 0, Direction.VERTICAL);
        Word wordHor = new Word( "aaaaaaaaaaaaaaa", 0, 5, Direction.HORIZONTAL);

        int pointsVert = PointsCalculatorForStandardBoard.getPointsForWord(wordVert);
        int pointsHor = PointsCalculatorForStandardBoard.getPointsForWord(wordHor);
        int points = 23;
        Assertions.assertEquals(points, pointsVert);
        Assertions.assertEquals(points, pointsHor);
    }
}
