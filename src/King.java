public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка корректности позиций
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) ||
                (line == toLine && column == toColumn)) {
            return false;
        }

        // Король может двигаться на одну клетку в любом направлении
        int deltaRow = Math.abs(line - toLine);
        int deltaCol = Math.abs(column - toColumn);

        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];

        if ((deltaRow <= 1 && deltaCol <= 1)) {
            // Целевая позиция должна быть пустой или занятой фигурой другого цвета
            return destinationPiece == null || !destinationPiece.getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    // Проверка, находится ли клетка под атакой
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < chessBoard.board.length; i++) {
            for (int j = 0; j < chessBoard.board[i].length; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                // Проверка, может ли враг атаковать указанную позицию
                if (piece != null && !piece.getColor().equals(this.color) &&
                        piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                    return true; // Клетка под атакой
                }
            }
        }
        return false; // Клетка не под атакой
    }

}