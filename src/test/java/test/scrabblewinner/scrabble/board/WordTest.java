package test.scrabblewinner.scrabble.board;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTest {
    @Test
    public void getPointsForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę", 0, 0, Direction.HORIZONTAL);

        int points = word.getPoints(new StandardBoard());
        Assertions.assertEquals(points, 243);
    }

    @Test
    public void getPointsForWordTestVerticalHorizontal() {
        Word wordVert = new Word("aaaaaaaaaaaaaaa", 5, 0, Direction.VERTICAL);
        Word wordHor = new Word( "aaaaaaaaaaaaaaa", 0, 5, Direction.HORIZONTAL);

        int pointsVert = wordVert.getPoints(new StandardBoard());
        int pointsHor = wordHor.getPoints(new StandardBoard());
        int points = 23;
        Assertions.assertEquals(points, pointsVert);
        Assertions.assertEquals(points, pointsHor);
    }
}
