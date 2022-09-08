package com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Alphabet;
import com.scrabble.backend.api.resolving.algorithm.scrabble.dictionary.Dictionary;

public class WordFitsChecker {
    protected static char[][] board;
    
    public static boolean doWordFits(Word word, char[][] board) {
        WordFitsChecker.board = board;
  
        return doLettersAgree(word) && doNotDisturbNeighborhood(word);
    }

    protected static boolean doLettersAgree(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            char charAtBoard = board[word.getXBegin()][i + word.getYBegin()];
            if (word.charAt(i) != charAtBoard && charAtBoard != Alphabet.getEmptySymbol()) return false;
        }
        return true;
    }

    protected static boolean doNotDisturbNeighborhood(Word word) {
        return notDisturbTheSides(word) && notDisturbUpOrDown(word);
    }

    protected static boolean notDisturbUpOrDown(Word word) {
        char empty = Alphabet.getEmptySymbol();

        if(word.getYBegin() > 0) {
            if (board[word.getXBegin()][word.getYBegin() - 1] != empty) return false;
        }
        if(word.getYBegin() + word.getLength() + 1 < board.length) {
            if (board[word.getXBegin()][word.getYEnd() + 1] != empty) return false;
        }
        return true;
    }

    protected static boolean notDisturbTheSides(Word word) {
        char empty = Alphabet.getEmptySymbol();

        for (int i = 0; i < word.getLength(); i++) {
            int yPos = i + word.getYBegin();

            if ((word.getXBegin() != 0 && board[word.getXBegin() - 1][yPos] != empty) ||
                    (word.getXBegin() != board.length && board[word.getXBegin() + 1][yPos] != empty)) {
                if (!wordDisturbButStillFits(word, yPos)) return false;
            }
        }
        return true;
    }

    protected static boolean wordDisturbButStillFits(Word word, int yPos) {
        char empty = Alphabet.getEmptySymbol();
        int xStart = word.getXBegin();

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
