package com.scrabblewinner.solver.wordsfinder;

import com.scrabblewinner.scrabble.alphabet.Alphabet;
import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;
import com.scrabblewinner.scrabble.dictionary.Dictionary;

import java.util.ArrayList;

public class CorrectWordsSelector {
    protected static char[][] board;
    protected static int columnNumber;
    protected static ArrayList<String> potentialWords;

    public static ArrayList<Word> select(char[][] board, int columnNumber, ArrayList<String> potentialWords) {
        CorrectWordsSelector.board = board;
        CorrectWordsSelector.columnNumber = columnNumber;
        CorrectWordsSelector.potentialWords = potentialWords;

        return getAllMatchingWordsForAllBlocksOfLetters();
    }

    protected static ArrayList<Word> getAllMatchingWordsForAllBlocksOfLetters() {
        ArrayList<Word> correctWords = new ArrayList<>();
        int startOfBlock = -1;
        int endOfBlock = -1;
        for (int i = 0; i < board[columnNumber].length; i++) {
            if (startsBlock(i)) startOfBlock = i;
            if (endsBlock(i)) {
                endOfBlock = i;

                correctWords.addAll(getAllMatchingWords(startOfBlock, endOfBlock));
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


    protected static ArrayList<Word> getAllMatchingWords(int startOfBlock, int endOfBlock) {
        ArrayList<Word> words = new ArrayList<>();

        for (String potentialWord : potentialWords) {
            int wordLength = potentialWord.length();

            for (int startingPoint = endOfBlock - wordLength + 1; startingPoint <= startOfBlock; startingPoint++) {
                if (startingPoint >= 0 && startingPoint + wordLength < board[columnNumber].length) {
                    Word word = new Word(potentialWord, columnNumber, startingPoint, Direction.VERTICAL);
                    if (WordFitsChecker.doWordFits(word, board)) words.add(word);
                }
            }
        }
        return words;
    }



}
