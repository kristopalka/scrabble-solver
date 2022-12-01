package com.scrabble.backend.image_processing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrabble.backend.api.dto.ImageAndPointsDto;
import com.scrabble.backend.solving.scrabble.ScrabbleResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.scrabble.backend.image_processing.PythonRunner.executeScript;

@Service
@Slf4j
public class ImageProcessingService {
    ObjectMapper mapper = new ObjectMapper();
    @Value("${config.python-exec}")
    private String exec;
    @Value("${config.python-scripts}")
    private String scripts;



    public String findCorners(String base64Image) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(base64Image);

        ImageTemp temp = new ImageTemp(binaryImage);
        String output = executeScript(exec, scripts + "find_corners.py", temp.getPath());
        temp.delete();

        return output;
    }

    public String cropAndRecognize(ImageAndPointsDto imageAndPointsDto, String lang) throws IOException {
        byte[] binaryImage = Base64.decodeBase64(imageAndPointsDto.getBase64Image());
        ImageTemp temp = new ImageTemp(binaryImage);

        String allowLetters = ScrabbleResources.getAlphabet(lang).getLettersAsString().toUpperCase();
        String cornersJson = mapper.writeValueAsString(imageAndPointsDto.getCorners());

        String output = executeScript(exec, scripts + "crop_and_recognize.py", temp.getPath(), cornersJson, lang, allowLetters);

        temp.delete();
        return output;
    }
}

