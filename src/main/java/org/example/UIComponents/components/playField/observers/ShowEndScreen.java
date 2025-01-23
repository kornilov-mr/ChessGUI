package org.example.UIComponents.components.playField.observers;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.VisualGameField;

/**
 * Notifiers, which reports all game ends for game controller
 * (Observer pattern)
 */
public class ShowEndScreen implements INotifyState {
    private final MainPlayField mainPlayField;

    public ShowEndScreen(VisualGameField visualGameField) {
        this.mainPlayField = visualGameField.getMainPlayField();
    }

    @Override
    public void notifyChangeColor(PieceColor color) {

    }

    @Override
    public void notifyOnlineGameStart(String username, Integer elo) {

    }

    @Override
    public void notifyWin(PieceColor color) {
        mainPlayField.getPopupLayer().showEndgamePopup(color.toString()+" Won the game");
        mainPlayField.freezeTheGame();
    }

    @Override
    public void notifyDraw() {
        mainPlayField.getPopupLayer().showEndgamePopup("Draw");
        mainPlayField.freezeTheGame();
    }

    @Override
    public void notifyOutOfTime(PieceColor color) {
        mainPlayField.getPopupLayer().showEndgamePopup(color.toString()+"Ran out of time");
        mainPlayField.freezeTheGame();
    }

    @Override
    public void notifyEndOfPuzzle() {
        mainPlayField.getPopupLayer().showEndgamePopup("End of the tutorial");
        mainPlayField.freezeTheGame();
    }
}
