import numpy as np
import pytesseract as tr
import cv2 as cv
from pytesseract import Output

from scrabble_image_processing.lib.utils import print_image, blue, draw_scrabble_grid_on_board, draw_grid_letters_and_confidences_on_board
from .letters_mask_creator import get_letters_mask


def _preprocess(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 31, 20)
    # image = cv.bitwise_not(image)
    # image = cv.erode(image, morphology.diamond(1))
    # image = cv.bitwise_not(image)
    return image


def _recognize_letter(field):
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    config = f"--psm 10  -c tessedit_char_whitelist={alphabet}"

    data = tr.image_to_data(field, output_type=Output.DICT, config=config, lang='eng')

    best_id = data['conf'].index(max(data['conf']))


    conf = data['conf'][best_id]
    letter = str(data['text'][best_id])
    if len(letter) == 0:
        letter = ' '
    else:
        letter = letter[0]

    return letter, conf


class LettersRecognizer:
    def __init__(self, board):
        self._debug = False
        self._board = board
        self._letters_mask = get_letters_mask(board.copy(), self._debug)
        self._letters = None
        self._confs = None

    def set_debug(self, debug):
        self._debug = debug
        return self

    def get_letters(self):
        return self._letters

    def get_confidences(self):
        return self._confs

    def process(self):
        self._board.image = _preprocess(self._board.image)

        if self._debug: print_image('1. Preprocessed image', draw_scrabble_grid_on_board(self._board, color=blue))

        self._confs = np.empty((15, 15), dtype=float)
        self._letters = np.empty((15, 15), dtype=str)
        self._letters[:] = ' '

        for x in range(0, 15):
            for y in range(0, 15):
                if self._letters_mask[x, y]:
                    field = self._board.get_field(x, y, field_margin=-30)
                    letter, conf = _recognize_letter(field)
                    self._letters[x, y] = letter
                    self._confs[x, y] = conf

        if self._debug:
            print_image('2. Recognized letters', draw_grid_letters_and_confidences_on_board(self._board, self._letters, self._confs))
            print(self._letters)

        return self
