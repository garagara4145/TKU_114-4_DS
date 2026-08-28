package ChG04;
import java.awt.Graphics;
import javax.swing.JFrame;

public class G04_4 {

    static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    static void test1(Graphics gr) {

        int cx = 200;
        int cy = 150;
        int r = 100;

        int[] xs = new int[4];
        int[] ys = new int[4];

        for (int i = 0; i < 4; i++) {

            double angle = 2 * Math.PI * i / 4 - Math.PI / 4;

            xs[i] = cx + (int)(r * Math.cos(angle));
            ys[i] = cy + (int)(r * Math.sin(angle));
        }

        gr.drawPolygon(xs, ys, 4);
    }

    public static void main(String[] dummy) {

        final JFrame jf1 = new JFrame("G04_4");

        jf1.setBounds(100, 100, 500, 400);
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Graphics gr = jf1.getContentPane().getGraphics();

        delay(100);

        test1(gr);
    }
}