package ChG07;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JFrame;

public class G07_3 extends tw.fc.gui.EzPanel {

    Color rectColor = new Color(0, 255, 180);

    int[] recX = {70, 100, 130, 160};
    int[] recY = {30, 60, 90, 120};
    int[] recW = {100, 100, 100, 100};
    int[] recH = {70, 70, 70, 70};

    boolean hold = false;

    int lastX, lastY;

    int selected = -1;

    @Override
    public void paintComponent(Graphics gr) {

        super.paintComponent(gr);

        gr.setColor(Color.WHITE);

        gr.fillRect(50, 50, 200, 3);
        gr.fillRect(50, 65, 200, 3);

        gr.setColor(rectColor);

        for (int i = 0; i < recX.length; i++) {

            gr.fillRect(
                recX[i],
                recY[i],
                recW[i],
                recH[i]
            );
        }
    }

    @Override
    public void mousePressed(MouseEvent ev) {

        final int x = ev.getX();
        final int y = ev.getY();

        selected = -1;

        for (int i = recX.length - 1; i >= 0; i--) {

            if (recX[i] <= x &&
                x <= recX[i] + recW[i] &&
                recY[i] <= y &&
                y <= recY[i] + recH[i]) {

                selected = i;

                hold = true;

                lastX = x;
                lastY = y;

                break;
            }
        }

        if (selected == -1) {
            hold = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent ev) {

        if (hold) {

            final int x = ev.getX();
            final int y = ev.getY();

            final int dx = x - lastX;
            final int dy = y - lastY;

            recX[selected] += dx;
            recY[selected] += dy;

            lastX = x;
            lastY = y;

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent ev) {

        hold = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent ev) {

        final int x = ev.getX();
        final int y = ev.getY();

        int target = -1;

        for (int i = recX.length - 1; i >= 0; i--) {

            if (recX[i] <= x &&
                x <= recX[i] + recW[i] &&
                recY[i] <= y &&
                y <= recY[i] + recH[i]) {

                target = i;
                break;
            }
        }

        if (target != -1) {

            int wheel = ev.getWheelRotation();

            recW[target] -= wheel * 5;
            recH[target] -= wheel * 5;

            if (recW[target] < 20) {
                recW[target] = 20;
            }

            if (recH[target] < 20) {
                recH[target] = 20;
            }

            repaint();
        }
    }

    public static void main(String[] dummy) {

        final JFrame mf = new JFrame("DragRectsW");

        mf.setBounds(100, 50, 400, 300);

        final G07_3 myPanel = new G07_3();

        mf.setContentPane(myPanel);

        mf.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        mf.setVisible(true);
    }
}