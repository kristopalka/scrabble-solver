import cv2 as cv
from pytesseract import Output
import pytesseract as tr

from src.libs.input_photo_processing import *
from src.libs.util.board import *


def calculate_mser(image):
    board_gray = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    board_gray = cv.equalizeHist(board_gray)

    max_tile_area = (image.shape[0] * image.shape[1]) // 4
    min_tile_area = (image.shape[0] * image.shape[1]) // 16
    MSER = cv.MSER_create(max_area=max_tile_area, min_area=min_tile_area, delta=40)

    regions, _ = MSER.detectRegions(board_gray)

    hulls = [cv.convexHull(p.reshape(-1, 1, 2)) for p in regions]
    cv.polylines(image, hulls, 1, (0, 0, 255), 2)

    return regions, image




photo = load_image('red/001.jpg')

corners = find_corners(photo, debug=False)


board = Board(photo, corners, margin=50)
print_image('Scrabble', board.get_board_with_grid())

for i in range(0, 12):
    field = board.get_field_image((8, i), field_margin=-10)
    field = cv.rotate(field, cv.ROTATE_90_COUNTERCLOCKWISE)

    regions, image = calculate_mser(field)

    print(f'{i}: {len(regions)}')
    print_image(f'Field {i}', image)
    # todo wziąć największy, najbliżej środka






# field = cv.cvtColor(field, cv.COLOR_BGR2GRAY)
# field = cv.bilateralFilter(field, 20, 80, 80)
# field = cv.equalizeHist(field)
# field = cv.adaptiveThreshold(field, 255, cv.ADAPTIVE_THRESH_GAUSSIAN_C, cv.THRESH_BINARY, 85, 25)
# print_image('Field', field)
#
# config = "--psm 10  -c tessedit_char_whitelist=ABCDEFGHIJKLMNOPRSTUW"
# d = tr.image_to_data(field, output_type=Output.DICT, config=config, lang='eng')
# print(d)



cv.waitKey(0)
cv.destroyAllWindows()

