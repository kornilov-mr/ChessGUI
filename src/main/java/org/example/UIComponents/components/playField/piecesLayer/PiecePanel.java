package org.example.UIComponents.components.playField.piecesLayer;

import javax.swing.*;

/**
 * Panel for a single piece
 */
public class PiecePanel extends JPanel {
    private JLabel pieceLabel;
    private final int XStartPosition;
    private final int YStartPosition;

    public PiecePanel(int xStartPosition, int yStartPosition) {
        this(new JLabel(), xStartPosition, yStartPosition);
    }
    public PiecePanel(JLabel pieceLabel, int xStartPosition, int yStartPosition) {
        this.pieceLabel = pieceLabel;
        XStartPosition = xStartPosition;
        YStartPosition = yStartPosition;
        add(pieceLabel);
        setOpaque(false);
        repaint();
    }
    public void removeLabel(){
        removeAll();
        this.pieceLabel = new JLabel();
        repaint();
        revalidate();
    }
    public void changeLabel(JLabel pieceLabel){
        removeAll();
        this.pieceLabel = pieceLabel;
        add(pieceLabel);
        revalidate();
        repaint();
    }
    public void hideLabel(){
        pieceLabel.setVisible(false);
        revalidate();
        repaint();
    }
    public void showLabel(){
        pieceLabel.setVisible(true);
        revalidate();
        repaint();
    }
    public void setToStartPosition(){
        pieceLabel.setLocation(XStartPosition, YStartPosition);
    }
    public void setPositionToLabel(int x, int y){
        pieceLabel.setLocation(x, y);
    }

}
