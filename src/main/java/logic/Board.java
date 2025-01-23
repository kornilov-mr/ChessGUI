package logic;

import logic.chessPieces.*;
import logic.pieceDisposition.BoardDispositionSetting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Class, which handles all basic piece logic,
 * like move pieces and detecting draw and win
 */
public class Board {
    private final int height;
    private final int width;

    private final Piece whiteKing;
    private final Piece blackKing;


    private final Piece[][] pieces;
    private Boolean[][] whiteAttack;
    private Boolean[][] blackAttack;

    private boolean isThereAnyPossibleBlackMoves;
    private boolean isThereAnyPossibleWhiteMoves;

    public Board(BoardDispositionSetting boardDispositionSetting) {
        this.height = boardDispositionSetting.getHeight();
        this.width = boardDispositionSetting.getWidth();
        this.pieces = boardDispositionSetting.getPieces();
        this.whiteKing = boardDispositionSetting.getWhiteKing();
        this.blackKing = boardDispositionSetting.getBlackKing();
        calculateTilesUnderAttack();
        calculateIsThereMoves();
    }

    /**
     * checks if there are possible move for a color
     * to then report lose or draw
     */
    public void calculateIsThereMoves() {
        isThereAnyPossibleBlackMoves = false;
        isThereAnyPossibleWhiteMoves = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Piece piece = getPieceOnTile(i, j);
                if (piece == null) continue;
                calculateTilesUnderAttack();
                if (piece.getPieceColor().equals(PieceColor.Black) && !isThereAnyPossibleBlackMoves) {
                    ArrayList<Move> moves = piece.getAllMovesInBounds(this);
                    for (Move move : moves) {
                        if (!isKingOfColorUnderAttackAfterMove(PieceColor.Black, move)) {
                            isThereAnyPossibleBlackMoves = true;
                            break;
                        }
                    }
                } else if (piece.getPieceColor().equals(PieceColor.White) && !isThereAnyPossibleWhiteMoves) {
                    ArrayList<Move> moves = piece.getAllMovesInBounds(this);
                    for (Move move : moves) {
                        if (!isKingOfColorUnderAttackAfterMove(PieceColor.White, move)) {
                            isThereAnyPossibleWhiteMoves = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * updates whiteAttack and blackAttack
     */
    public void calculateTilesUnderAttack() {
        Boolean[][] blackAttack = new Boolean[width][height];
        Boolean[][] whiteAttack = new Boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blackAttack[i][j] = false;
                whiteAttack[i][j] = false;
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Piece piece = getPieceOnTile(i, j);
                if (piece == null) continue;
                if (piece.getPieceColor().equals(PieceColor.Black)) {
                    ArrayList<Move> Attacks = piece.getAllAttacksOfPieceInBounds(this);
                    for (Move move : Attacks) {
                        blackAttack[move.getXEnd()][move.getYEnd()] = true;
                    }
                } else {
                    ArrayList<Move> Attacks = piece.getAllAttacksOfPieceInBounds(this);
                    for (Move move : Attacks) {
                        whiteAttack[move.getXEnd()][move.getYEnd()] = true;
                    }
                }
            }
        }
        this.blackAttack = blackAttack;
        this.whiteAttack = whiteAttack;
    }

    /**
     * return all the possible castling moves
     * @param color which color you want to get
     */
    public ArrayList<Move> castlingMovesAvailableForKingOfColor(PieceColor color) {
        return color.equals(PieceColor.Black) ? castlingMovesForBlack() : castlingMovesForWhite();
    }
    /**
     * get all castling move available in current position
     */
    public ArrayList<Move> castlingMovesForWhite() {
        if (whiteKing.getMoved()) return new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();

        Piece leftRook = pieces[0][0];
        if (leftRook instanceof Rook && !leftRook.getMoved()) {
            if (!blackAttack[0][1] && !blackAttack[0][2] && !blackAttack[0][3] &&
                    pieces[0][1] == null && pieces[0][2] == null && pieces[0][3] == null)
                moves.add(new CastingMove(0, 4, 0, 2, (Rook) leftRook, 0, 3));
        }
        Piece rightRook = pieces[0][7];
        if (leftRook instanceof Rook && !rightRook.getMoved()) {
            if (!blackAttack[0][6] && !blackAttack[0][5] &&
                    pieces[0][6] == null && pieces[0][5] == null)
                moves.add(new CastingMove(0, 4, 0, 6, (Rook) rightRook, 0, 5));
        }
        return moves;
    }

    /**
     * check if the move is a pawn move on the end of the board
     */
    public boolean shouldMoveBePromotionalMove(Move move) {
        Piece piece = getPieceOnMoveStart(move);
        if (piece == null) return false;
        if (!(piece instanceof Pawn)) return false;
        if (piece.getPieceColor().equals(PieceColor.White)) {
            return move.getXEnd() == height - 1;
        } else {
            return move.getXEnd() == 0;
        }
    }

    /**
     * get all castling move available in current position
     */
    public ArrayList<Move> castlingMovesForBlack() {
        if (blackKing.getMoved()) return new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();

        Piece leftRook = pieces[7][0];
        if (leftRook instanceof Rook) {
            if (!leftRook.getMoved()) {
                if (!whiteAttack[7][1] && !whiteAttack[7][2] && !whiteAttack[7][3] &&
                        pieces[7][1] == null && pieces[7][2] == null && pieces[7][3] == null)
                    moves.add(new CastingMove(7, 4, 7, 2, (Rook) leftRook, 7, 3));
            }
        }

        Piece rightRook = pieces[7][7];
        if (rightRook instanceof Rook) {
            if (!rightRook.getMoved()) {
                if (!whiteAttack[7][6] && !whiteAttack[7][5] &&
                        pieces[7][6] == null && pieces[7][5] == null)
                    moves.add(new CastingMove(7, 4, 7, 6, (Rook) rightRook, 7, 5));
            }
        }
        return moves;
    }

    public Boolean isKingOfColorUnderAttack(PieceColor color) {
        if (color.equals(PieceColor.Black)) {
            Piece king = blackKing;
            return whiteAttack[king.getXPosition()][king.getYPosition()];
        } else {
            Piece king = whiteKing;
            return blackAttack[king.getXPosition()][king.getYPosition()];
        }
    }

    /**
     * makes a move and places new piece on
     * the spot where was pawn, that promoted
     */
    public void makeAPromotionMove(Move move) {
        PromotionMove promotionMove = (PromotionMove) move;
        makeAMove(promotionMove);
        pieces[move.getXEnd()][move.getYEnd()] = promotionMove.getPiece();
    }

    /**
     * makes a move and then recalculates colorAttack and isThereMove
     */
    public void makeAMove(Move move) {
        makeAMoveWithoutRecalculating(move);
        Piece tempPiece = pieces[move.getXEnd()][move.getYEnd()];
        calculateTilesUnderAttack();
        calculateIsThereMoves();
        tempPiece.setMovedAsTrue();
    }

    /**
     * makes a king move and move a rook as well
     */
    public void makeACastlingMove(CastingMove castingMove) {
        makeAMove(castingMove);
        makeAMove(new Move(castingMove.getRook().getXPosition(), castingMove.getRook().getYPosition(),
                castingMove.getxRookEnd(), castingMove.getyRookEnd()));


    }

    /**
     * makes a move with recalculation underAttack and isThereMoves
     */
    private void makeAMoveWithoutRecalculating(Move move) {
        Piece tempPiece = getPieceOnMoveStart(move);
        if (tempPiece == null) throw new RuntimeException("piece on move start doesn't exist");

        pieces[move.getXStart()][move.getYStart()] = null;
        pieces[move.getXEnd()][move.getYEnd()] = tempPiece;
        tempPiece.moveToMoveEnd(move);

    }

    /**
     * checks if after a move king gets attack, used to detect "pins"
     * @param color color of the king
     * @param move object with information about the move
     */
    public Boolean isKingOfColorUnderAttackAfterMove(PieceColor color, Move move) {
        Piece piece1 = getPieceOnMoveStart(move);
        if (piece1 == null) throw new RuntimeException("piece on move start doesn't exist");

        Piece piece2 = getPieceOnMoveEnd(move);

        pieces[move.getXStart()][move.getYStart()] = null;
        pieces[move.getXEnd()][move.getYEnd()] = piece1;
        piece1.moveToMoveEnd(move);

        calculateTilesUnderAttack();
        boolean result = isKingOfColorUnderAttack(color);

        pieces[move.getXStart()][move.getYStart()] = piece1;
        pieces[move.getXEnd()][move.getYEnd()] = piece2;

        calculateTilesUnderAttack();
        piece1.moveToMoveStart(move);
        return result;
    }

    @Nullable
    public Piece getPieceOnMoveStart(Move move) {
        return pieces[move.getXStart()][move.getYStart()];
    }

    @Nullable
    public Piece getPieceOnMoveEnd(Move move) {
        return pieces[move.getXEnd()][move.getYEnd()];
    }

    @Nullable
    public PieceColor getPieceColorOnMoveStart(Move move) {
        Piece piece = getPieceOnTile(move.getXStart(), move.getYStart());
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    @Nullable
    public PieceColor getPieceColorOnMoveEnd(Move move) {
        Piece piece = getPieceOnTile(move.getXEnd(), move.getYEnd());
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    @Nullable
    public Piece getPieceOnTile(int x, int y) {
        if (x < 0 || x >= 8) return null;
        if (y < 0 || y >= 8) return null;
        return pieces[x][y];
    }

    @Nullable
    public PieceColor getPieceColorOnTile(int x, int y) {
        Piece piece = getPieceOnTile(x, y);
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    public boolean areCoordsInBounds(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) return true;
        return false;
    }

    public boolean isThereAnyPossibleBlackMoves() {
        return isThereAnyPossibleBlackMoves;
    }

    public boolean isThereAnyPossibleWhiteMoves() {
        return isThereAnyPossibleWhiteMoves;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Boolean[][] getWhiteAttack() {
        return whiteAttack;
    }

    public Boolean[][] getBlackAttack() {
        return blackAttack;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public King getWhiteKing() {
        return (King) whiteKing;
    }

    public King getBlackKing() {
        return (King) blackKing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(width).append(" ").append(height).append("\n");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Piece piece = pieces[i][j];
                if (piece == null) {
                    sb.append("--- ");
                } else {
                    sb.append(pieces[i][j].toString()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
