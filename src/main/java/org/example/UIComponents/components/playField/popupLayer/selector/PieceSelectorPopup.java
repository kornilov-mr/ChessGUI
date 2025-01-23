package org.example.UIComponents.components.playField.popupLayer.selector;

import logic.Move;
import logic.chessPieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel with all piece, which user can select,
 * on selection fire ISelectPieces
 */
public class PieceSelectorPopup extends JPanel implements MouseListener {
    private final int squareSize;
    private final ArrayList<Piece> pieces;
    private final Map<JLabel, Piece> labelToPiece;
    private final ArrayList<ISelectPieces> subscribed;
    private final Move move;

    public PieceSelectorPopup( int squareSize, ArrayList<Piece> pieces, Move move) {
        this.squareSize = squareSize;
        this.pieces = pieces;
        this.move = move;
        this.labelToPiece = new HashMap<>();
        this.subscribed = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(Piece piece : pieces) {
            JLabel pieceLabel = piece.getImageEnum().getPieceLabel((int) (squareSize*0.8));
            labelToPiece.put(pieceLabel,piece);
            add(pieceLabel);
        }
        addMouseListener(this);
    }
    public void subscribeTo(ISelectPieces iSelectPieces){
        subscribed.add(iSelectPieces);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component component = SwingUtilities.getDeepestComponentAt(this, e.getX()-getX(), e.getY()-getY());
        if(!(component instanceof JLabel)) return;
        Piece selectedPiece = labelToPiece.get((JLabel) component);
        if(selectedPiece == null) return;
        for(ISelectPieces iSelectPieces : subscribed){
            iSelectPieces.onPieceSelected(selectedPiece, move);
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

    @Override
    public int getWidth() {
        return squareSize*pieces.size()+10;
    }

    @Override
    public int getHeight() {
        return squareSize+10;
    }
}
