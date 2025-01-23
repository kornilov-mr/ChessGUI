package gameControl.timer;

/**
 * Timer: 10 minutes with 2 second increment
 */
public class Timer10to2 extends AbstractGameTimer{
    public Timer10to2() {
        super(1000*60*10,1000*60*10,1000*2);
    }
    @Override
    public String toString() {
        return "10/2";
    }
}
