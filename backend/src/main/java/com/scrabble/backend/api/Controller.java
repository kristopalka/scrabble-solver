package com.scrabble.backend.api;

import com.scrabble.backend.api.dto.GameStateDto;
import com.scrabble.backend.api.dto.InfoDto;
import com.scrabble.backend.api.dto.LettersValuesDto;
import com.scrabble.backend.api.dto.WordDto;
import com.scrabble.backend.image_processing.ImageProcessingService;
import com.scrabble.backend.solving.SolvingService;
import com.scrabble.backend.solving.solver.finder.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final SolvingService solvingService;
    private final ImageProcessingService imageProcessingService;


    @PostMapping(value = "/image-to-text")
    public @ResponseBody ResponseEntity<String> imageToText(@RequestBody String base64Image,
                                                            @RequestParam(defaultValue = "en") String lang) throws IOException {
        // byte[] binaryImage = Base64.decodeBase64(base64Image);
        // return new ResponseEntity<>(imageProcessingService.imageToText(binaryImage), HttpStatus.OK);
        String response = "{\"board\": [[\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \"M\", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \"Z\", \"A\", \"B\", \"I\", \"A\", \" \", \"O\", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \"M\", \" \", \" \", \" \", \"O\", \" \", \" \", \"R\", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \"L\", \" \", \" \", \" \", \"T\", \" \", \" \", \"D\", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \"S\", \"L\", \"O\", \"N\", \"E\", \"C\", \"Z\", \"K\", \"O\"], [\" \", \" \", \" \", \" \", \" \", \" \", \"L\", \" \", \" \", \" \", \"K\", \" \", \" \", \"A\", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \"U\", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"], [\" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \", \" \"]]}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/solve-scrabble")
    public @ResponseBody ResponseEntity<Object> bestWord(
            @RequestBody GameStateDto request,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "score") String mode,
            @RequestParam(defaultValue = "10") Integer number) {
        try {
            List<Word> words = solvingService.bestWords(request, lang, mode, number);
            List<WordDto> wordsDto = words.stream().map(WordDto::new).toList();
            return new ResponseEntity<>(wordsDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public @ResponseBody ResponseEntity<InfoDto> info() {
        return new ResponseEntity<>(new InfoDto(), HttpStatus.OK);
    }

    @GetMapping("/letters-values")
    public @ResponseBody ResponseEntity<LettersValuesDto> lettersValues(@RequestParam(defaultValue = "en") String lang) {
        return new ResponseEntity<>(new LettersValuesDto(lang), HttpStatus.OK);
    }
}
