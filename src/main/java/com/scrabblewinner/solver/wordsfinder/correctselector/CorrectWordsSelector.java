package com.scrabblewinner.solver.wordsfinder.correctselector;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.Word;

import java.util.ArrayList;

public class CorrectWordsSelector {
    protected static char[][] board;
    protected static int columnNumber;
    protected static ArrayList<String> potentialWords;

    public static ArrayList<Word> select(char[][] board, int columnNumber, ArrayList<String> potentialWords) {
        CorrectWordsSelector.board = board;
        CorrectWordsSelector.columnNumber = columnNumber;
        CorrectWordsSelector.potentialWords = potentialWords;

        return getAllMatchingWords();
    }

    protected static ArrayList<Word> getAllMatchingWords() {
        ArrayList<Word> correctWords = new ArrayList<>();
        int startOfBlock = -1;
        int endOfBlock = -1;
        for (int i = 0; i < board[columnNumber].length; i++) {
            if (startsBlock(i)) startOfBlock = i;
            if (endsBlock(i)) {
                endOfBlock = i;

                correctWords.addAll(getAllMatchingWordsForBlockOfLetters(startOfBlock, endOfBlock));
            }
        }
        return correctWords;
    }

    private static boolean startsBlock(int i) {
        return board[columnNumber][i] != Alphabet.getEmptySymbol() && (i == 0 || board[columnNumber][i - 1] == Alphabet.getEmptySymbol());
    }

    private static boolean endsBlock(int i) {
        return board[columnNumber][i] != Alphabet.getEmptySymbol() && (i == board[columnNumber].length - 1 || board[columnNumber][i + 1] == Alphabet.getEmptySymbol());
    }


    protected static ArrayList<Word> getAllMatchingWordsForBlockOfLetters(int startOfBlock, int endOfBlock) {
        ArrayList<Word> matchingWords = new ArrayList<>();

        for (String potentialWord : potentialWords) {
            int wordLength = potentialWord.length();

            if (wordLength <= endOfBlock - startOfBlock + 1) continue;

            for (int startingPoint = endOfBlock - wordLength + 1; startingPoint <= startOfBlock; startingPoint++) {
                if (startingPoint >= 0 && startingPoint + wordLength < board[columnNumber].length) {
                    Word word = new Word(potentialWord, columnNumber, startingPoint, Word.Direction.VERTICAL);
                    if (WordFitsChecker.doWordFits(word, board)) matchingWords.add(word);
                }
            }
        }
        return matchingWords;
    }


}
