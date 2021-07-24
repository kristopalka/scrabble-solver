package test.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.solver.wordsfinder.correctselector.WordFitsChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordFitsCheckerTest extends WordFitsChecker {


    @Test
    public void doWordFitsTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("abcdaabc", 1, 1, Direction.VERTICAL));
        board.addWord(new Word("aabcnab", 5, 1, Direction.VERTICAL));
        board.addWord(new Word("ab", 1, 5, Direction.HORIZONTAL));
        board.addWord(new Word("ynt", 4, 5, Direction.HORIZONTAL));


        Assertions.assertTrue(WordFitsChecker.doWordFits(
                new Word("abszmak", 3, 3, Direction.VERTICAL), board.toCharArray()));
        Assertions.assertFalse(WordFitsChecker.doWordFits(
                new Word("abszmak", 7, 3, Direction.VERTICAL), board.toCharArray()));
    }


    @Test
    public void doLettersAgreeTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("absyd", 0, 0, Direction.VERTICAL));

        WordFitsChecker.board = board.toCharArray();

        Assertions.assertTrue(WordFitsChecker.doLettersAgree(
                new Word("absyda", 0, 0, Direction.VERTICAL)));
        Assertions.assertFalse(WordFitsChecker.doLettersAgree(
                new Word("abdyda", 0, 0, Direction.VERTICAL)));
    }

    @Test
    public void doWordDisturbNeighborhoodTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("absyd", 0, 3, Direction.HORIZONTAL));

        WordFitsChecker.board = board.toCharArray();

        //System.out.println(board);

        Assertions.assertFalse(WordFitsChecker.doWordDisturbNeighborhood(
                new Word("absyd", 5, 3, Direction.VERTICAL)));
        Assertions.assertTrue(WordFitsChecker.doWordDisturbNeighborhood(
                new Word("absyd", 5, 2, Direction.VERTICAL)));
    }

    @Test
    public void wordDisturbButStillFitsTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("absyd", 0, 0, Direction.HORIZONTAL));

        WordFitsChecker.board = board.toCharArray();

        Assertions.assertTrue(WordFitsChecker.wordDisturbButStillFits(
                new Word("absyd", 5, 0, Direction.VERTICAL), 0));

        Assertions.assertFalse(WordFitsChecker.wordDisturbButStillFits(
                new Word("xbsyd", 5, 0, Direction.VERTICAL), 0));

    }

}
