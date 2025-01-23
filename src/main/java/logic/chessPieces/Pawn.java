package logic.chessPieces;

import logic.Board;
import logic.Move;
import org.example.utils.visual.IconsEnum;

import java.util.ArrayList;

/**
 * Implementation for pawns
 */
public class Pawn extends Piece {

    public Pawn(int xPosition, int yPosition,PieceColor color) {
        super(xPosition, yPosition,color);
    }

    @Override
    protected ArrayList<Move> getAllMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        if(pieceColor.equals(PieceColor.Black)){
            if(board.getPieceOnTile(xPosition-1,yPosition) == null){
                moves.add(new Move(xPosition,yPosition ,xPosition-1, yPosition));
                if(board.getPieceOnTile(xPosition-2,yPosition) == null){
                    if (!isMoved) moves.add(new Move(xPosition,yPosition ,xPosition-2, yPosition));
                }
            }
            if(board.areCoordsInBounds(xPosition-1,yPosition-1)){
                PieceColor colorOfNextPiece = board.getPieceColorOnTile(xPosition-1,yPosition-1);
                if(colorOfNextPiece !=null && colorOfNextPiece != pieceColor){
                    moves.add(new Move(xPosition,yPosition,xPosition-1,yPosition-1));
                }
            }
            if(board.areCoordsInBounds(xPosition-1,yPosition+1)){
                PieceColor colorOfNextPiece = board.getPieceColorOnTile(xPosition-1,yPosition+1);
                if(colorOfNextPiece !=null && colorOfNextPiece != pieceColor){
                    moves.add(new Move(xPosition,yPosition,xPosition-1,yPosition+1));
                }
            }
        }else{
            if(board.getPieceOnTile(xPosition+1,yPosition) == null){
                moves.add(new Move(xPosition,yPosition ,xPosition+1, yPosition));
                if(board.getPieceOnTile(xPosition+2,yPosition) == null){
                    if (!isMoved) moves.add(new Move(xPosition,yPosition ,xPosition+2, yPosition));
                }
            }
            if(board.areCoordsInBounds(xPosition+1,yPosition+1)){
                PieceColor colorOfNextPiece = board.getPieceColorOnTile(xPosition+1,yPosition+1);
                if(colorOfNextPiece !=null && colorOfNextPiece != pieceColor){
                    moves.add(new Move(xPosition,yPosition,xPosition+1,yPosition+1));
                }
            }
            if(board.areCoordsInBounds(xPosition+1,yPosition-1)){
                PieceColor colorOfNextPiece = board.getPieceColorOnTile(xPosition+1,yPosition-1);
                if(colorOfNextPiece !=null && colorOfNextPiece != pieceColor){
                    moves.add(new Move(xPosition,yPosition,xPosition+1,yPosition-1));
                }
            }
        }

        return PieceUtils.filterMovesBasedOnColor(moves,board,pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllAttacksOfPiece(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        if(pieceColor.equals(PieceColor.Black)){
            moves.add(new Move(xPosition,yPosition ,xPosition-1, yPosition-1));
            moves.add(new Move(xPosition,yPosition ,xPosition-1, yPosition+1));
        }else{
            moves.add(new Move(xPosition,yPosition ,xPosition+1, yPosition+1));
            moves.add(new Move(xPosition,yPosition ,xPosition+1, yPosition-1));
        }
        return moves;
    }
    @Override
    public String toString() {
        return "pa"+ (pieceColor.equals(PieceColor.Black)? "B" : "W") ;
    }
    @Override
    public IconsEnum getImageEnum() {
        return pieceColor.equals(PieceColor.Black) ? IconsEnum.BLACK_PAWN : IconsEnum.WHITE_PAWN;
    }
}
