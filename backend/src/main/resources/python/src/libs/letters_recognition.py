import pytesseract as tr
from pytesseract import Output

from corners_detection import *
from letters_mask import calculate_letters_mask
from util.cv_methods import *
from util.grouping_points import *
from util.intersections import *
from util.drawing import *
from util.board import *

config = "--psm 10  -c tessedit_char_whitelist=ABCDEFGHIJKLMNOPQRSTUWXYZ"


def preprocess(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 31, 20)
    # image = cv.bitwise_not(image)
    # image = cv.erode(image, morphology.diamond(1))
    # image = cv.bitwise_not(image)
    return image


def recognize_letter(field):
    data = tr.image_to_data(field, output_type=Output.DICT, config=config, lang='eng')

    conf = data['conf'][4]

    letter = str(data['text'][4])
    if len(letter) == 0:
        letter = ' '
    else:
        letter = letter[0]

    return letter, conf


def add_found_letters_to_image(board, letters, confs):
    board_image = board.get_board_with_grid(color=blue)
    for x in range(0, 15):
        for y in range(0, 15):
            if letters[x, y] != ' ':
                (w, h, size) = board.get_field_coords(x, y)
                cv.putText(board_image, letters[x, y], (w, h + size - 40), cv2.FONT_HERSHEY_TRIPLEX, 3, green, 3)
                cv.putText(board_image, str(confs[x, y]), (w, h + size - 10), cv2.FONT_HERSHEY_TRIPLEX, 1, blue, 2)
    return board_image


def board_to_text(board, mask, debug=False):
    board.preprocess_image(preprocess)

    if debug: print_image('1. Preprocessed image', board.get_board_with_grid(color=blue, preprocessed=True))

    confs = np.empty((15, 15), dtype=float)
    letters = np.empty((15, 15), dtype=str)
    letters[:] = ' '

    for x in range(0, 15):
        for y in range(0, 15):
            if mask[x, y]:
                field = board.get_field((x, y), field_margin=-30, preprocessed=True)
                letter, conf = recognize_letter(field)
                letters[x, y] = letter
                confs[x, y] = conf

    if debug:
        print_image('2. Recognized letters', add_found_letters_to_image(board, letters, confs))
        print(letters)
