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
    [' ', 'L', ' ', 'Ł', ' ', 'M', ' ', 'N', ' ', 'Ń', ' ', 'O', ' ', 'Ó', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'p', ' ', 'R', ' ', 'S', ' ', 'Ś', ' ', 'T', ' ', 'U', ' ', 'W', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', 'Y', ' ', 'Z', ' ', 'Ż', ' ', 'Ź', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ],
    [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ]]

# -------------------------------------------
path = '/home/krist/Downloads/inżynierka/rozpoznawanie zdjęcia/extracted/'
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
        board_image = load_image(path + image_name)
        board = Board(board_image, board_image.shape[0], 0)

        start = time.time()
        recognizer = LettersRecognizer(board.copy()).set_debug(False).recognize()
        processing_times.append(time.time() - start)

        letters = recognizer.get_letters()
        confidences = recognizer.get_confidences()
        letters_mask = recognizer.get_letters_mask()

        correct_local = 0

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
                        correct_local += 1
                        correct_letter += 1
                else:
                    if actual == ' ':
                        unexpected_empty += 1
                    else:
                        if expected == ' ':
                            unexpected_letter += 1
                        else:
                            wrong_letter += 1
        print(image_name, "-", correct_local)

correct = correct_empty + correct_letter
incorrect = unexpected_letter + unexpected_empty + wrong_letter



print("Poprawność wstępnej kwalifikacji: ", round(correct_mask / n * 100, 3), "%")
print("Nieoczekiwane puste: ", round(wrong_mask_unexpected_empty / n * 100, 3), "%")
print("Nieoczekiwana litera: ", round(wrong_mask_unexpected_letter / n * 100, 3), "%")
print()

print("Poprawnie: ", round(correct / n * 100, 3), "%")
print("Poprawnie litera: ", round(correct_letter / n * 100, 3), "%")
print("Poprawnie puste: ", round(correct_empty / n * 100, 3), "%")
print("Niepoprawnie: ", round(incorrect / n * 100, 3), "%")
print("Nieoczekiwana litera: ", round(unexpected_letter / n * 100, 3), "%")
print("Nieoczekiwane puste: ", round(unexpected_empty / n * 100, 3), "%")
print("Niepoprawnie rozpoznane litery: ", round(wrong_letter / n * 100, 3), "%")
print()

print("One board processing")
print("time: ", round(sum(processing_times) / len(processing_times), 3))
print("std dev: ", round(np.std(np.float32(processing_times)), 3))



