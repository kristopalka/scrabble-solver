package com.scrabble.backend.resolving;

import com.scrabble.backend.resolving.dto.ResolveRequestDto;
import com.scrabble.backend.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ResolvingController {
    private final ResolvingService service;

    @GetMapping("/best-word")
    public @ResponseBody WordDto bestWord(@RequestBody ResolveRequestDto request)  {
        return new WordDto(service.bestWord(request));
    }


}
