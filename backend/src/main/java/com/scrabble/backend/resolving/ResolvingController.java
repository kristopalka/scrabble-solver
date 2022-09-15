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
    public @ResponseBody List<WordDto> bestWord(@RequestBody GameStateDto request,
                                                @RequestParam(defaultValue = "points") String mode,
                                                @RequestParam(defaultValue = "5") Integer number) {
        return service.bestWords(request, mode, number)
                .stream().map(WordDto::new).toList();
    }
}
