package logic.chessPieces;

import logic.Board;
import logic.Move;

import java.util.ArrayList;

class PieceUtils {
    /**
     * Get all move in a certain direction (xDirection,yDirection)
     * for a start point(xPosition,yPosition)
     * used for Bishops, Rooks and Queens
     * @param xPosition xPosition of start point
     * @param yPosition yPosition of start point
     * @param xDirection direction on x-axis (if +1 up, if -1 down)
     * @param yDirection direction on y-axis (if +1 up, if -1 down)
     * @param board currentBoard
     */
    protected static ArrayList<Move> getAllMovesInLine(int xPosition, int yPosition, int xDirection, int yDirection, Board board) {
        if(xDirection>1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(xDirection<-1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(yDirection>1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(yDirection<-1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        int xStartPosition = xPosition;
        int yStartPosition = yPosition;
        ArrayList<Move> moves = new ArrayList<>();
        while(xPosition+xDirection>=0 && xPosition+xDirection< board.getWidth() && yPosition+yDirection>=0 && yPosition+yDirection<board.getHeight()){
            xPosition+=xDirection;
            yPosition+=yDirection;
            moves.add(new Move(xStartPosition,yStartPosition ,xPosition, yPosition));
        }
        return moves;
    }

    /**
     * filters all the moves that are impossible due to
     * being behind different piece of the piece's color
     * @param moves object with info about the move
     * @param board object with current piece position
     */
    protected static ArrayList<Move> filterMovesBeforeAPiece(ArrayList<Move> moves, Board board) {
        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            PieceColor currColor = board.getPieceColorOnMoveEnd(move);
            filteredMoves.add(move);
            if (currColor != null) {
                break;
            }
        }
        return filteredMoves;
    }
    /**
     * filters all the moves that are impossible due to
     * being behind different piece of different color from the piece's color
     * @param moves object with info about the move
     * @param board object with current piece position
     */
    protected static ArrayList<Move> filterMovesBasedOnColor(ArrayList<Move> moves, Board board,PieceColor color) {
        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            if(board.getPieceColorOnMoveEnd(move)==null){
                filteredMoves.add(move);
                continue;
            }
            if(board.getPieceColorOnMoveEnd(move)==color) continue;
            filteredMoves.add(move);
        }
        return filteredMoves;
    }

    /**
     * filters all moves that are under attack of different color
     * used for Kings moves
     * @param moves object with info about the move
     * @param board object with current piece position
     * @param color color of the piece, whose move need to be filtered
     */
    protected static ArrayList<Move> filterMovesUnderAttackOfColor(ArrayList<Move> moves, Board board,PieceColor color) {
        Boolean[][] attacks = color.equals(PieceColor.White) ? board.getBlackAttack() : board.getWhiteAttack();

        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            if(!attacks[move.getXEnd()][move.getYEnd()])
                filteredMoves.add(move);
        }
        return filteredMoves;
    }
}
