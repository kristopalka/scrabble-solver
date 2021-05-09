package com.ScrabbleMachine;

import com.ScrabbleMachine.Components.Board.Board;
import com.ScrabbleMachine.Components.Dictionary;
import com.ScrabbleMachine.Components.Holder.Holder;

import java.util.List;

public class Solver
{
    public static void doBestMove(Board board, Holder holder)
    {
        goThroughAllRows();
        goThroughAllCollumns();

    }

    private static void goThroughAllRows() {

    }

    private static void goThroughAllCollumns() {

    }



    public static String returnMissingLetters(String word, String letters)
    {
        int lettersPointer = 0;
        for (int wordPointer = 0; wordPointer < word.length(); wordPointer++)
        {
            while (word[wordPointer] > letters[lettersPointer])
                lettersPointer;

            if ()
        }
    }

}
