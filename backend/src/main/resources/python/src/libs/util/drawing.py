import numpy as np
import cv2 as cv


def draw_hough_lines(image, lines):
    img = image.copy()
    for line in lines:
        rho, theta = line[0]
        a, b = np.cos(theta), np.sin(theta)
        x0, y0 = a * rho, b * rho
        x1 = int(x0 + 1000 * (-b))
        y1 = int(y0 + 1000 * a)
        x2 = int(x0 - 1000 * (-b))
        y2 = int(y0 - 1000 * a)
        cv.line(img, (x1, y1), (x2, y2), (0, 255, 0), 1)

    return img


def draw_points(image, points, color=(0, 0, 255), radius=5, thickness=2):
    img = image.copy()
    for point in points:
        cv.circle(img, point, radius, color, thickness)
    return img


