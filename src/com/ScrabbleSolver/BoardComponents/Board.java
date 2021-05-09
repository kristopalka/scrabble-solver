package com.ScrabbleSolver.BoardComponents;

import java.security.InvalidParameterException;

public abstract class Board
{
    int length = 15;
    Field[][] fields = new Field[length][length];

    public void addWordHorizontally(String word, int xStart, int yStart)
    {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length) throw new InvalidParameterException("Start parameter out of range");
        if (xStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off board");

        for (int x = 0; x < word.length(); x++)
        {
            ifOverrideWarming(x, yStart, word.charAt(x));
            fields[x + xStart][yStart].setValue(word.charAt(x));
        }
    }

    public void addWordVertically(String word, int xStart, int yStart)
    {
        if (xStart < 0 || xStart >= length || yStart < 0 || yStart >= length) throw new InvalidParameterException("Start parameter out of range");
        if (yStart + word.length() >= length + 1) throw new InvalidParameterException("Word goes off scale");

        for (int y = 0; y < word.length(); y++)
        {
            ifOverrideWarming(xStart, y, word.charAt(y));
            fields[xStart][yStart + y].setValue(word.charAt(y));
        }
    }

    private void ifOverrideWarming(int x, int y, char targetLetter)
    {
        if (!fields[x][y].isEmpty())
        {
            if (Alphabet.areEquals(fields[x][y].getValue(),targetLetter))
            {
                System.err.printf("Overrided field '%c'->'%c' (%d,%d)\n", fields[x][y].getValue(), targetLetter, y, x);
            }
        }
    }



    public void printBoard()
    {
        for (int y = 0; y<length; y++)
        {
            for (int x = 0; x<length; x++)
            {
                System.out.print(fields[x][y].getValue()+ " ");
            }
            System.out.println("");
        }
    }

}
