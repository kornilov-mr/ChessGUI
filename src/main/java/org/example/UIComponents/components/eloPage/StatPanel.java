package org.example.UIComponents.components.eloPage;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with one stat
 */
public class StatPanel extends JPanel {

    public StatPanel(String key, String value) {
        setLayout(new BorderLayout());
        add(new JLabel(key+":"), BorderLayout.WEST);
        add(new JLabel(" "+value), BorderLayout.EAST);
    }
}
