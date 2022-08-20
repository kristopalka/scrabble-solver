package com.scrabble.backend;

import com.scrabble.backend.dto.PointsDto;
import com.scrabble.backend.util.PythonExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService service;

    @GetMapping("/test")
    public @ResponseBody String test() throws IOException {
        return PythonExecutor.executeScript("test.py", "world");
    }

    @PostMapping(value = "/corners-detection")
    public @ResponseBody String cornersDetection(@RequestParam MultipartFile image) throws IOException {
        return service.cornersDetection(image.getBytes());
    }
}
