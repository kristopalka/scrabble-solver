from scrabble_image_processing import *

image = load_image('/home/krist/Projects/Scrabble-Solver/image detection/resources/photos/red/008.jpg')

extractor = BoardExtractor(image).set_debug(True).process()
board = extractor.get_board(margin=50)
corners = extractor.get_corners()

print_image("Board", board.image)

recognizer = LettersRecognizer(board.copy()).set_debug(True).process()
recognizer.get_letters()
recognizer.get_confidences()

cv.waitKey(0)
cv.destroyAllWindows()
