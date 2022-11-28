from .contours import *
from .grouping_points import *
from .intersections import *
from ..utils import resize, print_image, draw_points, draw_hough_lines, Board

diamond2 = np.array([[0, 0, 1, 0, 0],
            [0, 1, 1, 1, 0],
            [1, 1, 1, 1, 1],
            [0, 1, 1, 1, 0],
            [0, 0, 1, 0, 0]], np.uint8)

diamond4 = np.array([[0, 0, 0, 0, 1, 0, 0, 0, 0],
            [0, 0, 0, 1, 1, 1, 0, 0, 0],
            [0, 0, 1, 1, 1, 1, 1, 0, 0],
            [0, 1, 1, 1, 1, 1, 1, 1, 0],
            [1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 1, 1, 1, 1, 1, 1, 1, 0],
            [0, 0, 1, 1, 1, 1, 1, 0, 0],
            [0, 0, 0, 1, 1, 1, 0, 0, 0],
            [0, 0, 0, 0, 1, 0, 0, 0, 0]], np.uint8)


def _change_image_size(img, lower_dim):
    ratio = lower_dim / min(img.shape[:2])
    img = resize(img, ratio)
    return img, ratio


def _restore_ratio(points, ratio):
    restored_points = []
    for point in points:
        restored = [int(point[0] // ratio), int(point[1] // ratio)]
        restored_points.append(restored)
    return np.array(restored_points)


def _find_board_mask(image, debug):
    gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    if debug: print_image('0. Gray', gray)

    blur = cv.GaussianBlur(gray, (5, 5), 0)
    edges = cv.Canny(blur, 40, 200)
    if debug: print_image('1. Canny edges', edges)

    dilate = cv.dilate(edges, diamond2)
    if debug: print_image('2. Dilate', dilate)

    fill = fill_all_contours(dilate)
    if debug: print_image('3. Fill contours', fill)

    erode = cv.erode(fill, diamond4)
    if debug: print_image('4. Erode', erode)

    largest_contour = largest_contour_image(erode)
    if debug: print_image('5. Largest blob', largest_contour)

    return largest_contour


def _get_corners_from_mask(image, mask, debug):
    edges = cv.Canny(mask, 100, 150)
    if debug: print_image('6. Edges', edges)

    lines = cv.HoughLines(edges, 1, np.pi / 180, 90)
    if debug: print('Number of lines: ', len(lines))

    intersections = find_good_intersections(lines, mask.shape)
    assert len(intersections) > 4, "Should be at least 4 intersections"

    if debug:
        print_image('7. Lines and intersections', draw_points(draw_hough_lines(image, lines), intersections))
        print('Nuber of intersections:', len(intersections))

    corners = find_four_corners(intersections, mask.shape[:2])
    if debug:
        print_image('8. Finding centers', draw_points(image, corners, color=(255, 0, 0), radius=8, thickness=6))

    return corners


class BoardExtractor:
    def __init__(self, image):
        self._debug = False
        self._image = image
        self._corners = None

    def set_debug(self, debug):
        self._debug = debug
        return self

    def get_corners(self):
        return self._corners

    def set_corners(self, corners):
        self._corners = np.array(corners)
        return self

    def find_corners(self):
        image_resized, ratio = _change_image_size(self._image, 1000)

        mask = _find_board_mask(image_resized, self._debug)
        corners = _get_corners_from_mask(image_resized, mask, self._debug)
        assert len(corners) == 4, f"Should be 4 corners. There is {len(corners)}"

        corners = _restore_ratio(corners, ratio)
        self._corners = sort_points_clockwise(corners)
        return self

    def get_board(self, margin=0):
        (x, y), (width, height), angle = cv.minAreaRect(self._corners)
        board_size = int(min(width, height))
        image_size = int(board_size + 2 * margin)

        dest = [[margin, margin],
                [image_size - margin, margin],
                [image_size - margin, image_size - margin],
                [margin, image_size - margin]]

        src = sort_points_clockwise(np.float32(self._corners))
        dest = sort_points_clockwise(np.float32(dest))

        matrix = cv.getPerspectiveTransform(src, dest)
        board_image = cv.warpPerspective(self._image, matrix, (image_size, image_size))

        return Board(board_image, int(min(width, height)), margin)
