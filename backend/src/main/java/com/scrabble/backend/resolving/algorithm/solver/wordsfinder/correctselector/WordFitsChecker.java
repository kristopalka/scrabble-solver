package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.Dictionary;

public class WordFitsChecker {
    protected static char[][] board;
    
    public static boolean doWordFits(Word word, char[][] board) {
        WordFitsChecker.board = board;
  
        return doLettersAgree(word) && doNotDisturbNeighborhood(word);
    }

    protected static boolean doLettersAgree(Word word) {
        for (int i = 0; i < word.length(); i++) {
            char charAtBoard = board[word.xBegin()][i + word.yBegin()];
            if (word.charAt(i) != charAtBoard && charAtBoard != Alphabet.getEmptySymbol()) return false;
        }
        return true;
    }

    protected static boolean doNotDisturbNeighborhood(Word word) {
        return notDisturbTheSides(word) && notDisturbUpOrDown(word);
    }

    protected static boolean notDisturbUpOrDown(Word word) {
        char empty = Alphabet.getEmptySymbol();

        if(word.yBegin() > 0) {
            if (board[word.xBegin()][word.yBegin() - 1] != empty) return false;
        }
        if(word.yBegin() + word.length() + 1 < board.length) {
            if (board[word.xBegin()][word.yEnd() + 1] != empty) return false;
        }
        return true;
    }

    protected static boolean notDisturbTheSides(Word word) {
        char empty = Alphabet.getEmptySymbol();

        for (int i = 0; i < word.length(); i++) {
            int yPos = i + word.yBegin();
            int xPos = word.xBegin();

            if(xPos != 0)
                if(board[xPos - 1][yPos] != empty)
                    if (!wordDisturbButStillFits(word, yPos)) return false;

            if(xPos != board.length-1)
                if(board[xPos + 1][yPos] != empty)
                    if (!wordDisturbButStillFits(word, yPos)) return false;
        }
        return true;
    }

    protected static boolean wordDisturbButStillFits(Word word, int yPos) {
        char empty = Alphabet.getEmptySymbol();
        int xStart = word.xBegin();

        while (xStart != 0 && board[xStart - 1][yPos] != empty) xStart--;

        StringBuilder newWordBuilder = new StringBuilder();
        for (int x = xStart; x < board.length && (board[x][yPos] != empty || x == word.xBegin()); x++) {
            if (x == word.xBegin()) newWordBuilder.append(word.charAt(yPos - word.yBegin()));
            else newWordBuilder.append(board[x][yPos]);
        }
        String newWord = newWordBuilder.toString();

        return Dictionary.containsWord(newWord);
    }
}
