package com.scrabblewinner.scrabble.holder;

import java.util.ArrayList;

public class StandardHolder extends Holder {
    public StandardHolder() {
        this.maxLettersNumber = 7;
        this.letters = new ArrayList<>();
    }
}
