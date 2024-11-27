public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)) {
            ChessPiece piece = board[startLine][startColumn];
            ChessPiece targetPiece = board[endLine][endColumn];

            if (piece != null && nowPlayer.equals(piece.getColor())) {

                // Проверка возможности перемещения
                if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                    if (targetPiece == null || !targetPiece.getColor().equals(piece.getColor())) {
                        // Если на целевой позиции вражеская фигура, она будет "съедена"
                        board[endLine][endColumn] = piece; // Перенос фигуры
                        board[startLine][startColumn] = null; // Очистка начальной позиции

                        // Отслеживания движения короля и ладьи
                        if (piece instanceof Rook || piece instanceof King) {
                            piece.check = false;
                        }

                        // Смена текущего игрока
                        this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            return performCastling(0, 0, 4, 2, 3);
        } else {
            return performCastling(7, 0, 4, 2, 3);
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            return performCastling(0, 7, 4, 6, 5);
        } else {
            return performCastling(7, 7, 4, 6, 5);
        }
    }

    private boolean performCastling(int row, int rookColumn, int kingColumn, int newKingColumn, int newRookColumn) {
        ChessPiece king = board[row][kingColumn];
        ChessPiece rook = board[row][rookColumn];

        // Условия для рокировки
        if (king instanceof King && rook instanceof Rook && king.check && rook.check &&
                isPathClear(row, kingColumn, rookColumn)) {

            int direction = (newKingColumn > kingColumn) ? 1 : -1;

            if (!((King) king).isUnderAttack(this, row, kingColumn) &&
                    !((King) king).isUnderAttack(this, row, kingColumn + direction) &&
                    !((King) king).isUnderAttack(this, row, newKingColumn)) {

                // Исполнение рокировки
                board[row][newKingColumn] = king;
                board[row][newRookColumn] = rook;
                board[row][kingColumn] = null;
                board[row][rookColumn] = null;

                king.check = false;
                rook.check = false;

                this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    private boolean isPathClear(int row, int startCol, int endCol) {
        for (int col = Math.min(startCol, endCol) + 1; col < Math.max(startCol, endCol); col++) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }
}