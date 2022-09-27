package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.solving.solver.finder.ColumnFinder;
import com.scrabble.backend.solving.solver.finder.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static com.scrabble.backend.solving.scrabble.resources.Alphabet.emptySymbol;
import static com.scrabble.backend.solving.solver.finder.ColumnFinder.*;

public class ColumnFinderTest {
    char e = emptySymbol;


    @Test
    public void extractContentText() {
        char[] column = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        String block = extractContent(column, 2, 4);
        Assertions.assertEquals("cde", block);
    }

    @Test
    public void findAllBlocksTest() {
        char[] column = {'a', e, e, e, 'b', 'c', 'd', e, e, e, e, e, e, 'g', 'h'};
        List<ColumnFinder.Block> allBlocks = findBlocks(column);

        Assertions.assertEquals(3, allBlocks.size());
        Assertions.assertEquals(new ColumnFinder.Block(0, "a"), allBlocks.get(0));
    }

    @Test
    public void getPossibleToArrangeWordsMamaTest() {
        String str = "mama";
        ColumnFinder.Block block = new ColumnFinder.Block(4, "a");

        List<Word> words = getPossibleWordsFromPotential(str, block, 9);
        Assertions.assertEquals(new Point(9, 3), words.get(0).begin);
        Assertions.assertEquals(new Point(9, 4), words.get(0).entryBegin);
        Assertions.assertEquals(str, words.get(0).value);

        Assertions.assertEquals(new Point(9, 1), words.get(1).begin);
        Assertions.assertEquals(new Point(9, 4), words.get(1).entryBegin);
        Assertions.assertEquals(str, words.get(1).value);
    }

    @Test
    public void getPossibleToArrangeWordsMaxLengthTest() {
        String str = "aaaaaaaaaa";
        Block block = new Block(7, "a");
        List<Word> words = getPossibleWordsFromPotential(str, block, 9);

        Assertions.assertEquals(6, words.size());
    }

    @Test
    public void getPossibleToArrangeWordsLongBlockTest() {
        String str = "wblocking";
        Block block = new Block(5, "block");
        List<Word> words = getPossibleWordsFromPotential(str, block, 9);

        Assertions.assertEquals(1, words.size());
        Assertions.assertEquals(str, words.get(0).value);
        Assertions.assertEquals(new Point(9, 4), words.get(0).begin);
        Assertions.assertEquals(new Point(9, 5), words.get(0).entryBegin);
    }


    @Test
    public void findOccurrencesTest() {
        String word = "abababxababxaba";
        String block = "abab";

        List<Integer> occurrences = findOccurrences(block, word);
        Assertions.assertEquals(0, occurrences.get(0));
        Assertions.assertEquals(2, occurrences.get(1));
        Assertions.assertEquals(7, occurrences.get(2));
    }



    @Test
    public void selectCorrectWordsTest() {
        char[] column = {e, e, e, e, e, e, e, 'r', 't', e, e, e, e, 'r', 't'};
        char[] ec = {e, e, e, e, e, e, e, e, e, e, e, e, e, e, e};
        char[][] board = {ec, column, ec, ec, ec, ec, ec, ec, ec, ec, ec, ec, ec, ec, ec};
        ColumnFinder finder = new ColumnFinder(board, "abe", "pl");

        List<Word> words = finder.find(1);

        words.forEach(w -> System.out.println(w.value + " " + w.begin + " " + w.entryBegin));
        Assertions.assertEquals(5, words.size());
    }
}
