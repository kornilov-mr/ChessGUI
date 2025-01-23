package logic.pieceDisposition;

import logic.chessPieces.*;

public class ChessPieceFactory {
    /**
     * takes current square and String with information about the piece type
     * and returns new piece
     * @param x x-axis
     * @param y y-axis
     * @param pieceString contains info about the piece
     *                    for example "kiW" - white king
     */
    public static Piece createPieceFromString(int x,int y, String pieceString){
        Piece piece = switch (pieceString) {
            case "roW" -> new Rook(x, y, PieceColor.White);
            case "knW" -> new Knight(x, y, PieceColor.White);
            case "biW" -> new Bishop(x, y, PieceColor.White);
            case "quW" -> new Queen(x, y, PieceColor.White);
            case "kiW" -> new King(x, y, PieceColor.White);
            case "paW" -> new Pawn(x, y, PieceColor.White);
            case "roB" -> new Rook(x, y, PieceColor.Black);
            case "knB" -> new Knight(x, y, PieceColor.Black);
            case "biB" -> new Bishop(x, y, PieceColor.Black);
            case "quB" -> new Queen(x, y, PieceColor.Black);
            case "kiB" -> new King(x, y, PieceColor.Black);
            case "paB" -> new Pawn(x, y, PieceColor.Black);
            default -> null;
        };
        return piece;
    }
}
