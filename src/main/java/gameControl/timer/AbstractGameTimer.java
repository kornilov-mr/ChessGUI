package gameControl.timer;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.observers.INotifyState;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer class for internal Game controller,
 * which trigger every 100 milliseconds and updates time
 */
public abstract class AbstractGameTimer implements GameTimer {
    private long timeWhiteMillisecond;
    private long timeBlackMillisecond;
    private final long timeAddForMove;
    private final Timer timer =  new Timer();
    private PieceColor currentColor;
    protected long deltaTime;
    public AbstractGameTimer(long timeWhiteMillisecond, long timeBlackMillisecond, long timeAddForMove) {
        this.timeWhiteMillisecond = timeWhiteMillisecond;
        this.timeBlackMillisecond = timeBlackMillisecond;
        this.timeAddForMove = timeAddForMove;
    }

    /**
     * changes which time should decrease next
     */
    @Override
    public void changeColor() {
        if(currentColor==null) throw new RuntimeException("Timer isn't running");
        if(currentColor.equals(PieceColor.Black)){
            timeWhiteMillisecond+=timeAddForMove;
            this.currentColor= PieceColor.White;
        }else{
            timeBlackMillisecond+=timeAddForMove;
            this.currentColor= PieceColor.Black;
        }

    }
    @Override
    public void startTimer(PieceColor pieceColor) {
        currentColor=pieceColor;
        deltaTime=new Date().getTime();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(currentColor.equals(PieceColor.Black)) {
                    timeWhiteMillisecond = timeWhiteMillisecond - (new Date().getTime()-deltaTime);
                    if(timeWhiteMillisecond<0){
                        timeWhiteMillisecond=0;
                        for (INotifyState notifier: subscribed){
                            notifier.notifyOutOfTime(pieceColor);
                        }
                        timer.cancel();
                    }
                }else{
                    timeBlackMillisecond = timeBlackMillisecond - (new Date().getTime()-deltaTime);
                    if(timeBlackMillisecond<0){
                        timeBlackMillisecond=0;
                        for (INotifyState notifier: subscribed){
                            notifier.notifyOutOfTime(pieceColor);
                        }
                        timer.cancel();
                    }
                }
                deltaTime=new Date().getTime();
            }
        },100,100);
    }
    @Override
    public long getTimeWhiteMillisecond() {
        return timeWhiteMillisecond;
    }
    @Override
    public long getTimeBlackMillisecond() {
        return timeBlackMillisecond;
    }

    @Override
    public void addNewNotification(INotifyState notifier) {
        subscribed.add(notifier);
    }

}
