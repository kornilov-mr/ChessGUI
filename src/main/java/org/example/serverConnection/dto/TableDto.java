package org.example.serverConnection.dto;

/**
 * Dto with information about the game
 */
public class TableDto {
    private String gameName;
    private String colorChooser;
    private String gameTimerType;
    private Boolean isTheGameOnPoints;
    private String piecePosition;

    public TableDto(String gameName, String colorChooser, String gameTimerType, Boolean isTheGameOnPoints, String piecePosition) {
        this.gameName = gameName;
        this.colorChooser = colorChooser;
        this.gameTimerType = gameTimerType;
        this.isTheGameOnPoints = isTheGameOnPoints;
        this.piecePosition = piecePosition;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setColorChooser(String colorChooser) {
        this.colorChooser = colorChooser;
    }

    public void setGameTimerType(String gameTimerType) {
        this.gameTimerType = gameTimerType;
    }

    public void setTheGameOnPoints(Boolean theGameOnPoints) {
        isTheGameOnPoints = theGameOnPoints;
    }

    public void setPiecePosition(String piecePosition) {
        this.piecePosition = piecePosition;
    }

    public String getGameName() {
        return gameName;
    }

    public String getColorChooser() {
        return colorChooser;
    }


    public String getGameTimerType() {
        return gameTimerType;
    }

    public Boolean getTheGameOnPoints() {
        return isTheGameOnPoints;
    }

    public String getPiecePosition() {
        return piecePosition;
    }
}
