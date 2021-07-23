package com.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.dictionary.Dictionary;

public class WordFitsChecker {
    protected static char[][] board;
    protected static int columnNumber;

    public static boolean doWordFits(Word word, char[][] board) {
        WordFitsChecker.board = board;
        WordFitsChecker.columnNumber = word.xStart;

        return doLettersAgree(word) && !doWordDisturbNeighborhood(word);
    }

    protected static boolean doLettersAgree(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            char charAtBoard = board[columnNumber][i + word.yStart];
            if (word.charAt(i) != charAtBoard && charAtBoard != Alphabet.getEmptySymbol()) return false;
        }
        return true;
    }

    protected static boolean doWordDisturbNeighborhood(Word word) {
        char empty = Alphabet.getEmptySymbol();

        for (int i = 0; i < word.getLength(); i++) {
            int yPos = i + word.yStart;


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
        for (int x = xStart; x < board.length && (board[x][yPos] != empty || x == word.xStart); x++) {
            if (x == word.xStart) newWordBuilder.append((char) word.charAt(yPos - word.yStart));
            else newWordBuilder.append(board[x][yPos]);
        }
        String newWord = newWordBuilder.toString();

        return Dictionary.containsWord(newWord);
    }
}
