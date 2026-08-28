package ChG04;
import java.awt.Graphics;
import javax.swing.JFrame;

public class G04_1 {

    static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    static void test1(Graphics gr) {

        int r = 100;       
        int x0 = 100;
        int y0 = 100;

        gr.drawOval(x0, y0, 2 * r, 2 * r);
        gr.drawOval(x0 + r, y0, 2 * r, 2 * r);
    }

    public static void main(String[] dummy) {

        final JFrame jf1 = new JFrame("G04_1");

        jf1.setBounds(100, 100, 500, 350);
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Graphics gr = jf1.getContentPane().getGraphics();

        delay(100);

        test1(gr);
    }
}