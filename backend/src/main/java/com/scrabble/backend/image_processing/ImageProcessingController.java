package com.scrabble.backend.image_processing;

import com.scrabble.backend.image_processing.scripts.PythonRunner;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class ImageProcessingController {
    private final ImageProcessingService service;

    @PostMapping(value = "/find-corners")
    public @ResponseBody String findCorners(@RequestBody String base64Image) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(base64Image);
        return service.findCorners(binaryImage);
    }

    @PostMapping(value = "/extract-board", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] extractBoard(@RequestBody String base64Image) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(base64Image);
        return service.extractBoard(binaryImage);
    }

    @PostMapping(value = "/image-to-text")
    public @ResponseBody String imageToText(@RequestBody String base64Image) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(base64Image);
        return service.imageToText(binaryImage);
    }
}

