package gameControl.timer;

/**
 * Timer: 5 minutes 2 seconds increment
 */
public class Timer5to2 extends AbstractGameTimer {
    public Timer5to2() {
        super(1000*60*5, 1000*60*5, 1000*2);
    }
    @Override
    public String toString() {
        return "5/2";
    }
}
