package com.scrabble.backend.image_processing;

import com.scrabble.backend.image_processing.scripts.PythonRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageProcessingController {
    private final ImageProcessingService service;

    @GetMapping("/test")
    public @ResponseBody String test() throws IOException {
        return PythonRunner.executeScript("test.py", "world");
    }

    @PostMapping(value = "/find-corners")
    public @ResponseBody String findCorners(@RequestParam MultipartFile image) throws IOException {
        return service.findCorners(image.getBytes());
    }

    @PostMapping(value = "/extract-board", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] extractBoard(@RequestParam MultipartFile image) throws IOException {
        return service.extractBoard(image.getBytes());
    }

    @PostMapping(value = "/image-to-text")
    public @ResponseBody String imageToText(@RequestParam MultipartFile image) throws IOException {
        return service.imageToText(image.getBytes());
    }
}
