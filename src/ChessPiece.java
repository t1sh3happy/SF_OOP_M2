public abstract class ChessPiece {
    protected String color;
    protected boolean check; // Следит за первым перемещением для рокировки

    public ChessPiece(String color) {
        this.color = color;
        this.check = true; // Изначально все фигуры не двигались
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    // Метод проверки первого перемещения для затемнения рокировки
    public boolean isFirstMove() {
        return check;
    }

    // Метод для установки флага первого перемещения
    public void setHasMoved() {
        this.check = false;
    }
}