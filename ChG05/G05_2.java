

package ChG05;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

public class G05_2 {

    static class MyFrame extends JFrame implements MouseMotionListener {

        int oldX;
        int oldY;

        MyFrame() {

            setTitle("G05-2");
            setBounds(200, 150, 400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            addMouseMotionListener(this);

            setVisible(true);
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int w = getWidth();
            int h = getHeight();

            w += x - oldX;
            h += y - oldY;

            if (w < 200) {
                w = 200;
            }

            if (h < 150) {
                h = 150;
            }

            setSize(w, h);

            oldX = x;
            oldY = y;
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            oldX = e.getX();
            oldY = e.getY();
        }
    }

    public static void main(String[] args) {

        new MyFrame();
    }
}