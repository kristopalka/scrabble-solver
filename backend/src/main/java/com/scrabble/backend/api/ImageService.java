package com.scrabble.backend.api;

import com.scrabble.backend.scripts.IOTemp;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.scrabble.backend.scripts.PythonExecutor.executeScript;

@Service
public class ImageService {
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
}

