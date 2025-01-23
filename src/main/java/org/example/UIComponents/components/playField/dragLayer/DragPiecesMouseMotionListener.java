package org.example.UIComponents.components.playField.dragLayer;

import org.example.UIComponents.components.playField.MainPlayField;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * MouseMotionListener,
 * which updates position of Dragged label for FieldDragLayer
 */
public class DragPiecesMouseMotionListener implements MouseMotionListener {
    private JLabel currPieceLabel;
    private final MainPlayField mainPlayField;

    public DragPiecesMouseMotionListener(MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
        this.currPieceLabel = new JLabel();
    }
    public void setPieceLabel(JLabel pieceLabel) {
        currPieceLabel=pieceLabel;
        mainPlayField.getDragLayer().setDraggedLabel(currPieceLabel);
    }
    public void deletePieceLabel(){
        currPieceLabel=new JLabel();
        mainPlayField.getDragLayer().removeLabel();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(mainPlayField.getDragLayer().isDraggingPiece()){
            mainPlayField.getDragLayer().setLocationToPanel(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
