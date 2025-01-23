package gameControl.timer;

/**
 * Timer 10 minutes without increment
 */
public class Timer10to0 extends AbstractGameTimer{
    public Timer10to0() {
        super(1000*60*10,1000*60*10,0);
    }
    @Override
    public String toString() {
        return "10/0";
    }
}
