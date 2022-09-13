package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;

import java.util.ArrayList;

public class CorrectWordsSelector {
    private static final char empty = Alphabet.getEmptySymbol();

    protected static char[][] board;
    protected static char[] column;
    protected static int columnNumber;
    protected static char[] holderLetters;

    protected static ArrayList<String> potentialWords;

    public static ArrayList<Word> selectCorrectWords(char[][] board, char[] holderLetters, int columnNumber, ArrayList<String> potentialWords) {
        CorrectWordsSelector.board = board;
        CorrectWordsSelector.column = board[columnNumber];
        CorrectWordsSelector.columnNumber = columnNumber;
        CorrectWordsSelector.potentialWords = potentialWords;
        CorrectWordsSelector.holderLetters = holderLetters;

        return considerWordsForBlocksOfLettersInColumn();
    }

    protected static ArrayList<Word> considerWordsForBlocksOfLettersInColumn() {
        ArrayList<Word> correctWords = new ArrayList<>();
        int startOfBlock = -1;
        int endOfBlock;
        for (int i = 0; i < column.length; i++) {
            if (isStartOfBlock(column, i)) startOfBlock = i;
            if (isEndOfBlock(column, i)) {
                endOfBlock = i;

                correctWords.addAll(getWordsPossibleToAddToBlock(startOfBlock, endOfBlock, potentialWords));
            }
        }
        return correctWords;
    }

    private static boolean isStartOfBlock(char[] column, int i) {
        return column[i] != empty && (i == 0 || column[i - 1] == empty);
    }

    private static boolean isEndOfBlock(char[] column, int i) {
        return column[i] != empty && (i == column.length - 1 || column[i + 1] == empty);
    }


    protected static ArrayList<Word> getWordsPossibleToAddToBlock(int startOfBlock, int endOfBlock, ArrayList<String> potentialWords) {
        ArrayList<Word> matchingWords = new ArrayList<>();

        for (String potentialWord : potentialWords) {
            int wordLength = potentialWord.length();

            if (wordLength <= endOfBlock - startOfBlock + 1) continue;

            for (int startingPoint = endOfBlock - wordLength + 1; startingPoint <= startOfBlock; startingPoint++) {
                if (startingPoint >= 0 && startingPoint + wordLength < board.length) {
                    Word word = new Word(potentialWord, columnNumber, startingPoint, Word.Direction.VERTICAL);
                    if (WordFitsChecker.doWordFits(word, board) && isEnoughLetters(word)) matchingWords.add(word);
                }
            }
        }
        return matchingWords;
    }

    protected static boolean isEnoughLetters(Word word) {
        ArrayList<Character> lettersToUse = new ArrayList<>();
        for (char letter : holderLetters) lettersToUse.add(letter);

        for (int i = 0; i < word.getLength(); i++) {
            char charAtBoard = column[i + word.getYBegin()];
            if (charAtBoard == empty) {
                int letterIndex = lettersToUse.indexOf(word.charAt(i));
                if (letterIndex == -1) return false;
                else lettersToUse.remove(letterIndex);
            }
        }
        return true;
    }
}
