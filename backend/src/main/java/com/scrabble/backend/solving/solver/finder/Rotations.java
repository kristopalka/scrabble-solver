package com.scrabble.backend.solving.solver.finder;

import com.scrabble.backend.solving.scrabble.ScrabbleResources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rotations {

    public static char[][] transpose(char[][] board) {
        char[][] transposedBoard = new char[ScrabbleResources.boardSize][ScrabbleResources.boardSize];
        for (int x = 0; x < ScrabbleResources.boardSize; x++) {
            for (int y = 0; y < ScrabbleResources.boardSize; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    public static List<Word> rotateVerticalToHorizontal(List<Word> vertical) {
        List<Word> horizontal = new ArrayList<>();
        for (Word verWord : vertical) {
            Word horWord = rotateWord(verWord);

            for (Word additionalHorWord : verWord.additionalWords)
                horWord.additionalWords.add(rotateWord(additionalHorWord));

            horizontal.add(horWord);
        }
        return horizontal;
    }

    private static Word rotateWord(Word word) {
        return new Word(word.value, transposePoint(word.begin), word.direction.getOpposite(), transposePoint(word.entryBegin), word.entryLength);
    }

    public static Point transposePoint(Point point) {
        if (point == null) return null;
        return new Point(point.y, point.x);
    }
}
