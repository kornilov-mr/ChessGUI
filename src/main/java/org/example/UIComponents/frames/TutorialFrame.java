package org.example.UIComponents.frames;

import org.example.UIComponents.components.tutorial.TutorialPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for Tutorials
 */
public class TutorialFrame extends JFrame {
    public TutorialFrame() throws HeadlessException {
        super("Tutorial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1024,800);


        setLayout(new BorderLayout());

        TutorialPanel tutorialPanel = new TutorialPanel(this);
        add(tutorialPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
