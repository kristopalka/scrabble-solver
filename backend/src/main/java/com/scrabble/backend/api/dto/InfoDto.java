package com.scrabble.backend.api.dto;

import com.scrabble.backend.solving.SolvingService;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import com.scrabble.backend.solving.scrabble.Alphabet;
import com.scrabble.backend.solving.scrabble.Bonuses;
import lombok.Data;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoDto {
    private Integer rackSize;
    private Integer boardSize;
    private String emptySymbol;
    private List<String> modes;
    private List<String> langs;
    private Bonuses.Bonus[][] boardBonuses;
    private List<LettersValuesDto> lettersValues;

    public InfoDto() {
        this.rackSize = ScrabbleResources.rackSize;
        this.boardSize = ScrabbleResources.boardSize;
        this.emptySymbol = String.valueOf(Alphabet.emptySymbol);
        this.modes = List.of(SolvingService.modes);
        this.langs = ScrabbleResources.supportedLanguages;
        this.boardBonuses = ScrabbleResources.bonuses.getBonuses();

        this.lettersValues = new ArrayList<>();
        for (String lang : langs) {
            this.lettersValues.add(new LettersValuesDto(lang));
        }
    }
}
