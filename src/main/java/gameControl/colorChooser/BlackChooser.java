package gameControl.colorChooser;

import logic.chessPieces.PieceColor;


public class BlackChooser implements ColorChooser {
    @Override
    public PieceColor getColor() {
        return PieceColor.Black;
    }

    @Override
    public String toString() {
        return "Black";
    }
}
