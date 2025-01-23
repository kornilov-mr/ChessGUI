package org.example.UIComponents.components.auth;


import org.example.UIComponents.forms.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with login
 */
public class Login extends JPanel {

    private final JButton switchToDifferentAuth;
    private LoginState currState;
    private final JButton backButton;
    public Login(JFrame frame) {
        frame.setTitle("Login");
        currState = LoginState.Login;
        this.setBackground(new Color(44, 44, 44));
        this.setLayout(new GridBagLayout());

        this.setPreferredSize(frame.getSize());

        LoginRegisterForm loginRegisterForm = new LoginRegisterForm(new Color(255, 255, 255, 50), 30,
                "Login",new LoginInstruction());

        this.add(loginRegisterForm);

        this.switchToDifferentAuth = new JButton("register");
        switchToDifferentAuth.addActionListener(_ -> switchToAuth());
        this.backButton = new JButton("Back");
        backButton.addActionListener(_ -> {
            Menu menu = new Menu(frame);
            frame.setContentPane(menu);
            frame.revalidate();
            frame.repaint();
        });
        add(switchToDifferentAuth);
        add(backButton);
        frame.setContentPane(this);
        frame.setVisible(true);

    }

    /**
     * Switches to different state
     * from register to login
     * from login to register
     */
    private void switchToAuth() {
        this.removeAll();
        switchToDifferentAuth.setText(currState.toString());
        currState = LoginState.switchToNext(currState);
        LoginRegisterForm loginRegisterForm = new LoginRegisterForm(new Color(255, 255, 255, 50), 30,
                currState.toString(),currState.getAuthInstruction());

        this.add(loginRegisterForm);
        this.add(switchToDifferentAuth);
        this.add(backButton);
        revalidate();
        repaint();

    }
}
