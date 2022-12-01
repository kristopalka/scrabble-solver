package com.scrabble.backend.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageAndPointsDto {
    private List<Coordinates> corners;

    private String base64Image;

    @Data
    static public class Coordinates {
        private Double x;
        private Double y;
    }
}
