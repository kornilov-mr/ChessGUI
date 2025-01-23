package org.example.serverConnection.responses;

/**
 * DTO with information about the game
 */
public class TableResponse {
    private String piecePosition;
    private String ownerColor;
    private Boolean isFull;
    private String tableName;
    private Long tableId;
    private String timerTypeName;
    private boolean isGameOnPoints;

    public TableResponse() {
    }


    public String getPiecePosition() {
        return piecePosition;
    }

    public void setPiecePosition(String piecePosition) {
        this.piecePosition = piecePosition;
    }

    public String getOwnerColor() {
        return ownerColor;
    }

    public void setOwnerColor(String ownerColor) {
        this.ownerColor = ownerColor;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTimerTypeName() {
        return timerTypeName;
    }

    public void setTimerTypeName(String timerTypeName) {
        this.timerTypeName = timerTypeName;
    }

    public boolean isGameOnPoints() {
        return isGameOnPoints;
    }

    public void setGameOnPoints(boolean gameOnPoints) {
        isGameOnPoints = gameOnPoints;
    }

}
