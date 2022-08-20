package com.scrabble.backend;

import com.scrabble.backend.util.ImageTemp;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.scrabble.backend.util.PythonExecutor.executeScript;

@Service
public class ImageService {


    public String cornersDetection(byte[] image) throws IOException {
        ImageTemp temp = new ImageTemp(image);
        String output = executeScript("detect_corners.py", temp.getPath());
        temp.delete();

        return output;
    }
}

