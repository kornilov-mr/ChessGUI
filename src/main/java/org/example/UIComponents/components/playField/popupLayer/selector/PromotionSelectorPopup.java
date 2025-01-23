package org.example.UIComponents.components.playField.popupLayer.selector;

import logic.Move;
import logic.chessPieces.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * PieceSelectorPopup, but only with pieces,
 * pawn can be promoted to
 */
public class PromotionSelectorPopup extends PieceSelectorPopup {
    public PromotionSelectorPopup(int squareSize, PieceColor pieceColor, Move move) {
        super(squareSize,
                new ArrayList<>(Arrays.asList(
                        pieceColor.equals(PieceColor.Black) ?
                                new Queen(0,0,PieceColor.Black):
                                new Queen(0,0,PieceColor.White),
                        pieceColor.equals(PieceColor.Black) ?
                                new Rook(0,0,PieceColor.Black):
                                new Rook(0,0,PieceColor.White),
                        pieceColor.equals(PieceColor.Black) ?
                                new Bishop(0,0,PieceColor.Black):
                                new Bishop(0,0,PieceColor.White),
                        pieceColor.equals(PieceColor.Black) ?
                                new Knight(0,0,PieceColor.Black):
                                new Knight(0,0,PieceColor.White))
                ),move);

    }

}
