package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.SolvingService;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.resources.Alphabet;
import com.scrabble.backend.solving.scrabble.resources.Bonuses;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoDto {
    private Integer holderSize;
    private Integer boardSize;
    private String emptySymbol;
    private List<String> modes;
    private List<String> langs;
    private Bonuses.Bonus[][] boardBonuses;
    private List<LettersValuesDto> lettersValues;

    public InfoDto() {
        this.holderSize = ScrabbleResources.holderSize;
        this.boardSize = ScrabbleResources.boardSize;
        this.emptySymbol = String.valueOf(Alphabet.emptySymbol);
        this.modes = List.of(SolvingService.modes);
        this.langs = ScrabbleResources.supportedLanguages;
        this.boardBonuses = Bonuses.getBonuses();

        this.lettersValues = new ArrayList<>();
        for (String lang : langs) {
            this.lettersValues.add(new LettersValuesDto(lang));
        }
    }
}
