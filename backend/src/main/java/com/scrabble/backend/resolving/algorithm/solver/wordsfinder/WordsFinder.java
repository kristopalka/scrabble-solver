package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;
import com.scrabble.backend.resolving.algorithm.solver.wordsfinder.possibleselector.DictionaryFinder;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector.CorrectWordsSelector.selectCorrectWords;


public class WordsFinder {
    private static final int size = ScrabbleSettings.getBoardSize();

    public static ArrayList<Word> getVerticalAndHorizontal(char[][] board, char[] holder) {
        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(getVertical(board, holder));
        allWords.addAll(getHorizontal(board, holder));

        return allWords;
    }


    public static ArrayList<Word> getVertical(char[][] board, char[] holder) {
        ArrayList<Word> words = new ArrayList<>();
        for (int colNum = 0; colNum < size; colNum++) {
            ArrayList<String> potentialWords = DictionaryFinder.getPotentialWordsForColumn(board[colNum], holder);
            ArrayList<Word> correctWords = selectCorrectWords(board, holder, colNum, potentialWords);
            words.addAll(correctWords);
        }
        return words;

//        List<Word> list = IntStream.range(0, board.length)
//                .mapToObj(colNum -> getWords(board, holder, colNum))
//                .flatMap(List::stream)
//                .toList();
//        return new ArrayList<>(list);
    }

//    private static ArrayList<Word> getWords(char[][] board, char[] holder, int colNum) {
//        ArrayList<String> potentialWords = getWordsPossibleToArrangeInColumn(board[colNum], holder);
//        return selectCorrectWords(board, holder, colNum, potentialWords);
//    }

    private static ArrayList<Word> getHorizontal(char[][] board, char[] holder) {
        ArrayList<Word> verticalToRotate = getVertical(transpose(board), holder);
        return rotateVerticalToHorizontal(verticalToRotate);
    }


    private static char[][] transpose(char[][] board) {
        char[][] transposedBoard = new char[WordsFinder.size][WordsFinder.size];
        for (int x = 0; x < WordsFinder.size; x++) {
            for (int y = 0; y < WordsFinder.size; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static ArrayList<Word> rotateVerticalToHorizontal(ArrayList<Word> verticalToRotate) {
        return verticalToRotate.stream()
                .map(word -> new Word(word.getValue(), word.getYBegin(), word.getXBegin(), Word.Direction.HORIZONTAL))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
