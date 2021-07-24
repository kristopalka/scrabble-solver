package test.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.wordsfinder.WordsFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WordsFinderTest {

    @Test
    public void getAllPossibleWordsVisualTest()  {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 4, 4, Direction.HORIZONTAL));


        StandardHolder holder = new StandardHolder();
        holder.add('a').add('b').add('c').add('d').add('e');

        ArrayList<Word> all = WordsFinder.getAllPossibleWords(board, holder);

        for (Word word : all) {
            StandardBoard newBoard = new StandardBoard();
            newBoard.addWord(new Word("mama", 4, 4, Direction.HORIZONTAL));
            newBoard.addWord(word);

            System.out.println(newBoard);
        }


    }
}
