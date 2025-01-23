package logic;

import logic.chessPieces.Piece;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * class, which handles intermediate logic
 * like type of move
 */
public class BoardController {

    private final Board board;
    public BoardController(BoardDispositionSetting boardDispositionSetting) {
        this.board = new Board(boardDispositionSetting);
    }

    /**
     * makes a move with specified color
     * @param move object with info about the move
     * @param color color of playing side
     */
    public void makeAMove(Move move, PieceColor color) throws MoveIsNotValidException {
        if (!isMoveValid(move,color)) throw new MoveIsNotValidException();
        Piece tempPiece = board.getPieceOnMoveStart(move);
        if(tempPiece==null) throw new RuntimeException("piece on move start doesn't exist");
        if(move instanceof PromotionMove){
            board.makeAPromotionMove(move);
        }else if(isMoveACastleMove(move,color)){
            board.makeACastlingMove(promoteMoveToACastlingMove(move,color));
        }else{
            board.makeAMove(move);
        }
    }

    /**
     * checks if move is a castling move
     */
    public boolean isMoveACastleMove(Move move, PieceColor color){
        if (!isMoveValid(move,color)) throw new MoveIsNotValidException();
        ArrayList<Move> moves = board.castlingMovesAvailableForKingOfColor(color);
        for(Move currMove:moves){
            if(currMove.equals(move)) return true;
        }
        return false;
    }
    public CastingMove promoteMoveToACastlingMove(Move move, PieceColor color){
        if (!isMoveACastleMove(move,color)) throw new RuntimeException("Trying to promote Non Castle Move to a Castle Move");
        ArrayList<Move> moves = board.castlingMovesAvailableForKingOfColor(color);
        for(Move currMove:moves){
            if(currMove.equals(move)) return(CastingMove) currMove;
        }
        throw new RuntimeException("Couldn't promote to a castle Move");
    }

    /**
     * checks if move is valid or not
     */
    public boolean isMoveValid(Move move, PieceColor color) {
        Piece piece = board.getPieceOnMoveStart(move);
        if (piece == null) return false;
        ArrayList<Move> moves = piece.getAllMovesInBounds(board);
        for (Move currMove : moves) {
            if (currMove.equals(move)) return !board.isKingOfColorUnderAttackAfterMove(color,move);
        }
        return false;
    }

    public boolean isGameInDraw(){
        if(!board.isThereAnyPossibleBlackMoves() || !board.isThereAnyPossibleWhiteMoves()){
            return !board.isKingOfColorUnderAttack(PieceColor.Black) && !board.isKingOfColorUnderAttack(PieceColor.White);
        }
        return false;
    }
    @Nullable
    public PieceColor whoWonTheGame(){
        if(!board.isThereAnyPossibleBlackMoves() && board.isKingOfColorUnderAttack(PieceColor.Black)){
            return PieceColor.White;
        }
        if(!board.isThereAnyPossibleWhiteMoves() && board.isKingOfColorUnderAttack(PieceColor.White)){
            return PieceColor.Black;
        }
        return null;
    }
    public String getBoardAsString(){
        return board.toString();
    }

    public Board getBoard() {
        return board;
    }
}
