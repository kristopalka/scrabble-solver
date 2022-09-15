package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.RequestDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ResolvingController {
    private final ResolvingService service;

    @PostMapping("/solve-scrabble")
    public @ResponseBody ResponseEntity<Object> bestWord(@RequestBody RequestDto request) {
        try {
            List<WordDto> words = service.bestWords(request).stream().map(WordDto::new).toList();
            return new ResponseEntity<>(words, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
