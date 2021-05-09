package com.ScrabbleMachine;

import com.ScrabbleMachine.Components.Board.Board;
import com.ScrabbleMachine.Components.Board.StandardBoard;
import com.ScrabbleMachine.Components.Holder.Holder;
import com.ScrabbleMachine.Components.Word;

import java.util.ArrayList;


public class Solver
{
    public static void doBestMove(StandardBoard board, Holder holder)
    {
        ArrayList<Word> possibleWords = PossibleWordsFinder.getAll(board, holder);
        //todo calculate points for each word
        //todo sort and return
    }





//    public static String returnMissingLetters(String word, String letters)
//    {
//        int lettersPointer = 0;
//        for (int wordPointer = 0; wordPointer < word.length(); wordPointer++)
//        {
//            while (word[wordPointer] > letters[lettersPointer])
//                lettersPointer;
//
//            if ()
//        }
//    }

}
