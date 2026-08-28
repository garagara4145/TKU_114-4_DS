package ChG04;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

public class G04_3 {

    static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    static void test1(Graphics gr) {

        int x0 = 20, y0 = 80;

        gr.setFont(new Font("a", Font.PLAIN, 60));
        gr.drawString("a", x0, y0);

        y0 += 80;
        gr.setFont(new Font("b", Font.PLAIN, 60));
        gr.drawString("b", x0, y0);

        y0 += 80;
        gr.setFont(new Font("c", Font.PLAIN, 60));
        gr.drawString("c", x0, y0);

        y0 += 80;
        gr.setFont(new Font("D", Font.PLAIN, 60));
        gr.drawString("D", x0, y0);

        y0 += 80;
        gr.setFont(new Font("e", Font.PLAIN, 60));
        gr.drawString("e", x0, y0);

       
    }

    public static void main(String[] dummy) {

        final JFrame jf1 = new JFrame("G04_3");

        jf1.setBounds(100, 100, 850, 750);
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Graphics gr = jf1.getContentPane().getGraphics();

        delay(100);

        test1(gr);
    }
}