package org.example.UIComponents.components.eloPage;

import javax.swing.*;

public class LeaderBoardRow extends JPanel {
    public LeaderBoardRow(String username, int elo) {
        add(new JLabel(username+": "+elo));
    }
}
