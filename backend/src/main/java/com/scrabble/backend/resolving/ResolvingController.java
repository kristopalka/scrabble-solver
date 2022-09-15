package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.GameStateDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ResolvingController {
    private final ResolvingService service;

    @PostMapping("/solve-scrabble")
    public @ResponseBody List<WordDto> bestWord(@RequestBody GameStateDto request,
                                                @RequestParam(defaultValue = "points") String mode,
                                                @RequestParam(defaultValue = "5") Integer number) {

        try {
            return service.bestWords(request, mode, number).stream().map(WordDto::new).toList();
        } catch (InvalidParameterException e) {
            log.error("Error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request", e);
        }
    }
}
