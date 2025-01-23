package org.example.serverConnection;

public class UserIsNotLoggedIn extends RuntimeException {
    public UserIsNotLoggedIn(String message) {
        super(message);
    }
}
