package com.scrabble.backend.api.resolving;

import com.scrabble.backend.api.resolving.dto.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ResolvingController {
    private final ResolvingService service;

    @GetMapping("/best-word")
    public @ResponseBody WordDto bestWord(char[][] board)  {
        return null;
    }


}
