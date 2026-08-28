package ChG07;

package ChG07;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class G07_2 extends tw.fc.gui.EzPanel {

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
            return;
        }

        int tempX = recX[selected];
        int tempY = recY[selected];
        int tempW = recW[selected];
        int tempH = recH[selected];

        for (int i = selected; i < recX.length - 1; i++) {

            recX[i] = recX[i + 1];
            recY[i] = recY[i + 1];
            recW[i] = recW[i + 1];
            recH[i] = recH[i + 1];
        }

        int last = recX.length - 1;

        recX[last] = tempX;
        recY[last] = tempY;
        recW[last] = tempW;
        recH[last] = tempH;

        selected = last;

        repaint();
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

    public static void main(String[] dummy) {

        final JFrame mf = new JFrame("DragRectsTop");

        mf.setBounds(100, 50, 400, 300);

        final G07_2 myPanel = new G07_2();

        mf.add(myPanel);

        mf.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        mf.setVisible(true);
    }
}