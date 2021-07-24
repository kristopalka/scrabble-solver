package test.scrabblewinner.scrabble.board;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class StandardBoardTest {
    @Test
    public void addWordTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("abcdefg", 0, 0, Direction.VERTICAL));

        Assertions.assertEquals(board.getField(0, 0).getValue(), 'a');
        Assertions.assertEquals(board.getField(0, 6).getValue(), 'g');
        Assertions.assertEquals(board.getField(0, 8).getValue(), Alphabet.getEmptySymbol());
    }

    @Test
    public void addWordErrorTest() {
        StandardBoard board = new StandardBoard();
        try {
            board.addWord(new Word("abcdefg", 12, 12, Direction.HORIZONTAL));
            //should throw exception
            Assertions.fail();
        }
        catch (InvalidParameterException ignored) {}
     }

    @Test
    public void toStringVisualTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("abcdefg", 0, 0, Direction.VERTICAL));
        board.addWord(new Word("ajdhddjw", 6, 4, Direction.HORIZONTAL));
        board.addWord(new Word("asdas", 9, 6, Direction.VERTICAL));

        System.out.println(board);
   }
}
