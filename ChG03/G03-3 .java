package ChG03;

import javax.swing.JOptionPane;

class G03 {

    public static void main(String[] args) {

        String name = JOptionPane.showInputDialog(
            null,
            "輸入："
        );

        String ageString = JOptionPane.showInputDialog(
            null,
            "輸入："
        );

        int age = Integer.parseInt(ageString);

        JOptionPane.showMessageDialog(
            null,
            "1：" + name + "\n" +
            "2：" + age,
            "3",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}