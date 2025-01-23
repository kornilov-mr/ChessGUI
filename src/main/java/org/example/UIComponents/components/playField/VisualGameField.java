package org.example.UIComponents.components.playField;

import gameControl.gameController.AbstractGameController;
import gameControl.gameController.OnlineGameController;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.fidgets.ChangeOrientationAction;
import org.example.UIComponents.components.playField.fidgets.MoveIndicator;
import org.example.UIComponents.components.playField.fidgets.OrientationSwitcher;
import org.example.UIComponents.components.playField.fidgets.VisualTimer;
import org.example.UIComponents.components.playField.fieldUpdaters.FieldUpdaterFactory;
import org.example.UIComponents.components.playField.fieldUpdaters.IUpdateField;
import org.example.UIComponents.components.playField.observers.ChangeColorNotifier;
import org.example.UIComponents.components.playField.observers.ShowEndScreen;
import org.example.UIComponents.components.playField.observers.ShowOnlineGameStart;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with main playing field and all fidgets:
 * Timer, Orientation Switcher, Move indicator
 */
public class VisualGameField extends JPanel {

    private AbstractGameController gameController;
    private final MoveIndicator moveIndicator;
    private final MainPlayField mainPlayField;
    private final VisualTimer visualTimer;
    private final JLabel idLabel;
    private final int squareSize;
    private final FieldUpdaterFactory fieldUpdaterFactory;
    public VisualGameField(int squareSize) {
        this.squareSize = squareSize;
        setLayout(new BorderLayout());


        this.mainPlayField = new MainPlayField(PieceColor.White,squareSize, this);
        this.visualTimer = new VisualTimer();
        this.fieldUpdaterFactory = new FieldUpdaterFactory(this);
        this.moveIndicator = new MoveIndicator();
        add(mainPlayField, BorderLayout.CENTER);
        this.idLabel = new JLabel();
        JPanel fidgetsPanel = new JPanel();
        fidgetsPanel.setLayout(new BoxLayout(fidgetsPanel, BoxLayout.X_AXIS));
        fidgetsPanel.add(idLabel);
        fidgetsPanel.add(moveIndicator);
        fidgetsPanel.add(visualTimer);
        fidgetsPanel.add(new OrientationSwitcher(squareSize,new ChangeOrientationAction(this)));
        add(fidgetsPanel, BorderLayout.NORTH);
    }

    /**
     * sets new logic and updates
     * all observers on new gameController
     */
    public void setVisualGameController(AbstractGameController gameController) {
        this.gameController = gameController;

        IUpdateField fieldUpdater = fieldUpdaterFactory.createFieldUpdater(gameController);
        gameController.addFieldUpdater(fieldUpdater);
        gameController.addINotify(new ShowEndScreen(this));
        gameController.addINotify(new ChangeColorNotifier(this));
        gameController.addINotify(new ShowOnlineGameStart(this));

        visualTimer.setVisualGameController(gameController);
        mainPlayField.getPiecesLayer().setUpdateField(fieldUpdater);
        mainPlayField.setVisualGameController(gameController);

        mainPlayField.getPiecesLayer().getPiecesMouseListener().startListener();
        moveIndicator.setNewIndication(PieceColor.White);
        if(gameController instanceof OnlineGameController onlineGameController){
            idLabel.setText(String.valueOf(onlineGameController.getGameId()));
        }
    }
    public void startGame(){
        gameController.startGame();
        visualTimer.startTimer();
    }
    public void freezeTheGame(){
        visualTimer.stopTimer();
        mainPlayField.getPopupLayer().hidePromotionSelector();
        mainPlayField.getDragLayer().removeLabel();
        mainPlayField.getSymbolLayer().deleteAllArrows();
        mainPlayField.getSquareLayer().reverseHighLighting();
        mainPlayField.getPiecesLayer().getPiecesMouseListener().stopListener();
    }
    public void reverseHighLight(){
        mainPlayField.getSquareLayer().reverseHighLighting();
    }

    public AbstractGameController getGameController() {
        return gameController;
    }


    public MainPlayField getMainPlayField() {
        return mainPlayField;
    }

    public VisualTimer getVisualTimer() {
        return visualTimer;
    }

    public MoveIndicator getMoveIndicator() {
        return moveIndicator;
    }

    public int getSquareSize() {
        return squareSize;
    }
}
