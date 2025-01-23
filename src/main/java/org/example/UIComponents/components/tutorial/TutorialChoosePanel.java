package org.example.UIComponents.components.tutorial;

import org.example.UIComponents.components.playField.VisualGameField;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with icon, name and description of Tutorial
 * uses TutorialChooseMouseListener
 */
public class TutorialChoosePanel extends JPanel{

    public TutorialChoosePanel(JTextArea descriptionArea, VisualGameField subscribedPlayField, TutorialDescriptionAndPositionEnum tutorialInfo) {
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(tutorialInfo.getImageIcon());

        JLabel titleLabel = new JLabel();
        titleLabel.setText(tutorialInfo.getName());

        mainPanel.add(iconLabel, BorderLayout.WEST);
        mainPanel.add(titleLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        addMouseListener(new TutorialChooseMouseListener(descriptionArea, subscribedPlayField, tutorialInfo));
    }
}
