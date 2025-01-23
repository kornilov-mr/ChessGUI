package gameControl.timer;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.observers.INotifyState;

import java.util.ArrayList;

/**
 * Interface for timers
 */
public interface GameTimer {
    ArrayList<INotifyState> subscribed = new ArrayList<>();
    void addNewNotification(INotifyState notifier);
    void changeColor();
    void startTimer(PieceColor pieceColor);
    long getTimeWhiteMillisecond();

    long getTimeBlackMillisecond();
}
