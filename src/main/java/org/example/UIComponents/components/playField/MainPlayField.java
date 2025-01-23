package org.example.UIComponents.components.playField;

import gameControl.gameController.AbstractGameController;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.dragLayer.FieldDragLayer;
import org.example.UIComponents.components.playField.piecesLayer.FieldPieceLayer;
import org.example.UIComponents.components.playField.popupLayer.FieldPopupLayer;
import org.example.UIComponents.components.playField.squaresLayer.FieldSquareLayer;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;
import org.example.UIComponents.components.playField.symbolLayer.FieldSymbolLayer;

import javax.swing.*;

/**
 * Class, which represents main playing field
 * is JLayerPane, containing 5 layers:
 * SquareLayer - black and white squares, highlighting
 * PiecesLayer - displaying all pieces
 * SymbolLayer - for indexing files and rows and drawing arrows
 * DragLayer - layer for pieces, which is being dragged
 * PopupLayer - layer for start game popup, game result, and promotional selector
 */
public class MainPlayField extends JLayeredPane  {

    private AbstractGameController gameController;
    private PieceColor colorOrientation;

    private final FieldPieceLayer piecesLayer;
    private final FieldSquareLayer squareLayer;
    private final FieldSymbolLayer symbolLayer;
    private final FieldDragLayer dragLayer;
    private final FieldPopupLayer popupLayer;

    private final int squareSize;
    private final VisualGameField visualGameField;

    public MainPlayField(PieceColor colorOrientation, int squareSize, VisualGameField visualGameField) {
        this.colorOrientation = colorOrientation;
        this.squareSize = squareSize;
        this.visualGameField = visualGameField;

        setLayout(new OverlayLayout(this));
        setBounds(0, 0, squareSize*80, squareSize*80);

        this.piecesLayer = new FieldPieceLayer(squareSize,this);
        this.squareLayer = new FieldSquareLayer(squareSize,this);
        this.symbolLayer = new FieldSymbolLayer(squareSize,this);
        this.dragLayer = new FieldDragLayer(this);
        this.popupLayer = new FieldPopupLayer(squareSize,this);

        add(squareLayer, JLayeredPane.DEFAULT_LAYER);
        add(symbolLayer, JLayeredPane.PALETTE_LAYER);
        add(piecesLayer, JLayeredPane.MODAL_LAYER);
        add(dragLayer, JLayeredPane.DRAG_LAYER);
        add(popupLayer, JLayeredPane.POPUP_LAYER);
    }

    /**
     * on setting new gameController
     * updates all Ui aspects of mainPlayField
     */
    public void setVisualGameController(AbstractGameController gameController) {
        this.gameController = gameController;
        SwingUtilities.invokeLater(() -> {
            getSymbolLayer().deleteAllArrows();
            getSquareLayer().reverseHighLighting();
            getPiecesLayer().displayPieces();
            getPopupLayer().hidePromotionSelector();
            getPopupLayer().hideEndGamePopup();
        });
    }
    public void freezeTheGame(){
        visualGameField.freezeTheGame();
    }

    public boolean areCoordsInBounds(int mouseX,int mouseY){
        if (mouseX >= 640 || mouseX < 0) return false;
        return mouseY < 640 && mouseY >= 0;
    }
    public SquareIndexes getSquareIndexesFromMousePositionOnBoard(int mouseX, int mouseY){
        if(getColorOrientation().equals(PieceColor.Black)) {
            return new SquareIndexes(mouseY / 80, mouseX / 80);
        }else{
            return new SquareIndexes(7-(mouseY / 80), mouseX / 80);
        }
    }
    public SquareIndexes getSquaresOnScreenFromSquaresOnBoard(int x, int y){
        if(getColorOrientation().equals(PieceColor.Black)) {
            return new SquareIndexes(x, y);
        }else{
            return new SquareIndexes(7-x, y);
        }
    }

    public SquareIndexes getSquareIndexesFromMousePositionOnScreen(int mouseX, int mouseY){
        return new SquareIndexes(mouseX / 80, mouseY / 80);
    }
    public PieceColor getColorOrientation() {
        return colorOrientation;
    }

    public AbstractGameController getGameController() {
        return gameController;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public FieldSquareLayer getSquareLayer() {
        return squareLayer;
    }

    public FieldPieceLayer getPiecesLayer() {
        return piecesLayer;
    }


    public FieldSymbolLayer getSymbolLayer() {
        return symbolLayer;
    }

    public FieldDragLayer getDragLayer() {
        return dragLayer;
    }

    public FieldPopupLayer getPopupLayer() {
        return popupLayer;
    }

    public void setColorOrientation(PieceColor colorOrientation) {
        this.colorOrientation = colorOrientation;
    }
}
