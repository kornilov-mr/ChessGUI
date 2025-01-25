package org.example.serverConnection.responses;


public class EloResponse {
    private String username;
    private int currElo;

    public EloResponse() {
    }

    public EloResponse(String username, int currElo) {
        this.username = username;
        this.currElo = currElo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrElo() {
        return currElo;
    }

    public void setCurrElo(int currElo) {
        this.currElo = currElo;
    }
}
