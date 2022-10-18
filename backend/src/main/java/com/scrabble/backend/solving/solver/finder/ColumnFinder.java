package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.scrabble.resources.Dictionary;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.awt.*;
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
        this.dictionary = ScrabbleResources.getDictionary(lang);
        this.checker = new SurroundingFittingChecker(board, dictionary);
    }

    public static List<Block> findBlocks(char[] column) {
        List<Block> blocks = new FastList<>();
        int start = -1;
        int end;
        for (int i = 0; i < ScrabbleResources.boardSize; i++) {
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
        return column[i] != Alphabet.emptySymbol && (i == ScrabbleResources.boardSize - 1 || column[i + 1] == Alphabet.emptySymbol);
    }

    public static String extractContent(char[] column, int start, int end) {
        return new String(Arrays.copyOfRange(column, start, end + 1));
    }

    public static List<Word> getPossibleWordsFromPotential(String potentialWord, Block block, int columnNumber) {
        List<Word> words = new FastList<>();
        IntArrayList occurrences = findOccurrences(block.content, potentialWord);
        for (int i = 0; i < occurrences.size(); i++) {
            Point begin = new Point(columnNumber, block.start - occurrences.get(i));
            Point entryBegin = new Point(columnNumber, block.start);
            Word word = new Word(potentialWord, begin, Word.Direction.VERTICAL, entryBegin, block.length());

            if (word.yBegin() >= 0 && word.yEnd() < ScrabbleResources.boardSize) words.add(word);
        }
        return words;
    }

    public static IntArrayList findOccurrences(String block, String word) {
        IntArrayList indexes = new IntArrayList();
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
        List<Word> words = new FastList<>();
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
