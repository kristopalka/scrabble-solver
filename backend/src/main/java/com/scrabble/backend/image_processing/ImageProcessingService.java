package com.scrabble.backend.image_processing;

import com.scrabble.backend.image_processing.scripts.IOTemp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.scrabble.backend.image_processing.scripts.PythonRunner.executeScript;

@Service
@Slf4j
public class ImageProcessingService {
    public String findCorners(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript("find_corners.py", temp.getPath());
        temp.delete();

        return output;
    }

    public byte[] extractBoard(byte[] inImage) throws IOException {
        IOTemp temp = new IOTemp(inImage);
        executeScript("extract_board.py", temp.getPath());
        byte[] outImage = temp.readTemp();
        temp.delete();

        return outImage;
    }

    public String imageToText(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript("image_to_text.py", temp.getPath());
        temp.delete();

        return output;
    }
}

