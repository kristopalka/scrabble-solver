from src.libs.util.cv_methods import *
from src.libs.util.drawing import *


def cut_board(image, corners, margin=20, debug=False):
    min_rectangle = cv.minAreaRect(corners)
    (x, y), (width, height), angle = min_rectangle

    if debug:
        cv.drawContours(image, [np.int0(cv.boxPoints(min_rectangle))], 0, (0, 255, 0), 2)
        print_image('1. Minimal rectangle', image)
        print(f"Size of extracted board (w: {width}, h: {height})")

    dim = min(width, height) + 2 * margin
    destination = [[margin, margin], [dim - margin, margin], [dim - margin, dim - margin], [margin, dim - margin]]

    matrix = cv.getPerspectiveTransform(np.float32(corners), np.float32(destination))
    board = cv.warpPerspective(image, matrix, (int(dim), int(dim)))

    if debug: print_image('2. Cut board', board)
    return board


def extract_field_from_board(board, coords, board_margin=0, field_margin=0):
    (row, column) = coords
    assert board.shape[0] == board.shape[1], "Board should be rectangle"

    board_dim = board.shape[0] - 2 * board_margin
    field_dim = board_dim // 15

    x = int(board_margin + (board_dim * (row / 15))) + 1
    y = int(board_margin + (board_dim * (column / 15))) + 1

    x1 = max(x - field_margin, 0)
    y1 = max(y - field_margin, 0)
    x2 = min(x + field_dim + field_margin, board_dim)
    y2 = min(y + field_dim + field_margin, board_dim)

    return board[x1:x2, y1:y2]
