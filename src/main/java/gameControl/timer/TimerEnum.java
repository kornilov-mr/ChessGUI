package gameControl.timer;

import java.util.HashMap;
import java.util.Map;

/**
 * Enums for timers for use in UI
 */
public enum TimerEnum {
    NO_TIMER(new NoTimer(),"No timer"),
    TIMER_2_TO_0(new Timer2to0(),"2/0"),
    TIMER_2_TO_2(new Timer2to2(),"2/2"),
    TIMER_5_TO_0(new Timer5to0(),"5/0"),
    TIMER_5_TO_2(new Timer5to2(),"5/2"),
    TIMER_10_TO_0(new Timer10to0(),"10/0"),
    TIMER_10_TO_2(new Timer10to2(),"10/2");
    private final GameTimer tamer;
    private final String timerName;

    TimerEnum(GameTimer tamer, String timerName) {
        this.tamer = tamer;
        this.timerName = timerName;
    }

    private static final Map<String, TimerEnum> timerMap = new HashMap<>();
    static{
        for(TimerEnum timerEnum : TimerEnum.values()){
            timerMap.put(timerEnum.getTimerName(), timerEnum);
        }
    }
    public static TimerEnum getTimerEnumByName(String timerName){
        return timerMap.get(timerName);
    }
    public GameTimer getTamer() {
        return tamer;
    }

    public String getTimerName() {
        return timerName;
    }

    @Override
    public String toString() {
        return timerName;
    }
}
