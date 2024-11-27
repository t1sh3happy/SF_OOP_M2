public class Rook extends ChessPiece {

    public Rook(String color) {
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

        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];

        // Ладья движется либо по строке, либо по столбцу
        if (line == toLine || column == toColumn) {
            int rowDirection = (toLine == line) ? 0 : (toLine - line) / Math.abs(toLine - line);
            int colDirection = (toColumn == column) ? 0 : (toColumn - column) / Math.abs(toColumn - column);
            int maxSteps = Math.max(Math.abs(toLine - line), Math.abs(toColumn - column));

            // Проверка на наличие фигур на пути
            for (int i = 1; i < maxSteps; i++) {
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
        return "R";
    }
}