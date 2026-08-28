package ChG02;

import javax.swing.JFrame;

public class G02_1 {
    public static void main(String[] args) {

        JFrame jf = new JFrame("G02_1");

        jf.setBounds(200, 100, 500, 300);

        System.out.println("X = " + jf.getX());
        System.out.println("Y = " + jf.getY());
        System.out.println("W = " + jf.getWidth());
        System.out.println("H = " + jf.getHeight());

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}