package org.example.utils.visual;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public enum IconsEnum {
    WHITE_ROOK("src/main/resources/figuren/rookWhite.png"),
    WHITE_QUEEN("src/main/resources/figuren/queenWhite.png"),
    WHITE_PAWN("src/main/resources/figuren/pawnWhite.png"),
    WHITE_KNIGHT("src/main/resources/figuren/knightWhite.png"),
    WHITE_BISHOP("src/main/resources/figuren/bishopWhite.png"),
    WHITE_KING("src/main/resources/figuren/kingWhite.png"),
    BLACK_ROOK("src/main/resources/figuren/rookBlack.png"),
    BLACK_QUEEN("src/main/resources/figuren/queenBlack.png"),
    BLACK_PAWN("src/main/resources/figuren/pawnBlack.png"),
    BLACK_KNIGHT("src/main/resources/figuren/knightBlack.png"),
    BLACK_BISHOP("src/main/resources/figuren/bishopBlack.png"),
    BLACK_KING("src/main/resources/figuren/kingBlack.png"),
    BACK_ICON("src/main/resources/icons/mainMenu/back.png"),
    CREDITS_ICON("src/main/resources/icons/mainMenu/Credits.png"),
    ELO_ICON("src/main/resources/icons/mainMenu/ELO.png"),
    PLAY_ICON("src/main/resources/icons/mainMenu/play.png"),
    SETTING_ICON("src/main/resources/icons/mainMenu/settings.png"),
    SWITCH_USER_ICON("src/main/resources/icons/mainMenu/switch_user.png"),
    USER_MANUAL_ICON("src/main/resources/icons/mainMenu/user_manual.png"),
    SWITCH_ICON("src/main/resources/icons/fidgets/SwitchIcon.png"),;
    private final File imageFile;
    IconsEnum(String imagePath) {
        this.imageFile = new File(imagePath);
        if(!imageFile.exists()) throw new RuntimeException("Image file not found: " + imagePath);
    }

    public JLabel getPieceLabel(int size) {
        JLabel pieceLabel = new JLabel();
        pieceLabel.setIcon(getImageIcon(size-10));
        return pieceLabel;
    }
    public ImageIcon getImageIcon(int size){
        Image image = new ImageIcon(imageFile.getAbsolutePath()).getImage();
        Image newImg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
