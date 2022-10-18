from lib import *

image = load_image('/run/media/krist/Data/Projects/Scrabble-Solver/resources/red_boards/008.jpg')

board = BoardExtractor(image).set_debug(False).find_corners().get_board(margin=50)

print_image("Board", board.image)

image = board.image

recognizer = LettersRecognizer(board.copy()).set_debug(True).recognize()
letters = recognizer.get_letters()
confidences = recognizer.get_confidences()

# reader = easyocr.Reader(['pl'], gpu=False)
# results = reader.readtext(image)
# for res in results:
#     top_left = (int(res[0][0][0]), int(res[0][0][1]))  # convert float to int
#     bottom_right = (int(res[0][2][0]), int(res[0][2][1]))  # convert float to int
#     cv.rectangle(image, top_left, bottom_right, (0, 255, 0), 3)
#     cv.putText(image, res[1], (top_left[0], top_left[1] - 10), cv.FONT_HERSHEY_SIMPLEX, 1.2, (0, 0, 255), 2)
# print_image('output', image)

cv.waitKey(0)
cv.destroyAllWindows()
