package org.example.UIComponents.components.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel with registration and Login
 */
public class LoginRegisterForm extends RoundedPanel {

    private final JButton loginButton;
    private final JTextField passwordField;
    private final JTextField usernameField;
    private final AuthInstruction authInstruction;

    public LoginRegisterForm(Color backgroundColor, int arc, String titleTest, AuthInstruction authInstruction) {
        super(backgroundColor, arc);
        this.authInstruction = authInstruction;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(40, 80, 40, 80));

        JLabel loginLabel = new JLabel(titleTest);
        loginLabel.setFont(new Font("Arial BLACK", Font.BOLD, 78));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedPanel usernamePanel = new RoundedPanel(Color.WHITE, 20);
        usernamePanel.setLayout(new BorderLayout());
        usernamePanel.setMaximumSize(new Dimension(600, 80));
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        JLabel usernameIcon = new JLabel("\uD83D\uDC64");

        this.usernameField = new JTextField();
        usernameField.setBorder(null);
        usernameField.setOpaque(false);

        usernamePanel.add(usernameIcon, BorderLayout.WEST);
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        RoundedPanel passwordPanel = new RoundedPanel(Color.WHITE, 20);
        passwordPanel.setLayout(new BorderLayout());
        passwordPanel.setMaximumSize(new Dimension(600, 80));
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        JLabel passwordIcon = new JLabel("\uD83D\uDD12");

        this.passwordField = new JPasswordField();
        passwordField.setBorder(null);
        passwordField.setOpaque(false);

        passwordPanel.add(passwordIcon, BorderLayout.WEST);
        passwordPanel.add(passwordField, BorderLayout.CENTER);


        this.loginButton = new JButton(titleTest);
        RoundedButton.setupButtonUI(loginButton, this);
        loginButton.setFont(new Font("Arial Black", Font.BOLD, 20));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(44, 44, 44));
        loginButton.setMaximumSize(new Dimension(400, 60));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(_ -> tryToAuth());

        JLabel registerLabel = new JLabel("Don't have an account? Register");
        registerLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 40)));
        add(loginLabel);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(usernamePanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordPanel);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(loginButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(registerLabel);
    }
    private void tryToAuth(){
        authInstruction.doAuthInstruction(usernameField.getText(), passwordField.getText());
    }
}
