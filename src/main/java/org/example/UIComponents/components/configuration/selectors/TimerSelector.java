package org.example.UIComponents.components.configuration.selectors;

import gameControl.gameController.GameBuilder;
import gameControl.timer.TimerEnum;

/**
 * Selector for timers
 */
public class TimerSelector extends AbstractSelector {

    public TimerSelector(GameBuilder gameBuilder) {
        super(gameBuilder);
        gameBuilder.setGameTimer(TimerEnum.NO_TIMER.getTamer());
    }

    @Override
    public void SelectOption(Object option) {
        if(!(option instanceof TimerEnum timer)) throw new RuntimeException("Invalid option type for TimerSelection");
        gameBuilder.setGameTimer(timer.getTamer());
    }
}
