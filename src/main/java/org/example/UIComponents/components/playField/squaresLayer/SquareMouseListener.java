package org.example.UIComponents.components.playField.squaresLayer;

import org.example.UIComponents.components.playField.MainPlayField;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MouseListener, which highlights squares on clicks
 */
public class SquareMouseListener implements MouseListener {
    private final MainPlayField mainPlayField;


    public SquareMouseListener(MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!mainPlayField.areCoordsInBounds(e.getX(),e.getY())) return;
        SquareIndexes currentSquareOnScreen = mainPlayField.getSquareIndexesFromMousePositionOnScreen(e.getX(),e.getY());
        if(e.getButton() == MouseEvent.BUTTON3) {
            mainPlayField.getSquareLayer().getSquare(currentSquareOnScreen.getX(),currentSquareOnScreen.getY()).changeHighLight();
            return;
        }
        if(e.getButton() == MouseEvent.BUTTON2) {
            mainPlayField.getSquareLayer().highLightAllMoveOfAPiece(currentSquareOnScreen.getX(),currentSquareOnScreen.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
