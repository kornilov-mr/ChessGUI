package org.example.UIComponents.components.playField.popupLayer.selector;

import logic.Move;
import logic.chessPieces.Piece;

/**
 * Interface for PieceSelection
 */
public interface ISelectPieces {
    void onPieceSelected(Piece piece, Move move);
}
