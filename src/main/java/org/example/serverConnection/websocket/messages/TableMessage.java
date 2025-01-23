package org.example.serverConnection.websocket.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Message with information about the current position
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
public class TableMessage {
    private String pieces;

    public TableMessage() {
    }

    public TableMessage(String pieces) {
        this.pieces = pieces;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
