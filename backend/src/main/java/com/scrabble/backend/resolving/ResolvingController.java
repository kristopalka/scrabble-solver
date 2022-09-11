package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.ResolveRequestDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResolvingController {
    private final ResolvingService service;

    @GetMapping("/solve-scrabble")
    public @ResponseBody List<WordDto> bestWord(@RequestBody ResolveRequestDto request) {
        return service.bestWords(request).stream().map(WordDto::new).toList();
    }
}
