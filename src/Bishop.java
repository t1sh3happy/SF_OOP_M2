public class Bishop extends ChessPiece {

    public Bishop(String color) {
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

        int deltaRow = Math.abs(line - toLine);
        int deltaCol = Math.abs(column - toColumn);
        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];

        // Проверка на движение по диагонали
        if (deltaRow == deltaCol) {
            int rowDirection = (toLine - line) / deltaRow;
            int colDirection = (toColumn - column) / deltaCol;

            // Проверка на наличие фигур на пути
            for (int i = 1; i < deltaRow; i++) {
                if (chessBoard.board[line + i * rowDirection][column + i * colDirection] != null) {
                    return false; // Обнаружена фигура на пути
                }
            }

            // Целевая позиция должна быть пустой или занятой фигурой другого цвета
            return destinationPiece == null || !destinationPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}