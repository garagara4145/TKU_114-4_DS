package ChG03;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/*
    public static int showConfirmDialog(
        Component owner,
        Object message,
        String title,
        int optionType,
        int messageType    // 內建icon類型
    ); // standard form

    public static int showConfirmDialog(
        Component owner,
        Object message,
        String title,
        int optionType,
        int messageType,
        Icon usricon
    )

    public static int showConfirmDialog(
        Component owner,
        Object message
    ); // convenience form
*/

class TestConfirmDialog {

    static final String msgTypeValue =
        "PLAIN_MESSAGE = " + JOptionPane.PLAIN_MESSAGE + "\n" +
        "ERROR_MESSAGE = " + JOptionPane.ERROR_MESSAGE + "\n" +
        "INFORMATION_MESSAGE = " + JOptionPane.INFORMATION_MESSAGE + "\n" +
        "WARNING_MESSAGE = " + JOptionPane.WARNING_MESSAGE + "\n" +
        "QUESTION_MESSAGE = " + JOptionPane.QUESTION_MESSAGE;

    static void testConstants() { // print values of message types
        System.out.println(msgTypeValue);
    }

    // PLAIN_MESSAGE = -1
    // ERROR_MESSAGE = 0
    // INFORMATION_MESSAGE = 1
    // WARNING_MESSAGE = 2
    // QUESTION_MESSAGE = 3

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

    static void testConvenienceMethod() {
        // convenience form, 即只傳 owner 與 message 的函數

        JOptionPane.showConfirmDialog(
            null,                       // owner window
            msgTypeValue                // message
        );
    }

    public static void main(String[] args) {

        testConstants();

        test1();

        testConvenienceMethod();
    }
}