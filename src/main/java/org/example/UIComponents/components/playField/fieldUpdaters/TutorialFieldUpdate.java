package org.example.UIComponents.components.playField.fieldUpdaters;

import gameControl.gameController.TutorialGameController;
import logic.Move;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.VisualGameField;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;
import org.example.UIComponents.components.playField.symbolLayer.Arrow;

/**
 * field updater, which updates pieces and show helping arrow for tutorial
 */
public class TutorialFieldUpdate extends BasicFieldUpdate {
    public TutorialFieldUpdate(VisualGameField visualGameField) {
        super(visualGameField);
    }

    @Override
    public void doUpdate() {
        super.doUpdate();
        if (!(visualGameField.getGameController() instanceof TutorialGameController tutorialGameController))
            throw new RuntimeException("gameController isn't a TutorialGameController");
        MainPlayField mainPlayField = visualGameField.getMainPlayField();
        mainPlayField.getSquareLayer().reverseHighLighting();
        Move prevMove = tutorialGameController.getPrevMove();
        if (prevMove != null) {
            SquareIndexes start = mainPlayField.getSquaresOnScreenFromSquaresOnBoard(prevMove.getXStart(), prevMove.getYStart());
            SquareIndexes end = mainPlayField.getSquaresOnScreenFromSquaresOnBoard(prevMove.getXEnd(), prevMove.getYEnd());
            mainPlayField.getSymbolLayer().deleteArrow(new Arrow(start.getY(), start.getX(),
                    end.getY(), end.getX()));
        }
        Move move = tutorialGameController.getNextExpectedMove();
        if (move != null) {

            mainPlayField.getSquareLayer().highLightAllMoveOfAPiece(move.getXStart(), move.getYStart());
            SquareIndexes start = mainPlayField.getSquaresOnScreenFromSquaresOnBoard(move.getXStart(), move.getYStart());
            SquareIndexes end = mainPlayField.getSquaresOnScreenFromSquaresOnBoard(move.getXEnd(), move.getYEnd());
            mainPlayField.getSymbolLayer().addNewArrowToDisplay(
                    new Arrow(start.getY(), start.getX(),
                            end.getY(), end.getX()));
        }
        visualGameField.getMainPlayField().revalidate();
        visualGameField.getMainPlayField().repaint();

    }
}
