package org.example.UIComponents.components.eloPage;

import org.example.UIComponents.forms.Menu;
import org.example.utils.visual.IconsEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Page with information about User
 * with username, MaxElo, currentElo, games played
 */
public class EloPage extends JPanel {


    public EloPage(JFrame frame) {
        frame.setTitle("EloPage");
        this.setBackground(new Color(44, 44, 44));
        this.setLayout(new GridBagLayout());

        this.setPreferredSize(frame.getSize());

        JButton backButton = new JButton();
        backButton.setIcon(IconsEnum.BACK_ICON.getImageIcon(30));
        backButton.addActionListener(_ -> {
            Menu menu = new Menu(frame);
            frame.setContentPane(menu);
            frame.revalidate();
            frame.repaint();
        });
        add(new StatsPanel());
        add(backButton);
        frame.setContentPane(this);
        frame.setVisible(true);
    }
}
