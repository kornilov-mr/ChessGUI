package gameControl.colorChooser;

import logic.chessPieces.PieceColor;

public class WhiteChooser implements ColorChooser {
    @Override
    public PieceColor getColor() {
        return PieceColor.White;
    }
    @Override
    public String toString() {
        return "White";
    }
}
