package test.scrabblewinner.scrabble;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTest {
    @Test
    public void getPointsForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę", 0, 0, Word.Direction.HORIZONTAL);

        int points = new StandardBoard().howManyPointsForWord(word);
        Assertions.assertEquals(points, 243);
    }

    @Test
    public void getPointsForWordTestVerticalHorizontal() {
        Word wordVert = new Word("aaaaaaaaaaaaaaa", 5, 0, Word.Direction.VERTICAL);
        Word wordHor = new Word( "aaaaaaaaaaaaaaa", 0, 5, Word.Direction.HORIZONTAL);

        int pointsVert = new StandardBoard().howManyPointsForWord(wordVert);
        int pointsHor = new StandardBoard().howManyPointsForWord(wordHor);
        int points = 23;
        Assertions.assertEquals(points, pointsVert);
        Assertions.assertEquals(points, pointsHor);
    }
}
