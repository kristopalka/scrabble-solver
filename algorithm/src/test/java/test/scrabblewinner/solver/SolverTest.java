package test.scrabblewinner.solver;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.Word;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SolverTest {

    @Test
    public void getBestWordTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 4, 4, Word.Direction.HORIZONTAL));


        StandardHolder holder = new StandardHolder();
        holder.add('a').add('b').add('c').add('d').add('e');

        Word bestWord = Solver.getBestWord(board, holder);
        board.addWord(bestWord);
        System.out.println(board);
        System.out.println("Points: " + new StandardBoard().howManyPointsForWord(bestWord));
    }

    @Test
    public void getBestPointedTest() {
        Word word1 = new Word("aąbcćdeę", 0, 0, Word.Direction.HORIZONTAL);
        Word word2 = new Word( "aaaaaaaaaaaaaaa", 5, 0, Word.Direction.VERTICAL);
        Word word3 = new Word( "bbbbbbbbbbbbbbb", 0, 5, Word.Direction.HORIZONTAL);

        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        words.add(word3);

        Word bestWord = Solver.getBestPointedWord(words, new StandardBoard());
        Assertions.assertEquals(bestWord, word1);
    }
}
