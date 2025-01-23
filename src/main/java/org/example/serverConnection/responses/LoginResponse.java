package org.example.serverConnection.responses;

/**
 * Dto with JWTToken and expiration date
 */
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse() {
    }

    public LoginResponse(String token, long expiresIn) {
        this.expiresIn = expiresIn;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
