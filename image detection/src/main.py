from src.libs.input_photo_processing import *
from src.libs.util.board import *



photo = load_image('red/001.jpg')

corners = find_corners(photo, debug=False)

margin = 50
board = cut_board(photo, corners, margin=margin)
print_image('Scrabble', draw_scrabble_mesh(board, margin=margin))


field = extract_field_from_board(board, (8, 8), board_margin=margin, field_margin=5)
field_gray = cv.cvtColor(field, cv.COLOR_BGR2GRAY)

print_image('Field', field)




cv.waitKey(0)
cv.destroyAllWindows()
