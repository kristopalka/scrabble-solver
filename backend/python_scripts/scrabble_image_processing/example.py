from lib import *


img = load_image('/home/krist/Downloads/inżynierka/rozpoznawanie zdjęcia/extracted/05.jpg')
board = Board(img, img.shape[0], 0)

print_image("Grid", draw_scrabble_grid_on_board(board, color=red, thickness=5))

recognizer = LettersRecognizer(board.copy()).set_debug(True).recognize()
recognizer.get_letters()
recognizer.get_confidences()

cv.waitKey(0)
cv.destroyAllWindows()
