public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) ||
                (line == toLine && column == toColumn)) {
            return false;
        }

        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
        int direction = "White".equals(color) ? 1 : -1;
        int startLine = "White".equals(color) ? 1 : 6;

        // Стандартный шаг вперед
        if (column == toColumn && destinationPiece == null) {
            if (line + direction == toLine) {
                return true;
            }
            // Первый ход на две клетки
            if (line == startLine && line + 2 * direction == toLine &&
                    chessBoard.board[line + direction][column] == null) {
                return true;
            }
        }

        // Взятие фигуры
        if (Math.abs(column - toColumn) == 1 && line + direction == toLine &&
                destinationPiece != null && !destinationPiece.getColor().equals(this.color)) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}