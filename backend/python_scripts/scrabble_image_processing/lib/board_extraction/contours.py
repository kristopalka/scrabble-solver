import cv2 as cv
import numpy as np


def fill_all_contours(img):
    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)
    for contour in contours:
        cv.drawContours(img, [contour], 0, 255, -1)
    return img


def largest_contour_image(img):
    largest_area = 0
    largest_index = 0

    contours, hierarchy = cv.findContours(img, cv.RETR_CCOMP, cv.CHAIN_APPROX_SIMPLE)

    assert len(contours) > 0, "There should be contours on image"

    for i, contour in enumerate(contours):
        area = cv.contourArea(contour)
        if area > largest_area:
            largest_area = area
            largest_index = i
    largest_contour = [contours[largest_index]]

    return cv.drawContours(np.zeros(img.shape, dtype=np.uint8), largest_contour, 0, 255, -1)
