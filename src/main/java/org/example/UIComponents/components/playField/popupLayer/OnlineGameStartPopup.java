package org.example.UIComponents.components.playField.popupLayer;

import org.example.account.Account;

import javax.swing.*;

/**
 * Popup, which players start.
 * is Shown on start of online game
 */
public class OnlineGameStartPopup extends JPanel {
    public OnlineGameStartPopup(Account account, String enemyUsername, int enemyElo) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel(account.getUserName()+" elo: "+ account.getCurrElo()));
        add(new JLabel("VS"));
        add(new JLabel(enemyUsername+" elo: "+ enemyElo));
        setOpaque(true);
    }
}
