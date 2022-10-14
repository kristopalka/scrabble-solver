package com.scrabble.backend.api;

import com.scrabble.backend.api.dto.GameStateDto;
import com.scrabble.backend.api.dto.ImagePointsDto;
import com.scrabble.backend.api.dto.InfoDto;
import com.scrabble.backend.api.dto.WordDto;
import com.scrabble.backend.image_processing.ImageProcessingService;
import com.scrabble.backend.solving.SolvingService;
import com.scrabble.backend.solving.solver.finder.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
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
        byte[] binaryImage = Base64.decodeBase64(base64Image);
        return new ResponseEntity<>(imageProcessingService.findCorners(binaryImage), HttpStatus.OK);
    }


    @PostMapping(value = "/crop-and-recognize")
    public @ResponseBody ResponseEntity<String> cropAndRecognize(@RequestBody ImagePointsDto imagePoints,
                                                                 @RequestParam(defaultValue = "en") String lang) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(imagePoints.getBase64Image());
        return new ResponseEntity<>(imageProcessingService.cropAndRecognize(binaryImage, imagePoints.getCorners(), lang), HttpStatus.OK);
    }


    @PostMapping("/solve-scrabble")
    public @ResponseBody ResponseEntity<Object> bestWord(
            @RequestBody GameStateDto request,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "score") String mode,
            @RequestParam(defaultValue = "10") Integer number) {
        try {
            List<Word> words = solvingService.bestWords(request, lang, mode, number);
            if (words.size() == 0) return new ResponseEntity<>("Not found words", HttpStatus.BAD_REQUEST);

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

}
