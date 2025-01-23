package org.example.UIComponents.components.playField.piecesLayer;

import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.fieldUpdaters.IUpdateField;

import javax.swing.*;
import java.awt.*;

/**
 * Layer of MainPlayField, which contains all of pieces
 */
public class FieldPieceLayer extends JPanel {
    private final PiecePanel[][] panelForPieces = new PiecePanel[8][8];
    private final PiecesMouseListener piecesMouseListener;
    public IUpdateField updateField;
    public FieldPieceLayer(int squareSize, MainPlayField mainPlayField) {

        GridLayout layout = new GridLayout(8, 8);
        layout.setHgap(0);
        layout.setVgap(0);
        setBounds(0, 0, squareSize*8, squareSize*8);
        setLayout(layout);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                panelForPieces[i][j]=new PiecePanel(i*squareSize, j*squareSize);
                add(panelForPieces[i][j]);
            }
        }
        PiecesMouseListener PML = new PiecesMouseListener(squareSize,mainPlayField);
        this.piecesMouseListener = PML;
        addMouseListener(PML);
        setOpaque(false);

    }
    public void displayPieces(){
        updateField.updateField();
    }

    public PiecePanel[][] getPanelForPieces() {
        return panelForPieces;
    }

    public PiecesMouseListener getPiecesMouseListener() {
        return piecesMouseListener;
    }


    public PiecePanel getPiecePanel(int x, int y){
        return panelForPieces[x][y];
    }

    public void setUpdateField(IUpdateField updateField) {
        this.updateField = updateField;
    }
}
