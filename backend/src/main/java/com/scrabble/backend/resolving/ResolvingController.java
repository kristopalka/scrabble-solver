package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.GameStateDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResolvingController {
    private final ResolvingService service;

    @PostMapping("/solve-scrabble")
    public @ResponseBody List<WordDto> bestWord(@RequestBody GameStateDto request) {
        return service.bestWords(request).stream().map(WordDto::new).toList();
    }
}
