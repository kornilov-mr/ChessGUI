package org.example.UIComponents.components.playField.fidgets;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.VisualGameField;

/**
 * Action to change orientation of the field
 */
public class ChangeOrientationAction implements Runnable {
    private final MainPlayField mainPlayField;
    public ChangeOrientationAction(VisualGameField visualGameField) {
        this.mainPlayField = visualGameField.getMainPlayField();
    }

    @Override
    public void run() {
        mainPlayField.setColorOrientation(PieceColor.changePieceColor(mainPlayField.getColorOrientation()));
        mainPlayField.getSquareLayer().changeOrientationOfHighlightedSquares();
        mainPlayField.getSymbolLayer().changeArrowsOrientation();
        mainPlayField.getSymbolLayer().changeOrientation();
        mainPlayField.getPiecesLayer().displayPieces();
        mainPlayField.revalidate();
        mainPlayField.repaint();
    }
}
