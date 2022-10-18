package com.scrabble.backend.image_processing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrabble.backend.api.dto.ImagePointsDto;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
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
    @Value("${config.python-exec}")
    private String exec;
    @Value("${config.python-scripts}")
    private String scripts;



    public String findCorners(byte[] image) throws IOException {
        IOTemp temp = new IOTemp(image);
        String output = executeScript(exec, scripts + "find_corners.py", temp.getPath());
        temp.delete();

        return output;
    }

    public String cropAndRecognize(byte[] inImage, List<ImagePointsDto.Coordinates> corners, String lang) throws IOException {
        IOTemp temp = new IOTemp(inImage);

        String allowLetters = ScrabbleResources.getAlphabet(lang).getLettersAsString().toUpperCase();
        String cornersJson = mapper.writeValueAsString(corners);

        String output = executeScript(exec, scripts + "crop_and_recognize.py", temp.getPath(), cornersJson, lang, allowLetters);

        temp.delete();
        return output;
    }
}

