package gameControl.colorChooser;


import logic.chessPieces.PieceColor;


/**
 * Interface for Choosing color, especially used to send the chooser on the server, and no the color,
 * which make games save from cheating playing color (because white's position is generally better)
 */
public interface ColorChooser {
    PieceColor getColor();
}
