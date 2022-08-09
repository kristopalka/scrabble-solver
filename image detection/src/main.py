from libs.methods import *

frame = cv.imread('resources/photos/red/_1.jpg')  # _1, _6, _8
board, config = loader('resources/boards/red')

match = find_board(frame, board, debug=True, align_image_sizes=True, debug_resize_factor=1/2)

lines = draw_lines(match, config)

cv.imshow('result', resize(lines, 1/3))
cv.waitKey(0)
cv.destroyAllWindows()
