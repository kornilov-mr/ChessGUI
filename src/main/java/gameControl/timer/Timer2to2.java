package gameControl.timer;

/**
 * Timer: 2 minutes with 2 seconds increment
 */
public class Timer2to2 extends AbstractGameTimer {
    public Timer2to2() {
        super(1000*60*2,1000*60*2,1000*2);
    }
    @Override
    public String toString() {
        return "2/2";
    }
}
