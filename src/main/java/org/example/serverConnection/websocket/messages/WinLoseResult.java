package org.example.serverConnection.websocket.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import logic.chessPieces.PieceColor;

/**
 * Message, which signalizes that a player won
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
public class WinLoseResult implements Jsonable {

    private String wonColor;
    private String reason;

    public WinLoseResult() {
    }

    public WinLoseResult(String wonColor, String reason) {
        this.wonColor = wonColor;
        this.reason = reason;
    }

    @Override
    public String toJsonString() {
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public String getWonColor() {
        return wonColor;
    }

    public String getReason() {
        return reason;
    }

    public void setWonColor(String wonColor) {
        this.wonColor = wonColor;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
