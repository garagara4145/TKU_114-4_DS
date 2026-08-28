package ChG05;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class G05_1 {

    static class MyFrame extends JFrame implements KeyListener {

        int dx = 10;
        int dy = 10;

        MyFrame() {

            setTitle("G05-1");
            setBounds(200, 150, 400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            addKeyListener(this);
            setFocusable(true);

            setVisible(true);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int x = getX();
            int y = getY();

            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    x -= dx;
                    break;

                case KeyEvent.VK_RIGHT:
                    x += dx;
                    break;

                case KeyEvent.VK_UP:
                    y -= dy;
                    break;

                case KeyEvent.VK_DOWN:
                    y += dy;
                    break;
            }

            setLocation(x, y);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    public static void main(String[] args) {

        new MyFrame();
    }
}