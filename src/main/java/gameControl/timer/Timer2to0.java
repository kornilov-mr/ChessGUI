package gameControl.timer;

/**
 * Timer 2 minutes without increment
 */
public class Timer2to0 extends AbstractGameTimer {
    public Timer2to0() {
        super(1000*60*2,1000*60*2,0);
    }

    @Override
    public String toString() {
        return "2/0";
    }
}
