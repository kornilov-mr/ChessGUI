package gameControl.timer;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.observers.INotifyState;

/**
 * Implementation without any logic, allows us to treat in the same
 * as any other timer without changing logic
 */
public class NoTimer implements GameTimer {

    @Override
    public void addNewNotification(INotifyState notifier) {

    }

    @Override
    public void changeColor() {

    }

    @Override
    public void startTimer(PieceColor pieceColor) {

    }

    @Override
    public long getTimeWhiteMillisecond() {
        return -1;
    }

    @Override
    public long getTimeBlackMillisecond() {
        return -1;
    }
    @Override
    public String toString() {
        return "No timer";
    }
}
