package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.Static;
import com.scrabble.backend.solving.scrabble.resources.Dictionary;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColumnFinder {
    private final char[][] board;
    private final String holder;
    private final Dictionary dictionary;
    private final SurroundingFittingChecker checker;

    public ColumnFinder(char[][] board, String holder, String lang) {
        this.board = board;
        this.holder = holder;
        this.dictionary = Static.getDictionary(lang);
        this.checker = new SurroundingFittingChecker(board, dictionary);
    }

    public static List<Block> findBlocks(char[] column) {
        List<Block> blocks = new ArrayList<>();
        int start = -1;
        int end;
        for (int i = 0; i < Static.boardSize; i++) {
            if (isStartOfBlock(column, i)) start = i;
            if (isEndOfBlock(column, i)) {
                end = i;
                blocks.add(new Block(start, extractContent(column, start, end)));
            }
        }
        return blocks;
    }

    public static boolean isStartOfBlock(char[] column, int i) {
        return column[i] != Alphabet.emptySymbol && (i == 0 || column[i - 1] == Alphabet.emptySymbol);
    }

    public static boolean isEndOfBlock(char[] column, int i) {
        return column[i] != Alphabet.emptySymbol && (i == Static.boardSize - 1 || column[i + 1] == Alphabet.emptySymbol);
    }

    public static String extractContent(char[] column, int start, int end) {
        return new String(Arrays.copyOfRange(column, start, end + 1));
    }

    public static List<Word> getPossibleWordsFromPotential(String potentialWord, Block block, int columnNumber) {
        List<Word> words = new ArrayList<>();
        for (Integer position : findOccurrences(block.content, potentialWord)) {
            Point begin = new Point(columnNumber, block.start - position);
            Point entryBegin = new Point(columnNumber, block.start);
            Word word = new Word(potentialWord, begin, Word.Direction.VERTICAL, entryBegin, block.length());

            if (word.yBegin() >= 0 && word.yEnd() < Static.boardSize) words.add(word);
        }
        return words;
    }

    public static List<Integer> findOccurrences(String block, String word) {
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

    public List<Word> find(int columnNumber) {
        return findBlocks(board[columnNumber])
                .stream().parallel()
                .map(block -> getPossibleWordsForBlock(block, columnNumber))
                .flatMap(List::stream)
                .filter(checker::doFits)
                .toList();
    }

    public List<Word> getPossibleWordsForBlock(Block block, int columnNumber) {
        List<Word> words = new ArrayList<>();
        for (String potentialWord : DictionaryFinder.getPotentialWords(block.content, holder, dictionary)) {
            if (potentialWord.length() <= block.length()) continue;
            words.addAll(getPossibleWordsFromPotential(potentialWord, block, columnNumber));
        }
        return words;
    }

    @ToString
    @EqualsAndHashCode
    public static class Block {
        public int start;
        public int end;
        public String content;

        public Block(int start, String content) {
            this.start = start;
            this.end = start + content.length() - 1;
            this.content = content;
        }

        public int length() {
            return end - start + 1;
        }
    }
}
