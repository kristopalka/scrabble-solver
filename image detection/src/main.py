from libs.methods import *

frame = cv.imread('../resources/photos/green/board_words.jpg')
board, config = loader('../resources/boards/green')

match = find_board(frame, board)

lines = draw_lines(match, config)

cv.imshow('found', resize(lines, 1))
cv.waitKey(0)
cv.destroyAllWindows()
