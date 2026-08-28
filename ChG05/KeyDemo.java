package ChG05;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
//import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

// //public class KeyDemo extends tw.fc.gui.GraphicPanel {
public class KeyDemo extends EzPanel {

   static final int LEFT=0x25, RIGHT=0x27, UP=0x26, DOWN=0x28;

   final int H_distance=15, V_distance=20;
   static JFrame mf;

   int posX=100, posY=100;
   Graphics gr;

   void drawPos() {
      gr.setColor(getBackground());
      gr.fillRect(0,0, this.getWidth(), this.getHeight());
      gr.setColor(Color.RED); 
      gr.drawRect(this.posX, this.posY, 
                  this.H_distance, this.V_distance);
   } 

   void drawChar(char c) {
      drawPos();
      gr.setColor(Color.BLACK); 
      gr.setFont(new Font(null, Font.PLAIN, 24));  // <== poor
      gr.drawString(""+c, this.posX, this.posY+this.V_distance);
   }

   @Override
   public void keyTyped(java.awt.event.KeyEvent e) {
      final char c=e.getKeyChar(); //: 已解析出charactor
      drawChar(c); 
//    cout.hex().p("[").pc((int)c).p(c).p("]"); //: 典型用法
   }

   @Override
   public void keyPressed(java.awt.event.KeyEvent e) {
      final int keyCode=e.getKeyCode();  
         //: 純按鍵, 不分大小寫. 含shift, alt, F1-F10, edit-key
      switch(keyCode){
      case LEFT:
         this.posX-=this.H_distance;   this.drawPos();
         break; 
      case RIGHT:
         this.posX+=this.H_distance;   this.drawPos();
         break; 
      case UP:
         this.posY-=this.V_distance;   this.drawPos();
         break; 
      case DOWN:
         this.posY+=this.V_distance;   this.drawPos();
         break; 
      default:
      }
//    cout.hex().p("(.").p(keyCode).p(")");  //: 典型用法
   }


  //========================================
   public static void main(String[] dummy) { 
      mf=new JFrame("KeyDemo");
      mf.setBounds(100,50, 400,300);
      final KeyDemo myPanel=new KeyDemo();            
      mf.setContentPane(myPanel);    
      mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mf.setVisible(true);     
      myPanel.gr=myPanel.getGraphics();    
      EzPanel.delay(100);   
      myPanel.drawPos();
   }
}



