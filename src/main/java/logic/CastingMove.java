package logic;

import logic.chessPieces.Rook;

/**
 * Class, inheritor of Move with additional information about the rook,
 * which should move after the King's move
 */
public class CastingMove extends Move {
    private final Rook rook;
    private final int xRookEnd;
    private final int yRookEnd;
    public CastingMove(int xStart, int yStart, int xEnd, int yEnd, Rook rook, int xRookEnd, int yRookEnd) {
        super(xStart, yStart, xEnd, yEnd);
        this.rook=rook;
        this.xRookEnd=xRookEnd;
        this.yRookEnd=yRookEnd;
    }

    public Rook getRook() {
        return rook;
    }

    public int getxRookEnd() {
        return xRookEnd;
    }

    public int getyRookEnd() {
        return yRookEnd;
    }
}
