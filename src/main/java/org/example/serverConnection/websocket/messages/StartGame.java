package org.example.serverConnection.websocket.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Messages, which signalizes that the online game start
 * with information about the players
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
public class StartGame implements Jsonable {
    private String firstUserName;
    private String secondUserName;
    private int firstUserElo;
    private int secondUserElo;

    public StartGame() {
    }

    public StartGame(String firstUserName, String secondUserName, int firstUserElo, int secondUserElo) {
        this.firstUserName = firstUserName;
        this.secondUserName = secondUserName;
        this.firstUserElo = firstUserElo;
        this.secondUserElo = secondUserElo;
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

    public String getFirstUserName() {
        return firstUserName;
    }

    public String getSecondUserName() {
        return secondUserName;
    }

    public int getFirstUserElo() {
        return firstUserElo;
    }

    public int getSecondUserElo() {
        return secondUserElo;
    }

    public void setFirstUserName(String firstUserName) {
        this.firstUserName = firstUserName;
    }

    public void setSecondUserName(String secondUserName) {
        this.secondUserName = secondUserName;
    }

    public void setFirstUserElo(int firstUserElo) {
        this.firstUserElo = firstUserElo;
    }

    public void setSecondUserElo(int secondUserElo) {
        this.secondUserElo = secondUserElo;
    }
}
