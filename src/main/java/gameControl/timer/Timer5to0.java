package gameControl.timer;

/**
 * Timer: 5 minutes without Increment
 */
public class Timer5to0 extends AbstractGameTimer{
    public Timer5to0() {
        super(1000*60*5,1000*60*5,0);
    }
    @Override
    public String toString() {
        return "5/0";
    }
}
