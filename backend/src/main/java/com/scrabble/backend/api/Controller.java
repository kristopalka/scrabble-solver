package com.scrabble.backend.api;

import com.scrabble.backend.api.dto.GameStateDto;
import com.scrabble.backend.api.dto.ImageAndPointsDto;
import com.scrabble.backend.api.dto.InfoDto;
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


    @PostMapping(value = "/find-corners")
    public @ResponseBody ResponseEntity<String> findCorners(@RequestBody String base64Image) throws IOException {
        String output = imageProcessingService.findCorners(base64Image);

        if (output.contains("NOT_FOUND"))
            return new ResponseEntity<>("Not found board on photo", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(output, HttpStatus.OK);
    }


    @PostMapping(value = "/crop-and-recognize")
    public @ResponseBody ResponseEntity<String> cropAndRecognize(@RequestBody ImageAndPointsDto imagePoints,
                                                                 @RequestParam(defaultValue = "en") String lang) throws IOException {
        return new ResponseEntity<>(imageProcessingService.cropAndRecognize(imagePoints, lang), HttpStatus.OK);
    }


    @PostMapping("/solve")
    public @ResponseBody ResponseEntity<Object> bestWord(
            @RequestBody GameStateDto request,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "score") String mode,
            @RequestParam(defaultValue = "10") Integer number) {
        List<Word> words = solvingService.bestWords(request, lang, mode, number);
        List<WordDto> wordsDto = words.stream().map(WordDto::new).toList();
        return new ResponseEntity<>(wordsDto, HttpStatus.OK);
    }

    @GetMapping("/info")
    public @ResponseBody ResponseEntity<InfoDto> info() {
        return new ResponseEntity<>(new InfoDto(), HttpStatus.OK);
    }

}
