package gameControl.gameController;

import gameControl.colorChooser.ColorChooser;
import gameControl.timer.GameTimer;
import logic.BoardController;
import logic.chessPieces.PieceColor;

/**
 * Implementation for basic game board, just uses basic logic from super class
 */
public class VisualGameController extends AbstractGameController{
    protected VisualGameController(ColorChooser colorChooser, BoardController boardController, GameTimer gameTimer) {
        super(PieceColor.White, boardController, gameTimer);
    }

    @Override
    protected void start() {

    }
}
