package org.example.account;

import org.example.serverConnection.CommonHttpBuilder;
import org.example.serverConnection.responses.AccountDataResponse;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * class which holds all the information about the account
 */
public class Account {
    /**
     * Token which is used to authorize the request to the server
     */
    private String JWTToken;
    private String userName;
    private int maxElo;
    private int currElo;
    private int gamePlayed;

    public Account(String JWTToken, AccountDataResponse accountDataResponse) {
        this(JWTToken, accountDataResponse.getUserName(), accountDataResponse.getMaxElo(),
                accountDataResponse.getCurrElo(), accountDataResponse.getGamePlayed());
    }

    public Account(String JWTToken, String userName, int maxElo, int currElo, int gamePlayed) {
        this.JWTToken = JWTToken;
        this.userName = userName;
        this.maxElo = maxElo;
        this.currElo = currElo;
        this.gamePlayed = gamePlayed;
    }

    /**
     * Takes all the account data from the server using the same JWTToken
     */
    public void updateAccountData() throws URISyntaxException, IOException, InterruptedException {
        AccountDataResponse accountDataResponse = CommonHttpBuilder.fetchAccountData(getJWTToken());
        this.userName = accountDataResponse.getUserName();
        this.maxElo=accountDataResponse.getMaxElo();
        this.currElo=accountDataResponse.getCurrElo();
        this.gamePlayed=accountDataResponse.getGamePlayed();
    }

    public String getJWTToken() {
        return JWTToken;
    }

    public String getUserName() {
        return userName;
    }

    public int getMaxElo() {
        return maxElo;
    }

    public int getCurrElo() {
        return currElo;
    }

    public int getGamePlayed() {
        return gamePlayed;
    }

    public void setJWTToken(String JWTToken) {
        this.JWTToken = JWTToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMaxElo(int maxElo) {
        this.maxElo = maxElo;
    }

    public void setCurrElo(int currElo) {
        this.currElo = currElo;
    }

    public void setGamePlayed(int gamePlayed) {
        this.gamePlayed = gamePlayed;
    }
}
