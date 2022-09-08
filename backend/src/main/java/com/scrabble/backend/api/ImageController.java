package com.scrabble.backend.api;

import com.scrabble.backend.scripts.PythonExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/find-corners")
    public @ResponseBody String findCorners(@RequestParam MultipartFile image) throws IOException {
        return service.findCorners(image.getBytes());
    }

    @PostMapping(value = "/extract-board", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] extractBoard(@RequestParam MultipartFile image) throws IOException {
        return service.extractBoard(image.getBytes());
    }
}
