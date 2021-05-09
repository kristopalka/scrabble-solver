package com.ScrabbleMachine.Components.Board;
import com.ScrabbleMachine.Components.Alphabet;
import java.security.InvalidParameterException;

public class Field
{
    Bonuses bonus;
    private char value;

    public Field(Bonuses bonus)
    {
        this.value = Alphabet.getEmptySymbol();
        this.bonus = bonus;
    }

    public Bonuses getBonus()
    {
        return bonus;
    }


    public char getValue()
    {
        return value;
    }

    public void setValue(char value)
    {
        if (!Alphabet.isAllowedCharacter(value)) throw new InvalidParameterException("This is not a letter");
        this.value = value;
    }

    public boolean isEmpty()
    {
        return Alphabet.isEmptySymbol(this.value);
    }

}
