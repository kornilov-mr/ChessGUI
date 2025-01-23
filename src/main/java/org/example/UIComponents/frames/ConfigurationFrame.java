package org.example.UIComponents.frames;

import org.example.UIComponents.components.configuration.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for configuration function
 */
public class ConfigurationFrame extends JFrame {
    public ConfigurationFrame() throws HeadlessException {
        super("Tutorial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1024, 800);


        setLayout(new BorderLayout());

        ConfigurationPanel tutorialPanel = new ConfigurationPanel();
        add(tutorialPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
