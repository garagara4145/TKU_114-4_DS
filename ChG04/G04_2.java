package ChG04;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class G04_2 {

    static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    static void test1(Graphics gr) {

        int x0 = 20, y0 = 20;
        final int uW = 80, uH = 80;

        // 內建顏色
        gr.setColor(Color.RED);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.ORANGE);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.YELLOW);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.GREEN);
        gr.fillOval(x0, y0, uW, uH);

        x0 = 20;
        y0 += 100;

        gr.setColor(Color.CYAN);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.BLUE);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.MAGENTA);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.PINK);
        gr.fillOval(x0, y0, uW, uH);

        x0 = 20;
        y0 += 100;

        gr.setColor(Color.BLACK);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.DARK_GRAY);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.GRAY);
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(Color.LIGHT_GRAY);
        gr.fillOval(x0, y0, uW, uH);

        x0 = 20;
        y0 += 100;

        gr.setColor(Color.WHITE);
        gr.fillOval(x0, y0, uW, uH);

        // 自訂顏色
        x0 += 100;
        gr.setColor(new Color(100, 150, 200));
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(new Color(200, 100, 150));
        gr.fillOval(x0, y0, uW, uH);

        x0 += 100;
        gr.setColor(new Color(100, 200, 100));
        gr.fillOval(x0, y0, uW, uH);
    }

    public static void main(String[] dummy) {

        final JFrame jf1 = new JFrame("G04_2");

        jf1.setBounds(100, 100, 450, 550);
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Graphics gr = jf1.getContentPane().getGraphics();

        delay(100);

        test1(gr);
    }
}