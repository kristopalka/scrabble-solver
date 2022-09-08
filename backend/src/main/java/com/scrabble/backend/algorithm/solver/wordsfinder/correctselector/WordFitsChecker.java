package com.scrabble.backend.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.algorithm.scrabble.Word;
import com.scrabble.backend.algorithm.scrabble.Alphabet;
import com.scrabble.backend.algorithm.scrabble.dictionary.Dictionary;

public class WordFitsChecker {
    protected static char[][] board;
    protected static int columnNumber;

    public static boolean doWordFits(Word word, char[][] board) {
        WordFitsChecker.board = board;
        WordFitsChecker.columnNumber = word.getXBegin();

        return doLettersAgree(word) && !doWordDisturbNeighborhood(word);
    }

    protected static boolean doLettersAgree(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            char charAtBoard = board[columnNumber][i + word.getYBegin()];
            if (word.charAt(i) != charAtBoard && charAtBoard != Alphabet.getEmptySymbol()) return false;
        }
        return true;
    }

    protected static boolean doWordDisturbNeighborhood(Word word) {
        return onTheSides(word) || onUpOrDown(word);
    }

    protected static boolean onUpOrDown(Word word) {
        char empty = Alphabet.getEmptySymbol();

        if(word.getYBegin() > 0) {
            if (board[word.getXBegin()][word.getYBegin() - 1] != empty) return true;
        }
        if(word.getYBegin() + word.getLength() + 1 < board.length) {
            if (board[word.getXBegin()][word.getYEnd() + 1] != empty) return true;
        }
        return false;
    }

    protected static boolean onTheSides(Word word) {
        char empty = Alphabet.getEmptySymbol();

        for (int i = 0; i < word.getLength(); i++) {
            int yPos = i + word.getYBegin();

            if ((columnNumber != 0 && board[columnNumber - 1][yPos] != empty) ||
                    (columnNumber != board.length && board[columnNumber + 1][yPos] != empty)) {
                if (!wordDisturbButStillFits(word, yPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static boolean wordDisturbButStillFits(Word word, int yPos) {
        char empty = Alphabet.getEmptySymbol();
        int xStart = columnNumber;

        while (xStart != 0 && board[xStart - 1][yPos] != empty) xStart--;

        StringBuilder newWordBuilder = new StringBuilder();
        for (int x = xStart; x < board.length && (board[x][yPos] != empty || x == word.getXBegin()); x++) {
            if (x == word.getXBegin()) newWordBuilder.append(word.charAt(yPos - word.getYBegin()));
            else newWordBuilder.append(board[x][yPos]);
        }
        String newWord = newWordBuilder.toString();

        return Dictionary.containsWord(newWord);
    }
}
