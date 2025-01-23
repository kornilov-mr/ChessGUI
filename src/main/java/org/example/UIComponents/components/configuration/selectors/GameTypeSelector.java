package org.example.UIComponents.components.configuration.selectors;

import gameControl.gameController.GameBuilder;
import gameControl.gameController.GameTypes;

/**
 * Selector for gameTypes
 */
public class GameTypeSelector extends AbstractSelector {
    public GameTypeSelector(GameBuilder gameBuilder) {
        super(gameBuilder);
        gameBuilder.setGameType(GameTypes.OFFLINE);
    }

    @Override
    public void SelectOption(Object option) {
        if(!(option instanceof GameTypes gameType)) throw new RuntimeException("Invalid option type for gameTypeSelection");
        gameBuilder.setGameType(gameType);
    }
}
