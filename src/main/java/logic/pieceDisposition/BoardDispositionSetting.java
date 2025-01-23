package logic.pieceDisposition;

import logic.chessPieces.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Class, which takes file or string and parses it, creating piece[][] array
 * and other important information about the board
 */
public class BoardDispositionSetting {


    private final Piece[][] pieces;

    private int height;
    private int width;
    private Piece whiteKing;
    private Piece blackKing;
    public BoardDispositionSetting(File settingFile) {
        this.pieces = getPiecesFromSettingFile(settingFile);
    }
    public BoardDispositionSetting(String piecePosition) {
        this.pieces = getPiecesFromSettingString(piecePosition);
    }

    /**
     * Parses board from a string
     * @param settingString string with positions' info
     */
    private Piece[][] getPiecesFromSettingString(String settingString){
        String[] settingByLines = settingString.split("\n");
        String configLine = settingByLines[0];
        String[] size = configLine.split(" ");
        this.width = Integer.parseInt(size[0]);
        this.height = Integer.parseInt(size[1]);
        Piece[][] pieces = new Piece[width][height];
        for(int i=0;i<height;i++){
            String line = settingByLines[i+1];
            String[] piecesInLine = line.trim().split(" ");
            for (int j = 0; j < piecesInLine.length; j++) {
                Piece piece = ChessPieceFactory.createPieceFromString(i,j,piecesInLine[j]);
                if(piece instanceof King) {
                    if(piece.getPieceColor().equals(PieceColor.Black)){
                        this.blackKing = piece;
                    }else{
                        this.whiteKing = piece;
                    }
                }
                pieces[i][j]=piece;
            }
        }
        return pieces;
    }

    /**
     * takes file, reads all data and the fires getPiecesFromSettingString
     * @param settingFile file with positions' info
     */
    private Piece[][] getPiecesFromSettingFile(File settingFile) {
        List<String> data;
        try {
            data =  Files.readAllLines(Path.of(settingFile.toURI()));
        } catch (IOException e) {
            throw new RuntimeException("failure while reading setting file",e);
        }
        return getPiecesFromSettingString(String.join("\n", data));
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Piece getWhiteKing() {
        return whiteKing;
    }

    public Piece getBlackKing() {
        return blackKing;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
