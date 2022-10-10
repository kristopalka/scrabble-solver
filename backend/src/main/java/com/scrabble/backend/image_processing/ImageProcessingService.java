package com.scrabble.backend.image_processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.scrabble.backend.image_processing.PythonRunner.executeScript;

@Service
@Slf4j
public class ImageProcessingService {
    @Value("${python.exec}")
    private String exec;
    @Value("${python.scripts}")
    private String scripts;

    public String findCorners(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript(exec, scripts + "find_corners.py", temp.getPath());
        temp.delete();

        return output;
    }

    public byte[] extractBoard(byte[] inImage) throws IOException {
        IOTemp temp = new IOTemp(inImage);
        executeScript(exec, scripts + "extract_board.py", temp.getPath());
        byte[] outImage = temp.readTemp();
        temp.delete();

        return outImage;
    }

    public String imageToText(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript(exec, scripts + "image_to_text.py", temp.getPath());
        temp.delete();

        if(Objects.equals(output, "ERROR")) throw new IllegalArgumentException();

        return output;
    }
}

