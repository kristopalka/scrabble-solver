package test.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.solver.wordsfinder.correctselector.CorrectWordsSelector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CorrectWordsSelectorTest extends CorrectWordsSelector {

    @Test
    public void test() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 0, 5, Word.Direction.HORIZONTAL));

        ArrayList<String> potentialWords = new ArrayList<>();
        potentialWords.add("mama");
        potentialWords.add("adam");


        System.out.println(CorrectWordsSelector.select(
                board.toCharArray(),
                2,
                potentialWords));
    }

}
