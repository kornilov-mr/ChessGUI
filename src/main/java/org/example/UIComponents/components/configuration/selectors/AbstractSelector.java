package org.example.UIComponents.components.configuration.selectors;

import gameControl.gameController.GameBuilder;

/**
 * Class for Selectors
 */
public abstract class AbstractSelector {
    protected final GameBuilder gameBuilder;

    protected AbstractSelector(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    /**
     * takes Object, which is some of gameBuilder field
     * @param option one of gameBuilder fields
     */
    public abstract void SelectOption(Object option);
}
