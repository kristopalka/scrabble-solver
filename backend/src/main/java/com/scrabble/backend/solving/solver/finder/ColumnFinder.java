package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.Dictionary;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.scrabble.backend.solving.scrabble.Alphabet.emptySymbol;

public class ColumnFinder {
    private final char[][] board;
    private final String rack;
    private final Dictionary dictionary;
    private final SurroundingFittingChecker checker;

    public ColumnFinder(char[][] board, String rack, String lang) {
        this.board = board;
        this.rack = rack;
        this.dictionary = ScrabbleResources.getDictionary(lang);
        this.checker = new SurroundingFittingChecker(board, dictionary);
    }


    public static String getLetters(char[] column) {
        StringBuilder str = new StringBuilder();
        for (char field:column) {
            if(field != emptySymbol) str.append(field);
        }
        return str.toString();
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
        return column[i] != emptySymbol && (i == 0 || column[i - 1] == emptySymbol);
    }

    public static boolean isEndOfBlock(char[] column, int i) {
        return column[i] != emptySymbol && (i == ScrabbleResources.boardSize - 1 || column[i + 1] == emptySymbol);
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
        List<Block> blocks = findBlocks(board[columnNumber]);
        List<Word> words = new FastList<>();

        for (Block block:findBlocks(board[columnNumber])) {
            words.addAll(DictionaryFinder.getPotentialWords(block.content, rack, dictionary, minBlockLength(blocks))
                    .stream().parallel()
                    .map(potentialWord -> getPossibleWordsFromPotential(potentialWord, block, columnNumber))
                    .flatMap(List::stream)
                    .filter(checker::doFits).toList());
        }
        return words;
    }


    public int minBlockLength(List<Block> blocks){
        if(blocks.size() == 0) return 0;

        int minLen = Integer.MAX_VALUE;
        for(Block block:blocks){
            int len = block.content.length();
            if(len < minLen) minLen = len;
        }
        return minLen;
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
