package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.Alphabet;
import com.scrabble.backend.resolving.algorithm.settings.Dictionary;

public class SurroundingFiltering {
    private static final char empty = Alphabet.getEmptySymbol();
    protected final char[][] board;

    public SurroundingFiltering(char[][] board) {
        this.board = board;
    }

    public boolean doFits(Word word) {
        return doLettersAgree(word) && doNotDisturbNeighborhood(word);
    }

    public boolean doLettersAgree(Word word) {
        for (int i = 0; i < word.length(); i++) {
            char charAtBoard = board[word.xBegin()][i + word.yBegin()];
            if (word.charAt(i) != charAtBoard && charAtBoard != empty) return false;
        }
        return true;
    }

    public boolean doNotDisturbNeighborhood(Word word) {
        return notDisturbTheSides(word) && notDisturbUpOrDown(word);
    }

    public boolean notDisturbUpOrDown(Word word) {
        if (word.yBegin() > 0) {
            if (board[word.xBegin()][word.yBegin() - 1] != empty) return false;
        }
        if (word.yBegin() + word.length() + 1 < board.length) {
            if (board[word.xBegin()][word.yEnd() + 1] != empty) return false;
        }
        return true;
    }

    public boolean notDisturbTheSides(Word word) {
        for (int i = 0; i < word.length(); i++) {
            if (thisIsEntryPoint(word, i)) continue;

            int yPos = i + word.yBegin();
            int xPos = word.xBegin();

            if (isSomethingOnLeft(yPos, xPos) || isSomethingOnRight(yPos, xPos))
                if (!wordDisturbButStillFits(word, yPos)) return false;
        }
        return true;
    }

    private boolean isSomethingOnRight(int yPos, int xPos) {
        return xPos != board.length - 1 && board[xPos + 1][yPos] != empty;
    }

    private boolean isSomethingOnLeft(int yPos, int xPos) {
        return xPos != 0 && board[xPos - 1][yPos] != empty;
    }

    private boolean thisIsEntryPoint(Word word, int yPos) {
        int absolutePos = word.yBegin() + yPos;
        return absolutePos >= word.entryBegin.y && absolutePos <= word.entryBegin.y + word.entryLength - 1;
    }

    public boolean wordDisturbButStillFits(Word word, int yPos) {
        Word newWord = extractDisturbedWord(word, yPos);

        if (Dictionary.containsWord(newWord.value)) {
            word.additionalWords.add(newWord);
            return true;
        }
        return false;
    }

    private Word extractDisturbedWord(Word word, int yPos) {
        int xStart = word.xBegin();

        while (xStart != 0 && board[xStart - 1][yPos] != empty) xStart--;

        StringBuilder newWordBuilder = new StringBuilder();
        for (int x = xStart; x < board.length && (board[x][yPos] != empty || x == word.xBegin()); x++) {
            if (x == word.xBegin()) newWordBuilder.append(word.charAt(yPos - word.yBegin()));
            else newWordBuilder.append(board[x][yPos]);
        }
        return new Word(newWordBuilder.toString(), xStart, yPos, Word.Direction.HORIZONTAL);
    }
}
