package com.scrabble.backend.algorithm.solver.wordsfinder;

import com.scrabble.backend.algorithm.scrabble.Word;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HorizontalWordFinder {
    public static ArrayList<Word> getHorizontal(char[][] board, char[] holder, int size) {
        ArrayList<Word> verticalToRotate = VerticalWordsFinder.getVertical(transpose(board, size), holder, size);
        return rotateVerticalToHorizontal(verticalToRotate);
    }

    private static char[][] transpose(char[][] board, int size) {
        char[][] transposedBoard = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static ArrayList<Word> rotateVerticalToHorizontal(ArrayList<Word> verticalToRotate) {
        return verticalToRotate
                .stream().map(word -> new Word(
                        word.getValue(),
                        word.getYBegin(),
                        word.getXBegin(),
                        Word.Direction.HORIZONTAL))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
