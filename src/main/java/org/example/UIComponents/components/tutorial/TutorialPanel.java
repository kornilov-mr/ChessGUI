package org.example.UIComponents.components.tutorial;


import org.example.UIComponents.components.playField.VisualGameField;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with all TutorialChoosePanel
 */
public class TutorialPanel extends JLayeredPane {
    private final JPanel mainPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel hintAndChoosePanel = new JPanel();
    private final JPanel hintPanel = new JPanel();
    private final JTextArea disriptionTextPane = new JTextArea();
    private final JPanel choosePanel = new JPanel();

    private final VisualGameField visualGameField;

    public TutorialPanel(JFrame jFrame) {
        setLayout(new OverlayLayout(this));

        this.visualGameField = new VisualGameField(80);



        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        boardPanel.add(visualGameField);

        mainPanel.add(boardPanel, BorderLayout.CENTER);

        hintAndChoosePanel.setLayout(new BorderLayout());
        mainPanel.add(hintAndChoosePanel, BorderLayout.EAST);

        hintPanel.setLayout(new BorderLayout());
        hintPanel.add(disriptionTextPane, BorderLayout.CENTER);

        disriptionTextPane.setText("Here will be hint about tutorial");
        disriptionTextPane.setEditable(false);
        disriptionTextPane.setFocusable(false);
        disriptionTextPane.setLineWrap(true);
        disriptionTextPane.setWrapStyleWord(true);

        choosePanel.setLayout(new BoxLayout(choosePanel, BoxLayout.Y_AXIS));
        hintAndChoosePanel.add(hintPanel, BorderLayout.NORTH);
        hintAndChoosePanel.add(choosePanel, BorderLayout.CENTER);


        for(TutorialDescriptionAndPositionEnum tutorial : TutorialDescriptionAndPositionEnum.values()) {
            TutorialChoosePanel tutorialChoosePanel = new TutorialChoosePanel(disriptionTextPane, visualGameField,tutorial);
            choosePanel.add(tutorialChoosePanel);
        }
        choosePanel.revalidate();

    }

}
