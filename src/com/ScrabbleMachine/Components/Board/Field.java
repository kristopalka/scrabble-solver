package com.ScrabbleMachine.Components.Board;

import com.ScrabbleMachine.Components.Alphabet;

import java.security.InvalidParameterException;

public class Field
{
    private int bonusType;
    private char value;

    public Field()
    {
        this.value = Alphabet.getEmptySymbol();
    }

    public int getBonusType()
    {
        return bonusType;
    }

    public void setBonusType(int bonusType)
    {
        if(!Bonus.isBonusValue(bonusType)) throw new InvalidParameterException("Invalid bonus type");
        else this.bonusType = bonusType;
    }

    public char getValue()
    {
        return value;
    }

    public void setValue(char value)
    {
        value = ("" + value).toUpperCase().charAt(0);

        if (!(Alphabet.isLetter(value) || !Alphabet.isEmptySymbol(value))) throw new InvalidParameterException("This is not a letter");
        this.value = value;
    }

    public boolean isEmpty()
    {
        if (Alphabet.isEmptySymbol(this.value)) return true;
        return false;
    }

}
