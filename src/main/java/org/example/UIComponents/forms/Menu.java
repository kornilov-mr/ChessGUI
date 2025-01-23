package org.example.UIComponents.forms;

import org.example.UIComponents.components.auth.Login;
import org.example.UIComponents.components.auth.RoundedButton;
import org.example.UIComponents.components.eloPage.EloPage;
import org.example.UIComponents.frames.ConfigurationFrame;
import org.example.UIComponents.frames.TutorialFrame;
import org.example.account.AppContext;
import org.example.serverConnection.CommonHttpBuilder;
import org.example.utils.visual.IconsEnum;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;

public class Menu extends JPanel {
    private JPanel panel1;
    private JButton playButton;
    private JButton ELOButton;
    private JButton instructionButton;
    private JButton switchUserButton;
    private JButton creditsButton;
    private JLabel menu;

    public Menu(JFrame frame) {
        frame.setTitle("Menü");
        panel1.setPreferredSize(frame.getSize());
        RoundedButton.setupButtonUI(playButton, panel1);

        instructionButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = 100;
                instructionButton.setIcon(IconsEnum.USER_MANUAL_ICON.getImageIcon(buttonWidth));
            }
        });

        ELOButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = 100;
                ELOButton.setIcon(IconsEnum.ELO_ICON.getImageIcon(buttonWidth));
            }
        });

        creditsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = 100;
                creditsButton.setIcon(IconsEnum.CREDITS_ICON.getImageIcon(buttonWidth));
            }
        });

        switchUserButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int buttonWidth = 100;
                switchUserButton.setIcon(IconsEnum.SWITCH_USER_ICON.getImageIcon(buttonWidth));
            }
        });

        add(panel1);

        playButton.addActionListener(_ -> new ConfigurationFrame());
        ELOButton.addActionListener(_ -> {
            CommonHttpBuilder.assertThatUserIsLoggedIn();
            try {
                AppContext.getAccount().updateAccountData();
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            EloPage eloPage = new EloPage(frame);
            frame.setContentPane(eloPage);
            frame.revalidate();
            frame.repaint();
        });
        switchUserButton.addActionListener(_ -> {
            Login l = new Login(frame);
            frame.setContentPane(l);
            frame.revalidate();
            frame.repaint();
        });
        creditsButton.addActionListener(_ -> {
            Credits c = new Credits(frame);
            frame.setContentPane(c);
            frame.revalidate();
            frame.repaint();
        });
        instructionButton.addActionListener(_ -> new TutorialFrame());
    }


}
