package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.RequestDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ResolvingController {
    private final ResolvingService service;

    @PostMapping("/solve-scrabble")
    public @ResponseBody List<WordDto> bestWord(@RequestBody RequestDto request) {

        try {
            return service.bestWords(request).stream().map(WordDto::new).toList();
        } catch (IllegalArgumentException e) {
            log.error("Error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
