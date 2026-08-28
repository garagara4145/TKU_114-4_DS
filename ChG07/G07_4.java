package ChG07;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class G07_4 extends JComponent {

    int x1 = 100;
    int y1 = 100;

    int x2 = 200;
    int y2 = 100;

    int r = 100;

    @Override
    protected void paintComponent(Graphics gr) {

        super.paintComponent(gr);

        gr.drawOval(
            x1 - r,
            y1 - r,
            r * 2,
            r * 2
        );

        gr.drawOval(
            x2 - r,
            y2 - r,
            r * 2,
            r * 2
        );
    }

    public static void main(String[] args) {

        final JFrame mf = new JFrame("G07_4");

        mf.setBounds(100, 50, 500, 400);

        mf.setContentPane(new G07_4());

        mf.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        mf.setVisible(true);
    }
}