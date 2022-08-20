import cv2
from skimage import morphology

from util.cv_methods import *
from util.grouping_points import *
from util.intersections import *
from util.drawing import *


def find_board_mask(image, debug=False):
    gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    blur = cv.GaussianBlur(gray, (5, 5), 0)

    edges = cv.Canny(blur, 40, 200)
    if debug: print_image('1. Canny edges', edges)

    dilate = cv.dilate(edges, morphology.diamond(2))
    if debug: print_image('2. Dilate', dilate)

    fill = fill_all_contours(dilate)
    if debug: print_image('3. Fill contours', fill)

    erode = cv.erode(fill, morphology.diamond(4))
    if debug: print_image('4. Erode', erode)

    largest_contour = find_largest_contour(erode)
    largest_blob = np.zeros(gray.shape, dtype=np.uint8)
    largest_blob = cv.drawContours(largest_blob, [largest_contour], 0, 255, -1)
    if debug: print_image('5. Largest blob', largest_blob)

    return largest_blob


def get_corners_from_mask(board_mask, image, debug=False):
    edges = cv.Canny(board_mask, 100, 150)
    if debug: print_image('6. Edges', edges)

    lines = cv.HoughLines(edges, 1, np.pi / 180, 90)
    if debug: print('Number of lines: ', len(lines))

    intersections = find_good_intersections(lines, board_mask.shape)
    assert len(intersections) > 4, "Should be at least 4 intersections"

    if debug:
        print_image('7. Lines and intersections', draw_points(draw_hough_lines(image, lines), intersections))
        print('Nuber of intersections:', len(intersections))

    corners = group_and_get_centers(intersections, image.shape[:2])
    if debug: print_image('8. Finding centers', draw_points(image, corners, color=(255, 0, 0), radius=7, thickness=3))

    return corners


def find_corners(image, debug=False):
    image, ratio = normalize_size(image)

    mask = find_board_mask(image, debug=debug)
    corners = get_corners_from_mask(mask, image, debug=debug)

    assert len(corners) == 4, f"Should be 4 corners. There is {len(corners)}"

    corners = sort_points_clockwise(corners)
    corners = restore_ratio(corners, ratio)
    return corners

