package test.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.scrabble.holder.Holder;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.wordsfinder.correctselector.CorrectWordsSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CorrectWordsSelectorTest extends CorrectWordsSelector {

    @Test
    public void test() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 0, 0, Word.Direction.HORIZONTAL));

        ArrayList<String> potentialWords = new ArrayList<>();
        potentialWords.add("adam");


        int howManyCorrect = CorrectWordsSelector.select(
                board.toCharArray(),
                new StandardHolder().add('d').add('a').add('m').toCharArray(),
                1,
                potentialWords).size();
        Assertions.assertEquals(1, howManyCorrect);

        int howManyCorrect2 = CorrectWordsSelector.select(
                board.toCharArray(),
                new StandardHolder().toCharArray(),
                1,
                potentialWords).size();
        Assertions.assertEquals(0, howManyCorrect2);
    }

}
