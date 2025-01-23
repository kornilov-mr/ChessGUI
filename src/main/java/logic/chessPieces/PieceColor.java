package logic.chessPieces;

public enum PieceColor {
    White, Black;
    public static PieceColor changePieceColor(PieceColor pieceColor) {
        return pieceColor == White ? Black : White;
    }

}
