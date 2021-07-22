package com.scrabblewinner.solver.finder;

import com.scrabblewinner.scrabble.board.components.Direction;
import com.scrabblewinner.scrabble.board.components.Word;

import java.util.ArrayList;

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
        verticalToRotate
                .forEach(word -> {
                    word.direction = Direction.HORIZONTAL;
                    int xStart = word.xStart;
                    word.xStart = word.yStart;
                    word.yStart = xStart;
                });
        return verticalToRotate;
    }

}
