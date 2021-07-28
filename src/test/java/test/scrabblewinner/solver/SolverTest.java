package test.scrabblewinner.solver;

import com.scrabblewinner.scrabble.board.StandardBoard;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.holder.StandardHolder;
import com.scrabblewinner.solver.Solver;
import org.junit.jupiter.api.Test;

public class SolverTest {

    @Test
    public void getBestWordTest() {
        StandardBoard board = new StandardBoard();
        board.addWord(new Word("mama", 4, 4, Direction.HORIZONTAL));


        StandardHolder holder = new StandardHolder();
        holder.add('a').add('b').add('c').add('d').add('e');

        Word bestWord = Solver.getBestWord(board, holder);
        board.addWord(bestWord);
        System.out.println(board);
        System.out.println("Points: " + bestWord.getPoints(board));
    }
}
