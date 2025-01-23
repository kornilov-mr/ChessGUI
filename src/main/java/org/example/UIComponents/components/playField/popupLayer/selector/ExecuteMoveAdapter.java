package org.example.UIComponents.components.playField.popupLayer.selector;

import logic.Move;
import logic.PromotionMove;
import logic.chessPieces.Piece;
import org.example.UIComponents.components.playField.MainPlayField;

/**
 * Class, which on Selection will create Promotion Move and send it to gameController
 */
public class ExecuteMoveAdapter implements ISelectPieces{
   private final MainPlayField mainPlayField;

    public ExecuteMoveAdapter(MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
    }

    @Override
    public void onPieceSelected(Piece piece, Move move) {
        PromotionMove promotionMove = new PromotionMove(move,piece);
        mainPlayField.getGameController().makeAMove(promotionMove);
        mainPlayField.getPiecesLayer().displayPieces();
        mainPlayField.getPopupLayer().hidePromotionSelector();
    }
}
