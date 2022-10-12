package com.scrabble.backend.image_processing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrabble.backend.api.dto.ImagePointsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.scrabble.backend.image_processing.PythonRunner.executeScript;

@Service
@Slf4j
public class ImageProcessingService {
    ObjectMapper mapper = new ObjectMapper();

    @Value("${python.exec}")
    private String exec;
    @Value("${python.scripts}")
    private String scripts;

    public String imageToText(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript(exec, scripts + "image_to_text.py", temp.getPath());
        temp.delete();
        return output;
    }

    public String findCorners(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript(exec, scripts + "find_corners.py", temp.getPath());
        temp.delete();
        return output;
    }


    public String cropAndRecognize(byte[] inImage, List<ImagePointsDto.Point> points, String lang) throws IOException {
        IOTemp temp = new IOTemp(inImage);

        String output = executeScript(exec, scripts + "crop_and_recognize.py",
                temp.getPath(), mapper.writeValueAsString(points), lang);

        temp.delete();
        return output;
    }
}

