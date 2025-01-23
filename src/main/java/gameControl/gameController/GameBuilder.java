package gameControl.gameController;

import gameControl.colorChooser.ColorChooser;
import gameControl.colorChooser.RandomColorChooser;
import gameControl.timer.GameTimer;
import gameControl.timer.TimerEnum;
import logic.BoardController;
import logic.Move;
import logic.chessPieces.PieceColor;
import org.example.serverConnection.CommonHttpBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * Builder for GameController: handles different types of GameControllers
 * especially usefully for selecting different options separately in UI
 * (Build Pattern)
 */
public class GameBuilder {

    private ColorChooser colorChooser;
    private String gameName;
    private BoardController boardController;
    private GameControllerType gameControllerType;
    private GameTimer gameTimer;
    private Boolean isTheGameOnPoints;
    private ArrayList<Move> playersExpectedMoves = new ArrayList<>();
    private ArrayList<Move> opponentsMoves = new ArrayList<>();
    private PieceColor firstColorToMove;
    private GameTypes gameType;

    public GameBuilder setGameType(GameTypes gameType) {
        this.gameType = gameType;
        return this;
    }

    public GameBuilder setFirstColorToMove(PieceColor firstColorToMove) {
        this.firstColorToMove = firstColorToMove;
        return this;
    }

    public GameBuilder setPlayersExpectedMoves(ArrayList<Move> playersExpectedMoves) {
        this.playersExpectedMoves = playersExpectedMoves;
        return this;
    }

    public GameBuilder setOpponentsMoves(ArrayList<Move> opponentsMoves) {
        this.opponentsMoves = opponentsMoves;
        return this;
    }

    public GameBuilder setColorChooser(ColorChooser colorChooser) {
        this.colorChooser=colorChooser;
        return this;
    }
    public GameBuilder setBoardController(BoardController boardController) {
        this.boardController=boardController;
        return this;
    }
    public GameBuilder setGameTimer(GameTimer gameTimer) {
        this.gameTimer=gameTimer;
        return this;
    }
    public GameBuilder setIsTheGameOnPoints(boolean isTheGameOnPoints) {
        this.isTheGameOnPoints=isTheGameOnPoints;
        return this;
    }
    public GameBuilder setIsTheGameOnPoints(IsGameOnPointsEnum isTheGameOnPoints) {
        this.isTheGameOnPoints=isTheGameOnPoints.getValue();
        return this;
    }
    public GameBuilder setGameName(String gameName) {
        this.gameName=gameName;
        return this;
    }
    public GameBuilder setGameControllerType(GameControllerType gameControllerType) {
        this.gameControllerType=gameControllerType;
        return this;
    }
    public AbstractGameController build() {
        if(boardController==null) throw new RuntimeException("Board controller not set");
        if(gameControllerType==null) throw new RuntimeException("Game controller type not set");
        if(colorChooser==null) colorChooser=new RandomColorChooser();
        if(gameType==null) gameType=GameTypes.OFFLINE;
        if(gameTimer==null) gameTimer= TimerEnum.TIMER_10_TO_0.getTamer();
        if(isTheGameOnPoints==null) isTheGameOnPoints = false;
        AbstractGameController gameController = switch (gameControllerType) {
            case GameControllerType.CONSOLE_CHESS ->
                    new ConsoleGameController(colorChooser, boardController, gameTimer);
            case GameControllerType.VISUAL_CHESS -> new VisualGameController(colorChooser, boardController, gameTimer);
            case GameControllerType.TUTORIAL_CHESS -> {
                if (firstColorToMove == null) throw new RuntimeException("firstColorToMove not set");
                yield new TutorialGameController(firstColorToMove, boardController, gameTimer, playersExpectedMoves, opponentsMoves);
            }
        };
        if(gameType.equals(GameTypes.ONLINE)){
            Long gameId;
            try {
                gameId  = CommonHttpBuilder.postTable(this);
            } catch (IOException | URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            gameController = new OnlineGameController(colorChooser.getColor(),boardController,gameTimer,gameId);
        }
        return gameController;
    }

    public ColorChooser getColorChooser() {
        return colorChooser;
    }

    public String getGameName() {
        return gameName;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public GameControllerType getGameControllerType() {
        return gameControllerType;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public Boolean getTheGameOnPoints() {
        return isTheGameOnPoints;
    }

    public ArrayList<Move> getPlayersExpectedMoves() {
        return playersExpectedMoves;
    }

    public ArrayList<Move> getOpponentsMoves() {
        return opponentsMoves;
    }

    public PieceColor getFirstColorToMove() {
        return firstColorToMove;
    }

    public GameTypes getGameType() {
        return gameType;
    }
}
