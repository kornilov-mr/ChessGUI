package gameControl.gameController;

import gameControl.timer.GameTimer;
import logic.BoardController;
import logic.Move;
import logic.MoveIsNotValidException;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.observers.INotifyState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * this implementation is used for tutorials
 * it's purpose is to write helping arrows for the next moves
 * and not allowing the player to execute wrong move
 */
public class TutorialGameController extends AbstractGameController {
    private final ArrayList<Move> playersExpectedMoves;
    private final ArrayList<Move> opponentsMoves;
    private int moveCount = 0;

    protected TutorialGameController(PieceColor currentColorToMove, BoardController boardController, GameTimer gameTimer, ArrayList<Move> playersExpectedMoves, ArrayList<Move> opponentsMoves) {
        super(currentColorToMove, boardController, gameTimer);
        this.playersExpectedMoves = playersExpectedMoves;
        this.opponentsMoves = opponentsMoves;
    }

    @Override
    public void makeAMove(Move move) throws MoveIsNotValidException {
        if (moveCount >= playersExpectedMoves.size()) throw new MoveIsNotValidException();
        if (!playersExpectedMoves.get(moveCount).equals(move)) throw new MoveIsNotValidException();

        moveCount++;
        try {
            super.makeAMove(move);
        }catch (MoveIsNotValidException e){
            moveCount--;
        }
        if (moveCount != playersExpectedMoves.size()) super.makeAMove(opponentsMoves.get(moveCount-1));
        if (moveCount == playersExpectedMoves.size()) {
            for (INotifyState notifier : resultsNotifiers) {
                notifier.notifyEndOfPuzzle();
            }
        }

    }

    @Nullable
    public Move getNextExpectedMove() {
        if (moveCount >= playersExpectedMoves.size()) return null;
        return playersExpectedMoves.get(moveCount);
    }

    @Nullable
    public Move getPrevMove() {
        if (moveCount == 0 || moveCount > playersExpectedMoves.size()) return null;
        return playersExpectedMoves.get(moveCount - 1);
    }

    @Override
    protected void start() {

    }
}
