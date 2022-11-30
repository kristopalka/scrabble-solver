import os
import time
from matplotlib import pyplot as plt

from scrabble_image_processing.lib import *

letters_template = [
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'A', ' ', 'Ą', ' ', 'B', ' ', 'C', ' ', 'Ć', ' ', 'D', ' ', 'E', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'Ę', ' ', 'F', ' ', 'G', ' ', 'H', ' ', 'I', ' ', 'J', ' ', 'K', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'L', ' ', 'Ł', ' ', 'M', ' ', 'N', ' ', 'O', ' ', 'Ó', ' ', 'P', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'R', ' ', 'S', ' ', 'Ś', ' ', 'T', ' ', 'U', ' ', 'W', ' ', 'Y', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'Z', ' ', 'Ż', ' ', 'Ź', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ]]

# -------------------------------------------
path = '/run/media/krist/Data/Projects/scrabble-solver/resources/green_boards/extracted/'
# -------------------------------------------
n = 0
unexpected_letter = 0
unexpected_empty = 0
wrong_letter = 0
correct_empty = 0
correct_letter = 0
correct_mask = 0
processing_times = []
wrong_mask_unexpected_letter = 0
wrong_mask_unexpected_empty = 0


for image_name in os.listdir(path):
    if image_name.endswith(".jpg"):
        print(image_name)
        board_image = load_image(path + image_name)
        board = Board(board_image, board_image.shape[0], 0)

        start = time.time()
        recognizer = LettersRecognizer(board.copy()).set_debug(False).recognize()
        processing_times.append(time.time() - start)

        letters = recognizer.get_letters()
        confidences = recognizer.get_confidences()
        letters_mask = recognizer.get_letters_mask()

        for x in range(0, 15):
            for y in range(0, 15):
                expected = letters_template[x][y]
                actual = letters[x][y]
                mask = letters_mask[x][y]

                n += 1
                if (expected == ' ' and not mask) or (expected != ' ' and mask):
                    correct_mask += 1
                if expected == ' ' and mask:
                    wrong_mask_unexpected_letter += 1
                if expected != ' ' and not mask:
                    wrong_mask_unexpected_empty += 1

                if expected == actual:
                    if actual == ' ':
                        correct_empty += 1
                    else:
                        correct_letter += 1
                else:
                    if actual == ' ':
                        unexpected_empty += 1
                    else:
                        if expected == ' ':
                            unexpected_letter += 1
                        else:
                            wrong_letter += 1

correct = correct_empty + correct_letter
incorrect = unexpected_letter + unexpected_empty + wrong_letter





print("Correct: ", round(correct / n * 100, 3), "%")
print("Incorrect: ", round(incorrect / n * 100, 3), "%")
print("Correct letter: ", round(correct_letter / n * 100, 3), "%")
print("Correct empty: ", round(correct_empty / n * 100, 3), "%")
print("Correct mask: ", round(correct_mask / n * 100, 3), "%")
print("Unexpected empty mask: ", round(wrong_mask_unexpected_empty / n * 100, 3), "%")
print("Unexpected letter mask: ", round(wrong_mask_unexpected_letter / n * 100, 3), "%")
print()

print("Unexpected letter: ", round(unexpected_letter / n * 100, 3), "%")
print("Unexpected empty: ", round(unexpected_empty / n * 100, 3), "%")
print("Wrong letter: ", round(wrong_letter / n * 100, 3), "%")
print()

print("One board processing")
print("time: ", round(sum(processing_times) / len(processing_times), 3))
print("std dev: ", round(np.std(np.float32(processing_times)), 3))



