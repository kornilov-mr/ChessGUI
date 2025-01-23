package org.example.serverConnection;

public class UserWithThisUsernameAlreadyExist extends RuntimeException {
    public UserWithThisUsernameAlreadyExist() {
        super("user with this username already exists");
    }
}
