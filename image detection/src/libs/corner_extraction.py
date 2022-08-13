from skimage import morphology

from src.libs.cv_methods import *
from src.libs.grouping_points import *
from src.libs.intersections import *


def extract_board_mask_from_image(image, debug=False):
    gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)

    edges = cv.Canny(gray, 100, 150)
    if debug: cv.imshow('1. Canny edges', edges)

    dilate = cv.dilate(edges, morphology.diamond(1))
    if debug: cv.imshow('2. Dilate', dilate)

    fill = fill_all_contours(dilate)
    if debug: cv.imshow('3. Fill contours', fill)

    erode = cv.erode(fill, morphology.diamond(4))
    if debug: cv.imshow('4. Erode', erode)

    largest_contour = find_largest_contour(erode)
    largest_blob = np.zeros(gray.shape, dtype=np.uint8)
    largest_blob = cv.drawContours(largest_blob, [largest_contour], 0, 255, -1)
    if debug: cv.imshow('5. Largest blob', largest_blob)

    return largest_blob


def get_corners_from_mask(board_mask, image, debug=False):
    edges = cv.Canny(board_mask, 100, 150)
    edges_with_marked_corners = cv.morphologyEx(edges, cv.MORPH_CLOSE, morphology.diamond(30))
    if debug: cv.imshow('6. Edges + marked corners', edges_with_marked_corners)

    lines = cv.HoughLines(edges_with_marked_corners, 1, np.pi / 180, 150)
    if debug: print('Number of lines: ', len(lines))

    intersections = find_good_intersections(lines, board_mask.shape)
    if debug:
        print('Nuber of intersections:', len(intersections))
        cv.imshow('7. Lines and intersections', draw_points(draw_lines(image, lines), intersections))

    corners = group_points(intersections, image.shape[:2])
    if debug: cv.imshow('8. k-means centers', draw_points(image, corners, color=(255, 0, 0), radius=7, thickness=3))

    return corners



def get_corners_from_image(image, debug=False):
    image, ratio = standardize_size(image)

    mask = extract_board_mask_from_image(image, debug=debug)
    corners = get_corners_from_mask(mask, image, debug=debug)


    corners = sort_points_clockwise(corners)
    corners = restore_ratio(corners, ratio)
    return corners
