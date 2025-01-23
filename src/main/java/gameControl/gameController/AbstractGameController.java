package gameControl.gameController;

import gameControl.timer.GameTimer;
import logic.BoardController;
import logic.Move;
import logic.MoveIsNotValidException;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.fieldUpdaters.IUpdateField;
import org.example.UIComponents.components.playField.observers.INotifyState;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Base class for games Controllers implements basic logic for games
 */
public abstract class AbstractGameController {
    /**
     * Object that handles all moves of pieces,
     * so GameController could only focus on game flow
     */
    protected BoardController boardController;
    /**
     * basic timer with notifier to report time to UI
     */
    protected final GameTimer gameTimer;

    protected PieceColor currentColorToMove;
    protected boolean started = false;

    /**
     * Notifies all the changes to UI
     * (Observer pattern)
     */
    protected final Set<INotifyState> resultsNotifiers = new HashSet<>();
    /**
     * Used to update game field according to gameController type
     * Example: Tutorial need to display arrows every update
     * (Observer pattern)
     */
    protected final Set<IUpdateField> fieldUpdaters = new HashSet<>();

    protected AbstractGameController(PieceColor currentColorToMove, BoardController boardController, GameTimer gameTimer) {
        this.currentColorToMove = currentColorToMove;
        this.boardController = boardController;
        this.gameTimer = gameTimer;
    }

    /**
     * starts the timer, allows to execute moves
     * and fires interface (used for chess in console)
     */
    public void startGame(){
        started = true;
        gameTimer.startTimer(currentColorToMove);
        start();
    }
    protected abstract void start();

    /**
     * Executes move is it possible, if the move isn't possible
     * throws MoveIsNotValidException (use to say Ui that the move played isn't allowed)
     * @param move object with info about the move
     */
    public void makeAMove(Move move) throws MoveIsNotValidException{
        if(!started) throw new MoveIsNotValidException();
        if(boardController.getBoard().getPieceOnMoveStart(move) == null) throw new MoveIsNotValidException();
        if(!Objects.equals(boardController.getBoard().getPieceColorOnMoveStart(move), currentColorToMove)) throw new MoveIsNotValidException();
        boardController.makeAMove(move,currentColorToMove);
        PieceColor winColor = whoWonTheGame();
        if(winColor!=null){
            for(INotifyState notifier : resultsNotifiers){
                notifier.notifyWin(winColor);
            }
        }
        if(isGameInDraw()){
            for(INotifyState notifier : resultsNotifiers){
                notifier.notifyDraw();
            }
        }
        for(IUpdateField updateField : fieldUpdaters){
            updateField.updateField();
        }
        changeColor();
    }

    /**
     * changes next color to play, notifying Ui about colorChange
     */
    protected void changeColor(){
        gameTimer.changeColor();
        currentColorToMove = PieceColor.changePieceColor(currentColorToMove);
        for(INotifyState notifier: resultsNotifiers){
            notifier.notifyChangeColor(currentColorToMove);
        }
    }
    public void addFieldUpdater(IUpdateField iUpdateField){
        fieldUpdaters.add(iUpdateField);
    }
    public void addINotify(INotifyState notify){
        resultsNotifiers.add(notify);
        gameTimer.addNewNotification(notify);
    }
    public boolean isGameInDraw(){
        return boardController.isGameInDraw();
    }
    @Nullable
    public PieceColor whoWonTheGame(){
        if(boardController.whoWonTheGame()!=null){
            return boardController.whoWonTheGame();
        }
        if(getWhiteTime()==0){
            return PieceColor.Black;
        }
        if(getBlackTime()==0){
            return PieceColor.White;
        }
        return null;
    }
    public long getWhiteTime(){
        return gameTimer.getTimeWhiteMillisecond();
    }
    public long getBlackTime(){
        return gameTimer.getTimeBlackMillisecond();
    }
    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public Set<IUpdateField> getFieldUpdaters() {
        return fieldUpdaters;
    }

    public Set<INotifyState> getResultsNotifiers() {
        return resultsNotifiers;
    }
}
