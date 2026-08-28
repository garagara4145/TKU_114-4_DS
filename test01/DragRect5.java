import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DragRect5 extends JPanel implements MouseListener, MouseMotionListener {

    Color rectColor = new Color(0, 0, 255, 180);

    int recX = 70, recY = 30, recW = 100, recH = 70;

    int redX = 70, redY = 100, redW = 100, redH = 70;

    boolean hold;
    int lastX, lastY;

    public DragRect5() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.setColor(Color.GREEN);
        gr.fillRect(0, 0, getWidth(), getHeight());

        gr.setColor(Color.WHITE);
        gr.fillRect(50, 50, 200, 3);
        gr.fillRect(50, 65, 200, 3);

      
        gr.setColor(rectColor);
        gr.fillRect(this.recX, this.recY, this.recW, this.recH);

        
        gr.setColor(Color.RED);
        gr.fillRect(this.redX, this.redY, this.redW, this.redH);
    }

    @Override
    public void mousePressed(MouseEvent ev) {
        final int x = ev.getX(), y = ev.getY();

        if (this.recX <= x && x <= this.recX + this.recW
                && this.recY <= y && y <= this.recY + this.recH) {

            this.hold = true;
            this.lastX = x;
            this.lastY = y;
        }
        else {
            this.hold = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.hold) {
            final int x = e.getX(), y = e.getY();

            final int dx = x - this.lastX;
            final int dy = y - this.lastY;

          
            this.recX += dx;
            this.recY += dy;

            
            this.redX += dx;
            this.redY += dy;

            this.lastX = x;
            this.lastY = y;

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.hold = false;
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

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] dummy) {
        JFrame mF = new JFrame("DragRect");

        mF.setBounds(100, 50, 400, 300);

        mF.setContentPane(new DragRect5());

        mF.setVisible(true);

        mF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}