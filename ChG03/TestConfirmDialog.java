package ChG03;

import javax.swing.JOptionPane;

/*
    public static int showConfirmDialog(
        Component owner,
        Object message,
        String title,
        int optionType,
        int messageType
    );

    optionType:
        DEFAULT_OPTION = -1
        YES_NO_OPTION = 0
        YES_NO_CANCEL_OPTION = 1
        OK_CANCEL_OPTION = 2

    messageType:
        PLAIN_MESSAGE = -1
        ERROR_MESSAGE = 0
        INFORMATION_MESSAGE = 1
        WARNING_MESSAGE = 2
        QUESTION_MESSAGE = 3
*/

public class TestConfirmDialog {

    static void test1() {

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            "Hello, how are you. ",     // message
            "PLAIN_MESSAGE",            // title
            JOptionPane.YES_NO_OPTION,  // optionType
            JOptionPane.PLAIN_MESSAGE   // messageType
        );

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            "This is a programming course", // message
            "INFORMATION_MESSAGE",      // title
            JOptionPane.YES_NO_OPTION,  // optionType
            JOptionPane.INFORMATION_MESSAGE // messageType
        );

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            "What?",                    // message
            "QUESTION_MESSAGE",         // title
            JOptionPane.YES_NO_OPTION,  // optionType
            JOptionPane.QUESTION_MESSAGE // messageType
        );

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            "Look out",                 // message
            "WARNING_MESSAGE",          // title
            JOptionPane.YES_NO_OPTION,  // optionType
            JOptionPane.WARNING_MESSAGE // messageType
        );

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            "Something wrong",          // message
            "ERROR_MESSAGE",            // title
            JOptionPane.YES_NO_OPTION,  // optionType
            JOptionPane.ERROR_MESSAGE   // messageType
        );
    }

    static void test2() {

        JOptionPane.showConfirmDialog(
            null,
            "Do you want to continue?",
            "Question",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        JOptionPane.showConfirmDialog(
            null,
            "Save this file?",
            "Save",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );

        JOptionPane.showConfirmDialog(
            null,
            "Do you want to cancel?",
            "Cancel",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
    }

    public static void main(String[] args) {

        test1();

        test2();
    }
}