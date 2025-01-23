package org.example.UIComponents.components.configuration;

import gameControl.colorChooser.ColorChooserEnum;
import gameControl.gameController.*;
import gameControl.timer.TimerEnum;
import logic.BoardController;
import logic.pieceDisposition.BoardDispositionEnum;
import org.example.UIComponents.components.configuration.selectors.ColorSelector;
import org.example.UIComponents.components.configuration.selectors.GameTypeSelector;
import org.example.UIComponents.components.configuration.selectors.IsGameOnPointsSelector;
import org.example.UIComponents.components.configuration.selectors.TimerSelector;
import org.example.UIComponents.components.playField.VisualGameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel with game configuration and Join button
 */
public class ConfigurationPanel extends JPanel {
    private final JPanel configPanel;
    private final JPanel boardPanel;
    private final JPanel optionSelectorPanel;
    private final GameBuilder gameBuilder = new GameBuilder();
    private final VisualGameField visualGameField;
    public ConfigurationPanel() {
        this.boardPanel = new JPanel();
        this.optionSelectorPanel = new JPanel();
        setLayout(new BorderLayout());
        this.visualGameField = new VisualGameField(80);
        this.configPanel = new JPanel();
        visualGameField.setVisualGameController(getDefaultGameController());
        boardPanel.add(visualGameField);

        optionSelectorPanel.setLayout(new BoxLayout(optionSelectorPanel, BoxLayout.Y_AXIS));
        optionSelectorPanel.add(new ConfigurationOptionBox<>(ColorChooserEnum.class,new ColorSelector(gameBuilder),"Choose color"));
        optionSelectorPanel.add(new ConfigurationOptionBox<>(TimerEnum.class,new TimerSelector(gameBuilder),"Choose timer"));
        optionSelectorPanel.add(new ConfigurationOptionBox<>(GameTypes.class,new GameTypeSelector(gameBuilder),"Choose game Type"));
        optionSelectorPanel.add(new ConfigurationOptionBox<>(IsGameOnPointsEnum.class,new IsGameOnPointsSelector(gameBuilder),"Play with rating"));

        configPanel.setLayout(new BorderLayout());
        configPanel.add(optionSelectorPanel, BorderLayout.NORTH);

        JButton createGameButton = new JButton("Create Game");
        createGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildGameAndSetUpBoard();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JoinPanel(visualGameField, this));
        panel.add(createGameButton);
        configPanel.add(panel, BorderLayout.SOUTH);

        configPanel.setBorder(BorderFactory.createEmptyBorder(250,0,0,0));
        add(configPanel, BorderLayout.EAST);
        add(boardPanel, BorderLayout.CENTER);
        setUpDefaultPreset();
    }
    private void setUpDefaultPreset(){
        gameBuilder.setIsTheGameOnPoints(true);
        gameBuilder.setGameType(GameTypes.ONLINE);
        gameBuilder.setGameTimer(TimerEnum.NO_TIMER.getTamer());
        gameBuilder.setColorChooser(ColorChooserEnum.RANDOM_CHOOSER.getColorChooser());
    }
    public AbstractGameController getDefaultGameController(){
        GameBuilder gb = new GameBuilder();
        gb.setGameControllerType(GameControllerType.VISUAL_CHESS);
        gb.setBoardController(new BoardController(BoardDispositionEnum.BASIC_POSITION_OF_PIECES_8X8.getBoardDispositionSetting()));
        gb.setColorChooser(ColorChooserEnum.WHITE_CHOOSER.getColorChooser());
        gb.setGameName("game Show off");
        gb.setGameTimer(TimerEnum.NO_TIMER.getTamer());
        gb.setIsTheGameOnPoints(false);
        return gb.build();
    }
    public void buildGameAndSetUpBoard(){
        gameBuilder.setGameControllerType(GameControllerType.VISUAL_CHESS);
        gameBuilder.setBoardController(new BoardController(BoardDispositionEnum.BASIC_POSITION_OF_PIECES_8X8.getBoardDispositionSetting()));
        gameBuilder.setGameName("new Game");
        AbstractGameController gameController = gameBuilder.build();
        visualGameField.setVisualGameController(gameController);
        if(!(gameController instanceof OnlineGameController))
            visualGameField.startGame();
        disableConfigurationPanel();
    }
    public void disableConfigurationPanel(){
        configPanel.setVisible(false);
        configPanel.setEnabled(false);
    }

}
