package logic;

import logic.chessPieces.Piece;

import java.util.Objects;

/**
 * Class, inheritor of Move with
 * additional information about the piece chosen by user
 */
public class PromotionMove extends Move {
    private final Piece piece;

    public PromotionMove(Move move, Piece piece) {
        this(move.getXStart(), move.getYStart(), move.getXEnd(), move.getYEnd(), piece);

    }

    public PromotionMove(int xStart, int yStart, int xEnd, int yEnd, Piece piece) {
        super(xStart, yStart, xEnd, yEnd);
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), piece);
    }

    @Override
    public String toString() {
        return "PromotionMove{" +
                "piece=" + piece +
                "Base=" + super.toString() +
                '}';
    }
}
