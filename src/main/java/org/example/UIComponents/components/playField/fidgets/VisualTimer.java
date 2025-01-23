package org.example.UIComponents.components.playField.fidgets;

import gameControl.gameController.AbstractGameController;
import org.example.utils.visual.TimeConverter;

import javax.swing.*;

/**
 * Timer, which updates everyFrame,
 * taking information form game Controller
 */
public class VisualTimer extends JPanel {
    private AbstractGameController visualGameController;
    private final JLabel blackTimeLabel = new JLabel();
    private final JLabel whiteTimeLabel = new JLabel();
    private final Timer timer;
    private final Runnable update;
    public VisualTimer() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(blackTimeLabel);
        add(Box.createHorizontalGlue());
        add(whiteTimeLabel);

        this.update = new Runnable() {
            @Override
            public void run() {
                if(visualGameController != null){
                    whiteTimeLabel.setText(TimeConverter.toNormalRepresentation(visualGameController.getWhiteTime()));
                    blackTimeLabel.setText(TimeConverter.toNormalRepresentation(visualGameController.getBlackTime()));
                }
            }
        };

        this.timer = new Timer(100, _ -> {
            SwingUtilities.invokeLater(update);
        });
    }

    public void stopTimer(){
        timer.stop();
    }
    public void startTimer(){
        timer.start();
    }
    public void setVisualGameController(AbstractGameController visualGameController) {
        this.visualGameController = visualGameController;
    }
}
