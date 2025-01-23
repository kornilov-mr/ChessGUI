package org.example.UIComponents.dialogs;


import javax.swing.*;


public class SimpleDialogFactory {
    public static void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(
                null,                       // Parent component (null for default placement)
                errorMessage,               // Error message to display
                "Error",                    // Title of the dialog
                JOptionPane.ERROR_MESSAGE   // Type of message
        );
    }
    public static void showInfoDialog(String infoMessage) {
        JOptionPane.showMessageDialog(
                null,                       // Parent component (null for default placement)
                infoMessage,               // Error message to display
                "Info",                    // Title of the dialog
                JOptionPane.INFORMATION_MESSAGE   // Type of message
        );
    }
}
