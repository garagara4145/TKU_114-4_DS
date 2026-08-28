package ChG07;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

public class G07_5 extends JComponent
        implements MouseInputListener {

    int x1 = 100;
    int y1 = 100;

    int x2 = 200;
    int y2 = 100;

    int r = 100;

    int oldX;
    int oldY;

    boolean dragging = false;

    public G07_5() {

        addMouseListener(this);
        addMouseMotionListener(this);
    }

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

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {

            oldX = e.getX();
            oldY = e.getY();

            dragging = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (dragging) {

            int newX = e.getX();
            int newY = e.getY();

            int dx = newX - oldX;
            int dy = newY - oldY;
            x1 += dx;
            y1 += dy;

            x2 += dx;
            y2 += dy;

            oldX = newX;
            oldY = newY;

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        dragging = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {

        final JFrame mf = new JFrame("G07_5");

        mf.setBounds(100, 50, 500, 400);

        final G07_5 myPanel = new G07_5();

        mf.setContentPane(myPanel);

        mf.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        mf.setVisible(true);
    }
}