package org.example.UIComponents.components.configuration.selectors;

import gameControl.gameController.GameBuilder;
import gameControl.gameController.IsGameOnPointsEnum;

/**
 * Selector for gameOnPoints
 */
public class IsGameOnPointsSelector extends AbstractSelector {
    public IsGameOnPointsSelector(GameBuilder gameBuilder) {
        super(gameBuilder);
    }

    @Override
    public void SelectOption(Object option) {
        if(!(option instanceof IsGameOnPointsEnum isOnPoints)) throw new RuntimeException("Invalid option type for isGameOnPointsSelector");
        gameBuilder.setIsTheGameOnPoints(isOnPoints);
    }
}
