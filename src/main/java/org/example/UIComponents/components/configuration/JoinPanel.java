package org.example.UIComponents.components.configuration;

import gameControl.gameController.AbstractGameController;
import org.example.UIComponents.components.playField.VisualGameField;
import org.example.UIComponents.dialogs.SimpleDialogFactory;
import org.example.serverConnection.CommonHttpBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Panel with join button
 */
public class JoinPanel extends JPanel implements ActionListener {
    private final JTextField idField;
    private final VisualGameField visualGameField;
    private final ConfigurationPanel configurationPanel;

    public JoinPanel(VisualGameField visualGameField, ConfigurationPanel configurationPanel) {
        this.visualGameField = visualGameField;
        this.configurationPanel = configurationPanel;
        this.idField = new JTextField();
        idField.setPreferredSize(new Dimension(100, 25));
        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(this);
        setLayout(new BorderLayout());

        add(idField, BorderLayout.WEST);
        add(joinButton, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Long gameId = Long.parseLong(idField.getText());
            AbstractGameController gameController = CommonHttpBuilder.connectToTheGameWithId(gameId);
            visualGameField.setVisualGameController(gameController);
            configurationPanel.disableConfigurationPanel();
        } catch (NumberFormatException ex) {
            SimpleDialogFactory.showErrorDialog("Game id should be an integer");
        } catch (URISyntaxException | InterruptedException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
