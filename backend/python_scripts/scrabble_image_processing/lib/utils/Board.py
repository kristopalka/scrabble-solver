class Board:
    def __init__(self, board_image, board_size, margin):
        self.image = board_image
        self.margin = margin
        self.board_size = board_size
        self.field_size = self.board_size // 15

    def copy(self):
        return Board(self.image.copy(), self.board_size, self.margin)

    def get_field_coords(self, row, column):
        x = int(self.margin + (self.board_size * (row / 15))) + 1
        y = int(self.margin + (self.board_size * (column / 15))) + 1
        return x, y, self.field_size

    def get_field(self, row, column, field_margin=0):
        assert -field_margin < self.field_size // 2, "Minus field margin can not be grater than half of field"

        x = int(self.margin + (self.board_size * (row / 15))) + 1
        y = int(self.margin + (self.board_size * (column / 15))) + 1

        x1 = max(x - field_margin, 0)
        y1 = max(y - field_margin, 0)
        x2 = min(x + self.field_size + field_margin, self.board_size + 2 * self.margin)
        y2 = min(y + self.field_size + field_margin, self.board_size + 2 * self.margin)

        return self.image[y1:y2, x1:x2]
