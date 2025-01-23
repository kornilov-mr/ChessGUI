package logic.chessPieces;

import logic.Board;
import logic.Move;
import org.example.utils.visual.IconsEnum;

import java.util.ArrayList;

/**
 * Implementation for Queens
 */
public class Queen extends Piece {
    public Queen(int xPosition, int yPosition, PieceColor pieceColor) {
        super(xPosition, yPosition, pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllMoves(Board board) {
        ArrayList<Move> moves = getAllAttacksOfPieceInBounds(board);
        return PieceUtils.filterMovesBasedOnColor(moves,board,pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllAttacksOfPiece(Board board) {
        ArrayList<Move> tempMoves;
        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,0,1, board);
        ArrayList<Move> moves = new ArrayList<>(PieceUtils.filterMovesBeforeAPiece(tempMoves, board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,-1,0, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,0,-1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,1,0, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,1,1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,-1,1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,1,-1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,-1,-1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));
        return moves;
    }
    @Override
    public String toString() {
        return "qu"+ (pieceColor.equals(PieceColor.Black)? "B" : "W") ;
    }
    @Override
    public IconsEnum getImageEnum() {
        return pieceColor.equals(PieceColor.Black) ? IconsEnum.BLACK_QUEEN : IconsEnum.WHITE_QUEEN;
    }
}
