package ChG02;

import java.awt.Color;
import javax.swing.JFrame;

public class G02_2 {
    public static void main(String[] args) {

        JFrame jf = new JFrame("G02_2");

        jf.setBounds(300, 100, 500, 300);

        jf.getContentPane().setBackground(Color.RED);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}