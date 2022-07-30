from libs.methods import *

frame = cv.imread('resources/photos/red/photo_%d.jpg' % 1)
board, config = loader('resources/boards/red')

match = find_board(frame, board)

lines = draw_lines(match, config)

cv.imshow('found', resize(lines, 1/3))
cv.waitKey(0)
cv.destroyAllWindows()
