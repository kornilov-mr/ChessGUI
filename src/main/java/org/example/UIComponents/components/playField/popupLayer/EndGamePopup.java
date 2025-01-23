package org.example.UIComponents.components.playField.popupLayer;

import javax.swing.*;

/**
 * Popup which information about end of the game
 */
public class EndGamePopup extends JPanel {
    public EndGamePopup(String description) {
        add(new JLabel(description));
        setOpaque(true);
    }
}
