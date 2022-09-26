package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.scrabble.Static;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.scrabble.resources.Bonuses;
import lombok.Data;

import java.util.List;

@Data
public class InfoDto {
    Integer holderSize;
    Integer boardSize;
    String emptySymbol;
    List<String> supportedLanguages;
    Bonuses.Bonus[][] bonuses;

    public InfoDto() {
        this.holderSize = Static.holderSize;
        this.boardSize = Static.boardSize;
        this.emptySymbol = String.valueOf(Alphabet.emptySymbol);
        this.supportedLanguages = Static.supportedLanguages;
        this.bonuses = Bonuses.getBonuses();
    }
}
