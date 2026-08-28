package ChG05;
//import java.awt.*;
//import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

// //public class KeyResponse extends tw.fc.gui.GraphicPanel {
public class KeyResponse extends EzPanel {
//public class KeyResponse extends tw.fc.gui.EzPanel {

   static JFrame mf;

   public void keyTyped(java.awt.event.KeyEvent ev) {
      final char c=ev.getKeyChar();    
         //: 已解析出charactor
      System.out.print(
         "["+Integer.toHexString(c)+","+c+"]"
      );  //: 典型用法
//    cout.hex().p("[").pc((int)c).p(c).p("]"); //: 典型用法
   }

   public void keyPressed(java.awt.event.KeyEvent ev) {
      final int keyCode=ev.getKeyCode();  
         //: 純按鍵, 不分大小寫. 含shift, alt, F1-F10, edit-key
      System.out.print(
         "(."+Integer.toHexString(keyCode)+")"
      );  //: 典型用法
//    cout.hex().p("(.").p(keyCode).p(")");  //: 典型用法
   }
   public void keyReleased(java.awt.event.KeyEvent ev) {
      final int keyCode=ev.getKeyCode();  //: 純按鍵, 不分大小寫.
      System.out.print(
         "(^"+Integer.toHexString(keyCode)+")"
      );  //: 典型用法
//    cout.hex().p("(^").p(keyCode).p(")");  //: 典型用法
   }

  //========================================
   public static void main(String[] dummy) { 
      mf=new JFrame("Key Response");
      mf.setBounds(100, 50, 330,150);
      final KeyResponse myPanel=new KeyResponse();            
      mf.setContentPane(myPanel);    
      mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mf.setVisible(true);        
   }
}



