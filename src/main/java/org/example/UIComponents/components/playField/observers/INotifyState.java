package org.example.UIComponents.components.playField.observers;

import logic.chessPieces.PieceColor;

/**
 * Interface for observers
 */
public interface INotifyState {
    void notifyChangeColor(PieceColor color);
    void notifyOnlineGameStart(String username, Integer elo);
    void notifyWin(PieceColor color);
    void notifyDraw();
    void notifyOutOfTime(PieceColor color);
    void notifyEndOfPuzzle();
}
