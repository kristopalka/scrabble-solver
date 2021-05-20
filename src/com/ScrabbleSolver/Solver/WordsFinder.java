package com.ScrabbleSolver.Solver;

import com.ScrabbleSolver.Scrabble.Board.StandardBoard;
import com.ScrabbleSolver.Scrabble.Holder.StandardHolder;
import com.ScrabbleSolver.Scrabble.Board.Components.Word;

import java.util.ArrayList;

public class WordsFinder {
    private static char[] holder;
    private static int length;


    public static ArrayList<Word> getAll(StandardBoard boardObject, StandardHolder holderObject) {
        char[][] board = boardObject.toCharArray();
        length = boardObject.getLength();
        holder = holderObject.toCharArray();


        ArrayList<Word> vertical = getVertical(board);

        ArrayList<Word> verticalToRotate = getVertical(transpose(board));
        ArrayList<Word> horizontal = rotateVerticalToHorizontal(verticalToRotate);


        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(vertical);
        allWords.addAll(horizontal);
        return allWords;
    }


    private static ArrayList<Word> getVertical(char[][] board) {
        ArrayList<Word> words = new ArrayList<>();
        for (int colNum = 0; colNum < length; colNum++) {
            ArrayList<String> potentialWords = PotentialWordsFinder.getAll(board[colNum], holder);
            ArrayList<Word> correctWords = selectCorrectWords(potentialWords);

            words.addAll(correctWords);
        }
        return words;
    }



    private static ArrayList<Word> selectCorrectWords(ArrayList<String> potentialWords) {
        ArrayList<Word> correctWords = new ArrayList<>();
        //todo wybierz te które: realnie da się ułożyć, nie kolidują z niczym
        //todo dodaj do globalnej possibleWords

        return correctWords;
    }


    public static char[][] transpose(char[][] board) {
        char[][] transposedBoard = new char[length][length];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static ArrayList<Word> rotateVerticalToHorizontal(ArrayList<Word> verticalToRotate) {
        ArrayList<Word> rotated = new ArrayList<>();
        //todo obracanie słów
        return rotated;
    }

}
