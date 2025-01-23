package org.example.UIComponents.components.playField.dragLayer;

import org.example.UIComponents.components.playField.MainPlayField;
import org.example.utils.logical.MultiLayerClickPropagation;
import org.example.utils.logical.MultiLayerMotionPropagation;

import javax.swing.*;

/**
 * Layer of MainPlayField, which is responsible for
 * dragging piece on Mouse
 */
public class FieldDragLayer extends JPanel {
    private final DragPiecesMouseMotionListener MML;
    private JLabel draggedPieceLabel;
    private boolean draggingPiece = false;
    public FieldDragLayer(MainPlayField mainPlayField) {
        setLayout(null);
        setOpaque(false);
        this.MML = new DragPiecesMouseMotionListener(mainPlayField);
        addMouseMotionListener(MML);

        addMouseListener(new MultiLayerClickPropagation());
        addMouseMotionListener(new MultiLayerMotionPropagation());
    }

    /**
     * sets new label to drag on Mouse
     */
    public void setDraggedLabel(JLabel draggedPieceLabel) {
        this.draggedPieceLabel=draggedPieceLabel;
        draggingPiece=true;
        draggedPieceLabel.setBounds(100,100,100,100);
        add(draggedPieceLabel);

        revalidate();
        repaint();
    }

    /**
     * set selected Label to certain point
     * @param x xPosition of point
     * @param y yPosition of point
     */
    public void setLocationToPanel(int x, int y) {
        draggedPieceLabel.setLocation(x - draggedPieceLabel.getWidth() / 2, y - draggedPieceLabel.getHeight() / 2);
        revalidate();
        repaint();
    }
    public void removeLabel(){
        removeAll();
        draggedPieceLabel=null;
        draggingPiece=false;
        revalidate();
        repaint();
    }
    public DragPiecesMouseMotionListener getMML() {
        return MML;
    }
    public boolean isDraggingPiece() {
        return draggingPiece;
    }
}
