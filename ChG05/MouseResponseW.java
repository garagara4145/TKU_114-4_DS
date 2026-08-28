package ChG05;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

// //public class MouseResponseW extends tw.fc.gui.GraphicPanel {
// public class MouseResponseW extends SimplePanel {
public class MouseResponseW extends EzPanel {

   static JFrame mf;

   @Override public void mouseEntered(MouseEvent ev) { 
      System.out.print("[");
   } 
   @Override public void mouseExited(MouseEvent ev) {  
      System.out.print("]");
   }
   @Override public void mousePressed(MouseEvent ev) {
      switch(ev.getButton()) {
      case MouseEvent.BUTTON1:
         System.out.print("\\L");  break;
      case MouseEvent.BUTTON2:  
         System.out.print("\\M");  break;
      case MouseEvent.BUTTON3:
         System.out.print("\\R");  break;
      }
   }
   @Override public void mouseReleased(MouseEvent ev) {
      switch(ev.getButton()) {
      case MouseEvent.BUTTON1:
         System.out.print("/L");  break;
      case MouseEvent.BUTTON2:  
         System.out.print("/M");  break;
      case MouseEvent.BUTTON3:
         System.out.print("/R");  break;
      }
   }
   @Override public void mouseClicked(MouseEvent ev) {
      System.out.print(ev.getClickCount());
   }
   @Override public void mouseMoved(MouseEvent ev) { 
      mf.setTitle(" ("+ev.getX()+", "+ev.getY()+") ");
      System.out.print("-");
   }
   @Override public void mouseDragged(MouseEvent ev) {  
      mf.setTitle(" ("+ev.getX()+", "+ev.getY()+") ");
      System.out.print("_");
   }
   @Override public void mouseWheelMoved(MouseWheelEvent ev) {
      final int roll=ev.getWheelRotation();
      if(roll>0) {  System.out.print("b"+roll);  }  
      else if(roll<0) {  System.out.print("f"+(-roll));  }  
   }
  //========================================
   public static void main(String[] dummy) { 
      mf=new JFrame("Mouse Response");
      mf.setBounds(100, 400, 330,150);
      final MouseResponseW myPanel=new MouseResponseW();            
      mf.setContentPane(myPanel);    
      mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mf.setVisible(true);        
   }
}



