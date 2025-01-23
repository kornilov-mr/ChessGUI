package org.example.UIComponents.components.auth;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.example.UIComponents.dialogs.SimpleDialogFactory;
import org.example.account.Account;
import org.example.account.AppContext;
import org.example.serverConnection.CommonHttpBuilder;
import org.example.serverConnection.responses.AccountDataResponse;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Login implementation
 */
public class LoginInstruction implements AuthInstruction {
    @Override
    public void doAuthInstruction(String username, String password) {
        String JWTToken;
        boolean isOkay;
        try {
            isOkay =true;
            JWTToken = CommonHttpBuilder.login(username, password);
            AccountDataResponse accountDataResponse = CommonHttpBuilder.fetchAccountData(JWTToken);
            Account account = new Account(JWTToken, accountDataResponse);
            AppContext.setAccount(account);
        } catch (MismatchedInputException e){
            SimpleDialogFactory.showErrorDialog("wrong username or password");
            isOkay =false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            SimpleDialogFactory.showErrorDialog("failed to connect to the server");
            isOkay =false;
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if(isOkay){
            SimpleDialogFactory.showInfoDialog("you are now logged in");
        }


    }
}
