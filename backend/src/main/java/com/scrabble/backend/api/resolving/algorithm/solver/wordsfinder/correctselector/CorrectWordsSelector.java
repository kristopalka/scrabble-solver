package com.scrabble.backend.api.resolving.algorithm.solver.wordsfinder.correctselector;

import com.scrabble.backend.api.resolving.algorithm.scrabble.Word;
import com.scrabble.backend.api.resolving.algorithm.scrabble.Alphabet;

import java.util.*;

public class CorrectWordsSelector {
    protected static char[][] board;
    protected static char[] column;
    protected static int boardSize;
    protected static int columnNumber;
    protected static char[] holder;

    protected static ArrayList<String> potentialStrings;

    public static ArrayList<Word> select(char[][] board, char[] holder, int columnNumber, ArrayList<String> potentialWords) {
        CorrectWordsSelector.board = board;
        CorrectWordsSelector.column = board[columnNumber];
        CorrectWordsSelector.boardSize = board.length;
        CorrectWordsSelector.columnNumber = columnNumber;
        CorrectWordsSelector.potentialStrings = potentialWords;
        CorrectWordsSelector.holder = holder;

        return considerWordsForBlocksOfLettersInColumn();
    }

    protected static ArrayList<Word> considerWordsForBlocksOfLettersInColumn() {
        ArrayList<Word> correctWords = new ArrayList<>();
        int startOfBlock = -1;
        int endOfBlock = -1;
        for (int i = 0; i < boardSize; i++) {
            if (startsBlock(i)) startOfBlock = i;
            if (endsBlock(i)) {
                endOfBlock = i;

                correctWords.addAll(getAllMatchingWords(startOfBlock, endOfBlock));
            }
        }
        return correctWords;
    }

    private static boolean startsBlock(int i) {
        return column[i] != Alphabet.getEmptySymbol() && (i == 0 || column[i - 1] == Alphabet.getEmptySymbol());
    }

    private static boolean endsBlock(int i) {
        return column[i] != Alphabet.getEmptySymbol() && (i == boardSize - 1 || column[i + 1] == Alphabet.getEmptySymbol());
    }


    protected static ArrayList<Word> getAllMatchingWords(int startOfBlock, int endOfBlock) {
        ArrayList<Word> matchingWords = new ArrayList<>();

        for (String potentialString : potentialStrings) {
            int wordLength = potentialString.length();

            if (wordLength <= endOfBlock - startOfBlock + 1) continue;

            for (int startingPoint = endOfBlock - wordLength + 1; startingPoint <= startOfBlock; startingPoint++) {
                if (startingPoint >= 0 && startingPoint + wordLength < boardSize) {
                    Word word = new Word(potentialString, columnNumber, startingPoint, Word.Direction.VERTICAL);
                    if (WordFitsChecker.doWordFits(word, board) && isEnoughLetters(word)) matchingWords.add(word);
                }
            }
        }
        return matchingWords;
    }

    protected static boolean isEnoughLetters(Word word) {
        ArrayList<Character> lettersToUse = new ArrayList<>();
        for (char letter : holder) lettersToUse.add(letter);

        for (int i = 0; i < word.getLength(); i++) {
            char charAtBoard = board[columnNumber][i + word.getYBegin()];
            if (charAtBoard == Alphabet.getEmptySymbol()) {
                int letterIndex = lettersToUse.indexOf(word.charAt(i));
                if (letterIndex == -1) return false;
                else lettersToUse.remove(letterIndex);
            }
        }
        return true;
    }
}
