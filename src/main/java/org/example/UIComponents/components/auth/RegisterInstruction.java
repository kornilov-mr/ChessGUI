package org.example.UIComponents.components.auth;

import org.example.UIComponents.dialogs.SimpleDialogFactory;
import org.example.serverConnection.CommonHttpBuilder;
import org.example.serverConnection.UserWithThisUsernameAlreadyExist;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Registration implementation
 */
public class RegisterInstruction implements AuthInstruction {
    @Override
    public void doAuthInstruction(String username, String password) {
        boolean isOkay;
        try {
            isOkay = true;
            CommonHttpBuilder.register(username, password);
        }catch (UserWithThisUsernameAlreadyExist e){
            SimpleDialogFactory.showErrorDialog("user with this username already exists");
            isOkay =false;
        } catch (IOException e) {
            SimpleDialogFactory.showErrorDialog("failed to connect to the server");
            isOkay =false;
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if(isOkay){
            SimpleDialogFactory.showInfoDialog("registered");
        }
    }
}
