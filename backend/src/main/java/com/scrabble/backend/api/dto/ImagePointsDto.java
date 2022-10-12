package com.scrabble.backend.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImagePointsDto {
    @Data
    static public class Point {
        private Integer x;
        private Integer y;
    }

    private String base64Image;
    private List<Point> points;
}
