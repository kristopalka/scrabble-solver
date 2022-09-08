package com.scrabble.backend.api.dto;

import lombok.Data;

import java.awt.*;
import java.util.List;


@Data
public class PointsDto {
    List<Point> points;
}
