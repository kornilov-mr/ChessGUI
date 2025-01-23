package org.example.UIComponents.components.playField.piecesLayer;

import logic.Board;
import logic.Move;
import logic.MoveIsNotValidException;
import logic.chessPieces.Piece;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;
import org.example.utils.sound.SoundEnum;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MouseListener, which tracks MouseMovement,
 * sending moves to gameController
 */
public class PiecesMouseListener implements MouseListener {
    private final MainPlayField mainPlayField;
    private final int squareSize;
    private SquareIndexes lastSquarePressedPositionOnBoard;
    private SquareIndexes lastSquarePressedPositionOnScreen;

    private boolean isWorking =true;
    public PiecesMouseListener(int squareSize, MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
        this.squareSize = squareSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!isWorking) return;
        if (!mainPlayField.areCoordsInBounds(e.getX(),e.getY())) return;
        SquareIndexes currentSquareOnBoard = mainPlayField.getSquareIndexesFromMousePositionOnBoard(e.getX(),e.getY());
        SquareIndexes currentSquareOnScreen = mainPlayField.getSquareIndexesFromMousePositionOnScreen(e.getX(),e.getY());

        if(mainPlayField.getGameController()==null) return;
        Board board =  mainPlayField.getGameController().getBoardController().getBoard();
        Piece currPiece = board.getPieceOnTile(currentSquareOnBoard.getX(),currentSquareOnBoard.getY());
        if(currPiece==null) return;

        if(e.getButton() == MouseEvent.BUTTON1) {
            PiecePanel piecePanel = mainPlayField.getPiecesLayer().getPiecePanel(currentSquareOnScreen.getY(),
                    currentSquareOnScreen.getX());
            piecePanel.hideLabel();


            mainPlayField.getDragLayer().getMML().setPieceLabel(currPiece.getImageEnum().getPieceLabel((int) (squareSize*1.4)));
            lastSquarePressedPositionOnBoard=currentSquareOnBoard;
            lastSquarePressedPositionOnScreen=currentSquareOnScreen;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!isWorking) return;
        if (lastSquarePressedPositionOnBoard == null) return;
        if (lastSquarePressedPositionOnScreen == null) return;
        if (!mainPlayField.areCoordsInBounds(e.getX(),e.getY())) return;
        if (mainPlayField.getGameController() == null) return;
        Board board = mainPlayField.getGameController().getBoardController().getBoard();

        mainPlayField.getDragLayer().getMML().deletePieceLabel();
        SquareIndexes currSquareOnBoard = mainPlayField.getSquareIndexesFromMousePositionOnBoard(e.getX(),e.getY());
        Move move = new Move(lastSquarePressedPositionOnBoard.getX(), lastSquarePressedPositionOnBoard.getY(),
                currSquareOnBoard.getX(), currSquareOnBoard.getY());
        PiecePanel piecePanel = mainPlayField.getPiecesLayer().getPiecePanel(lastSquarePressedPositionOnScreen.getY(),
                lastSquarePressedPositionOnScreen.getX());
        if(board.shouldMoveBePromotionalMove(move)) {
            mainPlayField.getPopupLayer().showPromotionSelectorOnMove(move);
        }else{
            try {
                mainPlayField.getGameController().makeAMove(move);
            } catch (MoveIsNotValidException ex) {
                piecePanel.showLabel();
                lastSquarePressedPositionOnScreen=null;
                lastSquarePressedPositionOnBoard=null;
                return;
            }
        }
        SoundEnum.PIECE_PLAYED_SOUND.getClip().loop(1);
        lastSquarePressedPositionOnScreen=null;
        lastSquarePressedPositionOnBoard=null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void startListener(){
        isWorking=true;
    }
    public void stopListener(){
        isWorking=false;
    }

}
