from libs.methods import *

frame = cv.imread('resources/photos/red/_1.jpg')
board, config = loader('resources/boards/red')

match = find_board(frame, board, debug=True)

lines = draw_lines(match, config)

cv.imshow('result', resize(lines, 1/3))
cv.waitKey(0)
cv.destroyAllWindows()
