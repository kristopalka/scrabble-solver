import pytesseract as tr
from pytesseract import Output

from src.libs.corners_detection import *
from src.libs.letters_mask import calculate_letters_mask
from src.libs.util.cv_methods import *
from src.libs.util.grouping_points import *
from src.libs.util.intersections import *
from src.libs.util.drawing import *
from src.libs.util.board import *

config = "--psm 10  -c tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUWXYZ"


def preprocess(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 31, 20)
    # image = cv.bitwise_not(image)
    image = cv.erode(image, morphology.diamond(1))
    # image = cv.bitwise_not(image)
    return image


def recognize_letter(field):
    letter = tr.image_to_string(field, config=config, lang='eng')
    letter = str(letter).strip('\n')

    if len(letter) == 0:
        return ' '
    else:
        return letter[0]


def add_found_letters_to_image(board, letters):
    board_image = board.get_board_with_grid(color=blue)
    for x in range(0, 15):
        for y in range(0, 15):
            if letters[x, y] != ' ':
                (w, h, size) = board.get_field_coords(x, y)
                cv.putText(board_image, letters[x, y], (w, h + size), cv2.FONT_HERSHEY_TRIPLEX, 3, green, 3)
    return board_image


def board_to_text(board, mask, debug=False):
    board.preprocess_image(preprocess)

    if debug: print_image('1. Preprocessed image', board.get_board_with_grid(color=blue, preprocessed=True))

    letters = np.empty((15, 15), dtype=str)
    letters[:] = ' '

    for x in range(0, 15):
        for y in range(0, 15):
            if mask[x, y]:
                field = board.get_field((x, y), field_margin=-10, preprocessed=True)
                letter = recognize_letter(field)
                letters[x, y] = letter

    if debug:
        print_image('2. Recognized letters', add_found_letters_to_image(board, letters))
        print(letters)
