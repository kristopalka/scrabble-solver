package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PotentialWordsFinder {
    private static final char empty = Alphabet.getEmptySymbol();
    private static final int length = ScrabbleSettings.getBoardSize();


    public static List<Word> selectCorrectWords(char[] column, int columnNumber, String holder) {
        return findAllBlocks(column).stream()
                .map(block -> collectFromPotential(holder, block, columnNumber))
                .flatMap(List::stream)
                .toList();
    }

    protected static List<Block> findAllBlocks(char[] column) {
        List<Block> blocks = new ArrayList<>();
        int start = -1;
        int end;
        for (int i = 0; i < length; i++) {
            if (isStartOfBlock(column, i)) start = i;
            if (isEndOfBlock(column, i)) {
                end = i;
                blocks.add(new Block(start, extractContent(column, start, end)));
            }
        }
        return blocks;
    }

    protected static boolean isStartOfBlock(char[] column, int i) {
        return column[i] != empty && (i == 0 || column[i - 1] == empty);
    }

    private static boolean isEndOfBlock(char[] column, int i) {
        return column[i] != empty && (i == length - 1 || column[i + 1] == empty);
    }

    protected static String extractContent(char[] column, int start, int end) {
        return new String(Arrays.copyOfRange(column, start, end + 1));
    }



    protected static List<Word> collectFromPotential(String holder, Block block, int columnNumber) {
        return DictionaryFinder.getPotentialWords(block.content, holder).stream()
                .filter(potentialWord -> potentialWord.length() > block.length())
                .map(potentialWord -> getPossibleToArrangeWords(potentialWord, block, columnNumber))
                .flatMap(List::stream)
                .toList();
    }

    protected static List<Word> getPossibleToArrangeWords(String potentialWord, Block block, int columnNumber) {
        List<Word> words = new ArrayList<>();
        for (Integer position : findOccurrences(block.content, potentialWord)) {
            Point begin = new Point(columnNumber, block.start - position);
            Point entryBegin = new Point(columnNumber, block.start);
            Word word = new Word(potentialWord, begin, Word.Direction.VERTICAL, entryBegin, block.length());

            if(word.yBegin() >= 0 && word.yEnd() < length) words.add(word);
        }
        return words;
    }

    protected static List<Integer> findOccurrences(String block, String word) {
        List<Integer> indexes = new ArrayList<>();
        int index = 0;
        while (index != -1) {
            index = word.indexOf(block, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }
}
