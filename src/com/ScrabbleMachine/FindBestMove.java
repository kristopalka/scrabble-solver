package com.ScrabbleMachine;

import com.ScrabbleMachine.Components.Board.Board;
import com.ScrabbleMachine.Components.Dictionary;
import com.ScrabbleMachine.Components.Holder.Holder;

import java.util.List;

public class FindBestMove
{
    public static void doBestMove(Board board, Holder holder)
    {
        List<String> dictionary = Dictionary.getAll();

        for (String word:dictionary)
        {
            findIfItSuitAnywhere(word);
        }
    }

    private static void findIfItSuitAnywhere(String word)
    {

    }

}
