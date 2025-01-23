package org.example.serverConnection.responses;

/**
 * Dto with information about the account
 */
public class AccountDataResponse {
    private String UserName;
    private int MaxElo;
    private int currElo;
    private int gamePlayed;

    public AccountDataResponse() {
    }

    public AccountDataResponse(String userName, int maxElo, int currElo, int gamePlayed) {
        UserName = userName;
        MaxElo = maxElo;
        this.currElo = currElo;
        this.gamePlayed = gamePlayed;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getMaxElo() {
        return MaxElo;
    }

    public void setMaxElo(int maxElo) {
        MaxElo = maxElo;
    }

    public int getCurrElo() {
        return currElo;
    }

    public void setCurrElo(int currElo) {
        this.currElo = currElo;
    }

    public int getGamePlayed() {
        return gamePlayed;
    }

    public void setGamePlayed(int gamePlayed) {
        this.gamePlayed = gamePlayed;
    }
}
