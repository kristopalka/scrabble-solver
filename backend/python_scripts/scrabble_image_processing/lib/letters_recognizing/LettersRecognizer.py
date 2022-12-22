import cv2 as cv
import numpy as np
from easyocr import easyocr
from pytesseract import pytesseract

from .letters_mask_creator import get_letters_mask
from ..utils import print_image, draw_scrabble_grid_on_board, draw_grid_and_letters_on_board


def _preprocess(image):
    image = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    image = cv.bilateralFilter(image, 25, 80, 80)
    image = cv.adaptiveThreshold(image, 255, cv.ADAPTIVE_THRESH_MEAN_C, cv.THRESH_BINARY, 35, 20)
    # image = cv.dilate(image, np.ones((3, 3), np.uint8))
    return image


def _get_best_result(results):
    bestResult = results[0]
    for result in results:
        if result[2] > bestResult[2]:
            bestResult = result
    return bestResult


class LettersRecognizer:
    def __init__(self, board, allow_letters="ABCDEFGHIJKLMNOPQRSTUVWXYZ", lang="en"):
        self._allow_letters = allow_letters
        self._debug = False
        self._board = board
        self._letters_mask = get_letters_mask(board.copy(), self._debug)
        self._letters = None
        self._confs = None
        self._lang = lang
        self.reader = easyocr.Reader(["pl"], gpu=False)

    def set_debug(self, debug):
        self._debug = debug
        return self

    def get_letters(self):
        return self._letters

    def get_confidences(self):
        return self._confs

    def get_letters_mask(self):
        return self._letters_mask

    def recognize_letter_pytesseract(self, field):
        english_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        polish_letters = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŻŹ"
        config = f"--psm 10  -c tessedit_char_whitelist={polish_letters}"

        data = pytesseract.image_to_data(field, output_type=pytesseract.Output.DICT, config=config, lang='pol')

        best_id = data['conf'].index(max(data['conf']))
        confidence = data['conf'][best_id]
        letter = str(data['text'][best_id])

        if len(letter) == 0:
            letter = ' '
        else:
            letter = letter[0]

        return letter, confidence

    def recognize_letter_easyocr(self, image):
        results = self.reader.recognize(image, allowlist=self._allow_letters)

        if len(results) == 0: return " ", 100
        bestResult = _get_best_result(results)

        text = bestResult[1] if bestResult[1] != "" else " "
        conf = round(bestResult[2], 2)

        return text, conf

    def recognize(self):
        self._board.image = _preprocess(self._board.image)

        if self._debug: print_image('1. Preprocessed image', draw_scrabble_grid_on_board(self._board))

        self._confs = np.empty((15, 15), dtype=float)
        self._letters = np.empty((15, 15), dtype=str)
        self._letters[:, :] = ' '

        for x in range(0, 15):
            for y in range(0, 15):
                if self._letters_mask[x, y]:
                    field = self._board.get_field(x, y, field_margin=0)

                    letter, conf = self.recognize_letter_easyocr(field)

                    self._letters[x, y] = letter
                    self._confs[x, y] = conf

        if self._debug:
            print_image('2. Recognized letters',
                        draw_grid_and_letters_on_board(self._board, self._letters, self._confs))

        return self
