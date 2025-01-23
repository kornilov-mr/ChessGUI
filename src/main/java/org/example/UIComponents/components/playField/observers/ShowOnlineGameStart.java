package org.example.UIComponents.components.playField.observers;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.VisualGameField;

/**
 * Observer for online game, which shows popup
 * with players information before the game
 */
public class ShowOnlineGameStart implements INotifyState {
    private final MainPlayField mainPlayField;

    public ShowOnlineGameStart(VisualGameField visualGameField) {
        this.mainPlayField = visualGameField.getMainPlayField();
    }

    @Override
    public void notifyChangeColor(PieceColor color) {

    }

    @Override
    public void notifyOnlineGameStart(String username, Integer elo) {
        mainPlayField.getPopupLayer().showNewOnlineGamePopup(username, elo);
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
