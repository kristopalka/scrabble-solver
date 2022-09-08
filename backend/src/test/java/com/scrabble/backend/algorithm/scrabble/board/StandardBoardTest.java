package com.scrabble.backend.algorithm.scrabble.board;

import com.scrabble.backend.algorithm.scrabble.Alphabet;
import com.scrabble.backend.algorithm.scrabble.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class StandardBoardTest {
    @Test
    public void addWordTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("abcdefg", 0, 0, Word.Direction.VERTICAL));

        Assertions.assertEquals(board.getField(0, 0).getValue(), 'a');
        Assertions.assertEquals(board.getField(0, 6).getValue(), 'g');
        Assertions.assertEquals(board.getField(0, 8).getValue(), Alphabet.getEmptySymbol());
    }

    @Test
    public void addWordErrorTest() {
        StandardBoard board = new StandardBoard();
        try {
            board.addWord(new Word("abcdefg", 12, 12, Word.Direction.HORIZONTAL));
            //should throw exception
            Assertions.fail();
        }
        catch (InvalidParameterException ignored) {}
     }

    @Test
    public void toStringVisualTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("abcdefg", 0, 0, Word.Direction.VERTICAL));
        board.addWord(new Word("ajdhddjw", 6, 4, Word.Direction.HORIZONTAL));
        board.addWord(new Word("asdas", 9, 6, Word.Direction.VERTICAL));

        System.out.println(board);
   }



    @Test
    public void getPointsForWordManyLettersTest() {
        Word word = new Word("aąbcćdeę", 0, 0, Word.Direction.HORIZONTAL);

        //int points = word.getPoints(new StandardBoard());
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
