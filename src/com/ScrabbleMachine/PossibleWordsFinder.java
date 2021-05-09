package com.ScrabbleMachine;

import com.ScrabbleMachine.Components.Board.StandardBoard;
import com.ScrabbleMachine.Components.Holder.Holder;
import com.ScrabbleMachine.Components.Word;

import java.util.ArrayList;
import java.util.Collection;

public class PossibleWordsFinder {
    private static char[] holder;
    private static int length;

    private static ArrayList<Word> possibleWords;

    public static ArrayList<Word> getAll(StandardBoard boardObject, Holder holderObject) {
        char[][] board = boardObject.toCharArray();
        length = boardObject.getLength();
        holder = holderObject.toCharArray();

        possibleWords = new ArrayList<>();

        findAndAddVertical(board);
        board = transpose(board);
        findAndAddVertical(board);

        return possibleWords;
    }

    private static void findAndAddVertical(char[][] board) {
        for (int colNum = 0; colNum < length; colNum++) {
            ArrayList<String> potentialWords = getPotentialFromColumn();
            addWordsMeetingRules(potentialWords);

        }
    }


    private static ArrayList<String> getPotentialFromColumn() {
        ArrayList<String> potentialWords = new ArrayList<>();
        //todo słowa które da się ułożyć z liter które są

        return potentialWords;
    }

    private static void addWordsMeetingRules(ArrayList<String> potentialWords) {
        //todo wybierz te które: realnie da się ułożyć, nie kolidują z niczym
        //todo dodaj do globalnej possibleWords
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


}
