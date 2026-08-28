//import javax.swing.*;
package ChG05;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

//public class PlotPaneT extends SimplePanel {
public class PlotPaneT extends EzPanel {

   // static final int R10=10;
   final int R10=10;
   final int R20=20;
   final int R30=30;

   public void mouseEntered(MouseEvent ev) { 
      final Graphics gr=this.getGraphics();   
      gr.drawRect(0,0, this.getWidth()-1, this.getHeight()-1);
   }
   public void mouseExited(MouseEvent ev) {  
      final Graphics gr=this.getGraphics();   
      gr.setColor(super.getBackground());  //: getBackground() of Component
      gr.fillRect(0,0, this.getWidth(), this.getHeight());
   }
   public void mousePressed(MouseEvent ev) {
      final int x=ev.getX(), y=ev.getY();    
      final Graphics gr=this.getGraphics();   
      gr.drawOval(x-this.R20,y-this.R20, this.R20*2,this.R20*2);
   }
   public void mouseReleased(MouseEvent ev) {
      final int x=ev.getX(), y=ev.getY();    
      final Graphics gr=this.getGraphics();   
      gr.drawOval(x-this.R30,y-this.R30, this.R30*2,this.R30*2);
   }
   public void mouseClicked(MouseEvent ev) {
      final int x=ev.getX(), y=ev.getY();    
      final Graphics gr=this.getGraphics();   
      gr.setColor(Color.WHITE);
      gr.fillOval(x-this.R10,y-this.R10, this.R10*2,this.R10*2);
   }

  //========================================
   public static void main(String[] dummy) { 
      final JFrame mf=new JFrame("Plot");
      mf.setBounds(100, 50, 400,300);
      final PlotPaneT myPanel=new PlotPaneT();            
      mf.setContentPane(myPanel);    
      mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mf.setVisible(true);        
   }
}



