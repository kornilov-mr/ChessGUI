package org.example.UIComponents.components.playField.fieldUpdaters;

import gameControl.gameController.AbstractGameController;
import logic.Board;
import logic.BoardController;
import logic.chessPieces.King;
import logic.chessPieces.Piece;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.VisualGameField;
import org.example.UIComponents.components.playField.piecesLayer.PiecePanel;
import org.example.UIComponents.components.playField.squaresLayer.FieldSquareLayer;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;
import org.example.utils.sound.SoundEnum;

import javax.swing.*;

/**
 * Updates all the pieces on the Field
 */
public class BasicFieldUpdate implements IUpdateField {
    protected final VisualGameField visualGameField;

    public BasicFieldUpdate(VisualGameField visualGameField) {
        this.visualGameField = visualGameField;
    }

    @Override
    public void updateField() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                doUpdate();
            }
        });
    }

    @Override
    public void doUpdate() {
        AbstractGameController gameController = visualGameField.getGameController();
        BoardController boardController = gameController.getBoardController();
        Board board = boardController.getBoard();
        PiecePanel[][] panelForPieces = visualGameField.getMainPlayField().getPiecesLayer().getPanelForPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                panelForPieces[i][j].removeLabel();
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPieceOnTile(i, j);
                if (piece == null) continue;
                if (visualGameField.getMainPlayField().getColorOrientation().equals(PieceColor.Black)) {
                    panelForPieces[i][j].changeLabel(piece.getImageEnum().getPieceLabel(visualGameField.getSquareSize()));
                } else {
                    panelForPieces[7 - i][j].changeLabel(piece.getImageEnum().getPieceLabel(visualGameField.getSquareSize()));
                }
            }
        }
        FieldSquareLayer fieldSquareLayer = visualGameField.getMainPlayField().getSquareLayer();

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                fieldSquareLayer.getSquare(i,j).changeShahHighlight();
            }
        }

        if (board.isKingOfColorUnderAttack(PieceColor.Black)) {
            King king = board.getBlackKing();
            SquareIndexes square = visualGameField.getMainPlayField().getSquaresOnScreenFromSquaresOnBoard(king.getXPosition(), king.getYPosition());
            fieldSquareLayer.getSquare(square.getY(), square.getX()).setShahColor();
            SoundEnum.CHECK_SOUND.getClip().start();
        }
        if (board.isKingOfColorUnderAttack(PieceColor.White)) {
            King king = board.getWhiteKing();
            SquareIndexes square = visualGameField.getMainPlayField().getSquaresOnScreenFromSquaresOnBoard(king.getXPosition(), king.getYPosition());
            fieldSquareLayer.getSquare(square.getY(), square.getX()).setShahColor();
            SoundEnum.CHECK_SOUND.getClip().start();
        }
        visualGameField.getMainPlayField().revalidate();
        visualGameField.getMainPlayField().repaint();
    }
}
