package org.example.UIComponents.components.tutorial;

import org.example.UIComponents.components.playField.VisualGameField;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse listener, which updates VisualGameField on click
 * with new gameController from Enum
 */
public class TutorialChooseMouseListener implements MouseListener {

    private final JTextArea descriptionArea;
    private final VisualGameField subscribedPlayField;
    private final TutorialDescriptionAndPositionEnum tutorialInfo;

    public TutorialChooseMouseListener(JTextArea descriptionArea, VisualGameField subscribedPlayField, TutorialDescriptionAndPositionEnum tutorialInfo) {
        this.descriptionArea = descriptionArea;
        this.subscribedPlayField = subscribedPlayField;
        this.tutorialInfo = tutorialInfo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this)) return;
        descriptionArea.setText(tutorialInfo.getDescription());
        subscribedPlayField.setVisualGameController(tutorialInfo.getTutorialGameController());
        subscribedPlayField.startGame();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
