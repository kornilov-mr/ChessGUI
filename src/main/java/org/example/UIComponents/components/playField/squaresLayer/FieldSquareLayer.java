package org.example.UIComponents.components.playField.squaresLayer;

import logic.Board;
import logic.Move;
import logic.chessPieces.Piece;
import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;
import org.example.utils.visual.ColorsEnum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Layer of MainPlayField, which contains squares and highlighting
 */
public class FieldSquareLayer extends JPanel {

    private final MainPlayField mainPlayField;
    private final PlaySquare[][] playingSquares = new PlaySquare[8][8];
    public FieldSquareLayer(int squareSize, MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;

        GridLayout layout = new GridLayout(8, 8);
        layout.setHgap(0);
        layout.setVgap(0);
        setBounds(0, 0, squareSize*8, squareSize*8);
        setLayout(layout);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PlaySquare playSquare = new PlaySquare((i + j) % 2 == 0 ? ColorsEnum.PLAY_FIELD_COLOR1.getColor() : ColorsEnum.PLAY_FIELD_COLOR2.getColor(),
                        squareSize);
                playingSquares[i][j] = playSquare;
                add(playSquare);
            }
        }
        addMouseListener(new SquareMouseListener(mainPlayField));
        setOpaque(false);
    }
    public PlaySquare getSquare(int x, int y) {
        return playingSquares[y][x];
    }
    public void reverseHighLighting(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                playingSquares[i][j].setBackToNormalColor();
            }
        }
    }
    public void highLightAllMoveOfAPiece(int x, int y){
        Board board = mainPlayField.getGameController().getBoardController().getBoard();
        Piece piece = board.getPieceOnTile(x, y);
        if(piece==null) return;
        ArrayList<Move> moves = piece.getAllMovesInBounds(board);
        for (Move move : moves) {
            if(mainPlayField.getColorOrientation().equals(PieceColor.Black)) {
                playingSquares[move.getXEnd()][move.getYEnd()].changeHighLight();
            }else{
                playingSquares[7-move.getXEnd()][move.getYEnd()].changeHighLight();
            }
        }
    }
    public void changeOrientationOfHighlightedSquares(){

        Color[][] colors = new Color[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                colors[i][j]= playingSquares[i][j].getColor();
            }
        }
        reverseHighLighting();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                playingSquares[i][j].setColor(colors[i][7-j]);
            }
        }
        repaint();
    }
}
