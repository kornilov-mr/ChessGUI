package org.example.UIComponents.components.playField.fidgets;

import logic.chessPieces.PieceColor;
import org.example.utils.visual.ColorsEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Panel, which show next color to move
 * updates via ChangeColorNotifier from GameController
 * (Observer Pattern)
 */
public class MoveIndicator extends JPanel {
    private final JPanel panelWithColor = new JPanel();

    public MoveIndicator() {
        panelWithColor.setPreferredSize(new Dimension(100, 100));
        setNewIndication(PieceColor.White);
        add(panelWithColor);
    }
    public void setNewIndication(PieceColor color) {
        panelWithColor.setBackground(color.equals(PieceColor.Black)?
                ColorsEnum.BLACK_PIECE_COLOR.getColor():
                ColorsEnum.WHITE_PIECE_COLOR.getColor());
        revalidate();
        repaint();
    }
}
