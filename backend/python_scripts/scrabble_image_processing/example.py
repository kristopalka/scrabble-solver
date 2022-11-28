from lib import *

image = load_image('/home/krist/Projects/scrabble-solver/resources/red_boards/001.jpg')

extractor = BoardExtractor(image).set_debug(False).find_corners()
# board = extractor.get_board(margin=0)
# corners = extractor.get_corners()

board = Board(load_image('/home/krist/Downloads/prosta.jpeg'), 2771, 0)
# board = Board(load_image('/home/krist/Downloads/pod_kątem.jpeg'), 2566, 0)

print_image("Grid", draw_scrabble_grid_on_board(board, color=red, thickness=5))

recognizer = LettersRecognizer(board.copy()).set_debug(True).recognize()
recognizer.get_letters()
recognizer.get_confidences()

cv.waitKey(0)
cv.destroyAllWindows()
