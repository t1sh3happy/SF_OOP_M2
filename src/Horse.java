public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка корректности позиций и избежание попытки остаться на месте
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) ||
                (line == toLine && column == toColumn)) {
            return false;
        }

        // Проверка разрешенного движения коня
        int deltaRow = Math.abs(line - toLine);
        int deltaCol = Math.abs(column - toColumn);

        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];

        if ((deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2)) {
            // Конь может двигаться только на пустую клетку или занимать клетку противника
            return destinationPiece == null || !destinationPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}