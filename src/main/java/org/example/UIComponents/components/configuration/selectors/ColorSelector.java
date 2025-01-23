package org.example.UIComponents.components.configuration.selectors;

import gameControl.colorChooser.ColorChooserEnum;
import gameControl.gameController.GameBuilder;

/**
 * Selector for Color
 */
public class ColorSelector extends AbstractSelector {
    public ColorSelector(GameBuilder gameBuilder) {
        super(gameBuilder);
        gameBuilder.setColorChooser(ColorChooserEnum.RANDOM_CHOOSER.getColorChooser());
    }

    @Override
    public void SelectOption(Object option) {
        if(!(option instanceof ColorChooserEnum colorChooserEnum)) throw new RuntimeException("Invalid option type for colorSelection");
        gameBuilder.setColorChooser(colorChooserEnum.getColorChooser());
    }
}
