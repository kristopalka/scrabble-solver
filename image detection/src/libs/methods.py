import cv2 as cv
import numpy as np


def resize(img, fraction=1.0):
    assert img is not None
    x = int(round(img.shape[1] * fraction))
    y = int(round(img.shape[0] * fraction))
    return cv.resize(img, (x, y))


def fill_all_contours(img):
    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)
    for contour in contours:
        cv.drawContours(img, [contour], 0, 255, -1)

    return img


def find_largest_contour(img):
    largest_area = 0
    largest_index = 0

    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)
    for i, contour in enumerate(contours):
        area = cv.contourArea(contour)
        if area > largest_area:
            largest_area = area
            largest_index = i

    return contours[largest_index]
