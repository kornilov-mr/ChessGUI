package logic.chessPieces;

import logic.Board;
import logic.Move;
import org.example.utils.visual.IconsEnum;

import java.util.ArrayList;

/**
 * Implementation for Kings
 */
public class King extends Piece {
    public King(int xPosition, int yPosition, PieceColor pieceColor) {
        super(xPosition, yPosition, pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllMoves(Board board) {
        ArrayList<Move> moves = getAllAttacksOfPieceInBounds(board);
        moves.addAll(board.castlingMovesAvailableForKingOfColor(pieceColor));
        moves= PieceUtils.filterMovesUnderAttackOfColor(moves,board,pieceColor);
        return PieceUtils.filterMovesBasedOnColor(moves,board,pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllAttacksOfPiece(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(new Move(xPosition,yPosition , xPosition + 1, yPosition + 1));
        moves.add(new Move(xPosition,yPosition ,xPosition + 1, yPosition - 1));
        moves.add(new Move(xPosition,yPosition ,xPosition - 1, yPosition + 1));
        moves.add(new Move(xPosition,yPosition ,xPosition - 1, yPosition - 1));
        moves.add(new Move(xPosition,yPosition ,xPosition, yPosition - 1));
        moves.add(new Move(xPosition,yPosition ,xPosition, yPosition + 1));
        moves.add(new Move(xPosition,yPosition ,xPosition - 1, yPosition ));
        moves.add(new Move(xPosition,yPosition ,xPosition + 1, yPosition ));
        return moves;
    }
    @Override
    public String toString() {
        return "ki"+ (pieceColor.equals(PieceColor.Black)? "B" : "W") ;
    }
    @Override
    public IconsEnum getImageEnum() {
        return pieceColor.equals(PieceColor.Black) ? IconsEnum.BLACK_KING : IconsEnum.WHITE_KING;
    }
}
