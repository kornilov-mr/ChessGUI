package org.example.account;

/**
 * Class which contains static objects
 */
public class AppContext {
    private static Account account;


    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        AppContext.account = account;
    }
}
