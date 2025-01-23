package org.example.UIComponents.components.eloPage;

import org.example.account.Account;
import org.example.account.AppContext;

import javax.swing.*;

/**
 * Panel with all user stats
 */
public class StatsPanel extends JPanel {
    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Account account = AppContext.getAccount();
        add(new StatPanel("Username", String.valueOf(account.getUserName())));
        add(new StatPanel("Max Elo", String.valueOf(account.getMaxElo())));
        add(new StatPanel("Current Elo", String.valueOf(account.getMaxElo())));
        add(new StatPanel("Games played total", String.valueOf(account.getGamePlayed())));
    }
}
