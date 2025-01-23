package org.example.UIComponents.components.playField.observers;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.VisualGameField;

/**
 * Notifier, which changes MoveIndicator
 * whenever next color to move changes in gameController
 * (Observer pattern)
 */
public class ChangeColorNotifier implements INotifyState{
    private final VisualGameField visualGameField;

    public ChangeColorNotifier(VisualGameField visualGameField) {
        this.visualGameField = visualGameField;
    }

    @Override
    public void notifyChangeColor(PieceColor color) {
        visualGameField.getMoveIndicator().setNewIndication(color);
    }

    @Override
    public void notifyOnlineGameStart(String username, Integer elo) {

    }

    @Override
    public void notifyWin(PieceColor color) {

    }

    @Override
    public void notifyDraw() {

    }

    @Override
    public void notifyOutOfTime(PieceColor color) {

    }

    @Override
    public void notifyEndOfPuzzle() {

    }
}
