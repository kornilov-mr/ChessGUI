package org.example.UIComponents.components.configuration;

import org.example.UIComponents.components.configuration.selectors.AbstractSelector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel which takes Enum and creates comboBox from it,
 * if object in compoBox is selected fires Selector
 * @param <E> Enum
 */
public class ConfigurationOptionBox<E extends Enum<E>> extends JPanel {
    private final JComboBox<String> optionSelector;
    private final ArrayList<E> options = new ArrayList<>();

    public ConfigurationOptionBox(Class<E> passedEnum, AbstractSelector abstractSelector, String description) {
        this.optionSelector = new JComboBox<>();

        E[] constants = passedEnum.getEnumConstants();
        for (E constant : constants) {
            optionSelector.addItem(constant.toString());
            options.add(constant);
        }
        optionSelector.addActionListener(_ -> {
            E option = options.get(optionSelector.getSelectedIndex());
            abstractSelector.SelectOption(option);
        });

        setLayout(new BorderLayout());
        add(optionSelector, BorderLayout.EAST);
        add(new JLabel(description), BorderLayout.WEST);
    }
}
