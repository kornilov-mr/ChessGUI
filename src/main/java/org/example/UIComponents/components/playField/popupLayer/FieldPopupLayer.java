package org.example.UIComponents.components.playField.popupLayer;

import logic.Move;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.popupLayer.selector.ExecuteMoveAdapter;
import org.example.UIComponents.components.playField.popupLayer.selector.PromotionSelectorPopup;
import org.example.UIComponents.components.playField.squaresLayer.SquareIndexes;
import org.example.account.AppContext;

import javax.swing.*;

/**
 * Layer of MainPlayField which contains all popups
 */
public class FieldPopupLayer extends JPanel {
    private final MainPlayField mainPlayField;
    private final int squareSize;
    private PromotionSelectorPopup promotionSelectorPopup;
    private EndGamePopup endGamePopup;
    private OnlineGameStartPopup onlineGameStartPopup;
    private final ExecuteMoveAdapter executeMoveAdapter;

    public FieldPopupLayer(int squareSize,MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
        this.squareSize = squareSize;
        this.executeMoveAdapter = new ExecuteMoveAdapter(mainPlayField);
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBounds(0, 0, squareSize*8, squareSize*8);
        add(panel);
        setLayout(null);
        setOpaque(false);
        addMouseListener(new SelectorClickPropagation(mainPlayField));

    }

    public void showPromotionSelectorOnMove(Move move) {
        PieceColor pieceColor = mainPlayField.getGameController().getBoardController()
                .getBoard().getPieceColorOnMoveStart(move);
        if(pieceColor == null) throw new RuntimeException("Couldn't find the piece on the Move start");
        this.promotionSelectorPopup = new PromotionSelectorPopup(squareSize,pieceColor,move);
        promotionSelectorPopup.subscribeTo(executeMoveAdapter);
        add(promotionSelectorPopup);
        SquareIndexes square = mainPlayField.getSquaresOnScreenFromSquaresOnBoard(move.getXEnd(), move.getYEnd());

        promotionSelectorPopup.setBounds(squareSize*square.getY()+ Math.min(((7-square.getY())-promotionSelectorPopup.getWidth()),0),
                squareSize*square.getX(),
                promotionSelectorPopup.getWidth(),
                promotionSelectorPopup.getHeight());
        promotionSelectorPopup.setVisible(true);
        revalidate();
        repaint();
    }
    public void hidePromotionSelector() {
        if(promotionSelectorPopup == null) return;
        remove(promotionSelectorPopup);
        revalidate();
        repaint();
    }
    public PromotionSelectorPopup getPromotionSelectorPopup() {
        return promotionSelectorPopup;
    }
    public void hideEndGamePopup() {
        if(endGamePopup == null) return;
        remove(endGamePopup);
        revalidate();
        repaint();
    }
    public void showEndgamePopup(String description) {
        if(endGamePopup != null)  remove(endGamePopup);
        EndGamePopup newPopup = new EndGamePopup(description);
        newPopup.setBounds(squareSize*4-newPopup.getWidth()/2,
                squareSize*4-newPopup.getHeight()/2,
                600,100);
        newPopup.setLocation(squareSize*4-newPopup.getWidth()/2,squareSize*4-newPopup.getHeight()/2);
        this.endGamePopup=newPopup;
        add(endGamePopup);
        revalidate();
        repaint();
    }
    public void hideNewOnlineGameStartPopup() {
        if(onlineGameStartPopup == null) return;
        remove(onlineGameStartPopup);
        revalidate();
        repaint();
    }
    public void showNewOnlineGamePopup(String enemyUsername, int enemyElo) {
        if(onlineGameStartPopup != null)  remove(onlineGameStartPopup);
        OnlineGameStartPopup newPopup = new OnlineGameStartPopup(AppContext.getAccount(),
                enemyUsername, enemyElo);
        newPopup.setBounds(squareSize*4-newPopup.getWidth()/2,
                squareSize*4-newPopup.getHeight()/2,
                600,100);
        newPopup.setLocation(squareSize*4-newPopup.getWidth()/2,squareSize*4-newPopup.getHeight()/2);
        this.onlineGameStartPopup=newPopup;
        add(onlineGameStartPopup);
        revalidate();
        repaint();
        Timer timerToHidePopup = new Timer(3000, _ -> hideNewOnlineGameStartPopup());
        timerToHidePopup.setRepeats(false);
        timerToHidePopup.start();
    }
}
