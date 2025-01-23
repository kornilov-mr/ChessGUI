package org.example.UIComponents.components.playField.symbolLayer;

import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * creates arrows on mouse clicks
 */
public class ArrowMouseListener implements MouseListener {
    private final MainPlayField mainPlayField;
    private SquareIndexes lastSquarePressedPositionOnScreen;

    public ArrowMouseListener(MainPlayField mainPlayField) {
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
            lastSquarePressedPositionOnScreen=currentSquareOnScreen;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (lastSquarePressedPositionOnScreen == null) return;
        if (!mainPlayField.areCoordsInBounds(e.getX(),e.getY())) return;
        SquareIndexes currSquareOnScreen = mainPlayField.getSquareIndexesFromMousePositionOnScreen(e.getX(),e.getY());
        if(currSquareOnScreen.equals(lastSquarePressedPositionOnScreen)) {
            lastSquarePressedPositionOnScreen=null;
            return;
        }
        Arrow arrow = new Arrow(lastSquarePressedPositionOnScreen.getX(), lastSquarePressedPositionOnScreen.getY(),
                currSquareOnScreen.getX(), currSquareOnScreen.getY());

        mainPlayField.getSymbolLayer().addOrDeleteArrow(arrow);
        mainPlayField.getSymbolLayer().repaint();
        lastSquarePressedPositionOnScreen=null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
