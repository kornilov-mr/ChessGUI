package logic.chessPieces;

import logic.Board;
import logic.Move;
import org.example.utils.visual.IconsEnum;

import java.util.ArrayList;

/**
 * basic class for piece
 */
public abstract class Piece {
    protected int xPosition;
    protected int yPosition;
    protected final PieceColor pieceColor;
    protected Boolean isMoved;

    public Piece(int xPosition, int yPosition, PieceColor pieceColor) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.pieceColor = pieceColor;
        isMoved=false;
    }

    /**
     * Takes the board and filters all the moves, so they aren't out of bounds
     * Moves and attacks of the piece are different
     * (mainly because a piece can't go on the place of another piece of it's color,
     * but can uplay pressure on it, which won't allow a king of other color to take it)
     * @param board object with current piece position
     */
    public ArrayList<Move> getAllMovesInBounds(Board board) {
        return filterOutOfBound(getAllMoves(board),board);
    }
    /**
     * Takes the board and filters all the attacks, so they aren't out of bounds
     * Moves and attacks of the piece are different
     * (mainly because a piece can't go on the place of another piece of it's color,
     * but can uplay pressure on it, which won't allow a king of other color to take it)
     * @param board object with current piece position
     */
    public ArrayList<Move> getAllAttacksOfPieceInBounds(Board board) {
        return filterOutOfBound(getAllAttacksOfPiece(board),board);
    }

    /**
     * implementation for getAllAttacksOfPieceInBounds
     * @param board object with current piece position
     */
    protected abstract ArrayList<Move> getAllAttacksOfPiece(Board board);
    /**
     * implementation for getAllMovesInBounds
     * @param board object with current piece position
     */
    protected abstract ArrayList<Move> getAllMoves(Board board);

    /**
     * return Icon of the piece
     */
    public abstract IconsEnum getImageEnum();
    public void moveToMoveEnd(Move move) {
        xPosition= move.getXEnd();
        yPosition= move.getYEnd();
    }
    public void moveToMoveStart(Move move){
        xPosition= move.getXStart();
        yPosition= move.getYStart();
    }

    /**
     * filters all moves, so they won't be out of bounds
     * @param moves  object with info about the move
     * @param board  object with current piece position
     */
    protected ArrayList<Move> filterOutOfBound(ArrayList<Move> moves,Board board){
        ArrayList<Move> allLegitMoves = new ArrayList<>();
        for(Move move: moves){
            if(move.getXEnd()>=0 && move.getXEnd()< board.getWidth() && move.getYEnd()>=0 && move.getYEnd()<board.getHeight())
                allLegitMoves.add(move);
        }
        return allLegitMoves;
    }
    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Boolean getMoved() {
        return isMoved;
    }

    public void setMovedAsTrue() {
        this.isMoved=true;
    }
    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

}
