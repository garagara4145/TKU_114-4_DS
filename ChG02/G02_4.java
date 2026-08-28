

package ChG02;

import java.awt.Color;
import javax.swing.JFrame;

public class G02_4 {
    public static void main(String[] args) {

        JFrame jf = new JFrame("G02_4 測試視窗");

       jf.setBounds(200, 100, 500, 300);

        jf.getContentPane().setBackground(Color.YELLOW);

        jf.setResizable(false);

        System.out.println("X = " + jf.getX());
        System.out.println("Y = " + jf.getY());
        System.out.println("W = " + jf.getWidth());
        System.out.println("H = " + jf.getHeight());

      
        jf.setVisible(true);
    }
}