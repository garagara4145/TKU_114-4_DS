// package tw.fc.gui;
package ChG05;
//import java.awt.Frame;
//import java.awt.Window;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
//import javax.swing.JPanel;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
//import java.awt.event.KeyEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Component;

//[  本類別的功能:
//[  1. 當frame的content pane用, 預設底色
//[     (可用java.awt.Component的setBackground改)
//[  2. 預設各種listener, 使程式師只須覆寫要用到的反應函數
//[     並提供滑鼠判別 isLeft, isRight, withControl, withShift, withAlt
//*********************************************//
// public abstract class SimplePanel
public abstract class EzPanel
   extends JComponent
   implements MouseInputListener,
              MouseWheelListener,
              KeyListener,
              ComponentListener,
              WindowListener
{
   //[==========  static part  ================================

   private static final long serialVersionUID= 2005111612L;

   public static final Cursor handCursor=   new Cursor(Cursor.HAND_CURSOR);
   public static final Cursor waitCursor=   new Cursor(Cursor.WAIT_CURSOR);
   public static final Cursor normalCursor= new Cursor(Cursor.DEFAULT_CURSOR);

   @SuppressWarnings("static-access") //A31208X
   public static void delay(int ms) { //:  各種game常會需要 delay
      try {
         Thread.sleep(ms);
      }
      catch(InterruptedException e) { }
   }

   //[ 設定去鋸齒化功能
   public static void antialias(Graphics g, boolean b) {
      if(g==null) throw new IllegalArgumentException("\n... null Graphics");
      final Graphics2D g2d=(Graphics2D)g;
      if(b) {
         g2d.setRenderingHint(
           RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON
        );
      }
      else {
         g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,///開啟去鋸齒化功能
            RenderingHints.VALUE_ANTIALIAS_OFF
         );
     }
   }

 //]==========  static part  ================================

 //[ =========  instance part  ==============================


   //------------------------------------------------
   public EzPanel() {
      this.setBackground(new Color(0x99,0xFF,0x99));
              //: of java.awt.JComponent,
              //: it overrides Components.setBackground
 
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.addMouseWheelListener(this);
      this.addKeyListener(this);
      this.addComponentListener(this);
   // this.addWindowListener(this);  //: 不行! 只能加在class Window上
   }

   //---------------------------------------------------------

   protected void paintComponent(Graphics gr) {
    // gr.clearRect(0,0,getWidth(), getHeight());
    //[ jdkdoc說若繼承JPanel就可以靠super畫背景
    //[ 本class只繼承JComponent, 所以自己畫背景
      final Color oldColor= gr.getColor();
      final Color bc= this.getBackground(); //: of java.awt.Component
  //D System.out.println("getBackground():"+bc);
      gr.setColor(bc);  gr.fillRect(0,0,getWidth(), getHeight());
      gr.setColor(oldColor); //: 否則可能會害接下來畫的看不到
    //] 畫背景

      this.requestFocusInWindow();  //: in JComponent
           //: jdkdoc說不一定會成功.
           //: 要等收到 FocusEvent.FOCUS_GAINED 才確定成功.
   }

   //---------------------------------------------------

   public static final boolean isLeftDown(MouseEvent ev) {
      // return ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK )!=0) ;
      return (ev.getButton()==MouseEvent.BUTTON1);  //: jdk1.4起
   }
   public static final boolean isMiddleDown(MouseEvent ev) {
      // return ((ev.getModifiersEx() & InputEvent.BUTTON2_DOWN_MASK )!=0) ;
      return (ev.getButton()==MouseEvent.BUTTON2);  //: jdk1.4起
   }
   public static final boolean isRightDown(MouseEvent ev) {
      // return ((ev.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK )!=0) ;
      return (ev.getButton()==MouseEvent.BUTTON3);  //: jdk1.4起
   }

   public static final boolean withControl(MouseEvent ev) {
      return ev.isControlDown();
   }
   public static final boolean withShift(MouseEvent ev) {
      return ev.isShiftDown();
   }
   public static final boolean withAlt(MouseEvent ev) {
      return ev.isAltDown();
   }
   //-------

 //[ ---------  implements  MouseInputListener  -------------
 //[  should be overrided to proper actions
 //[  MouseEvent給全名是為寫subclass時便於抄製
   public void mouseEntered(java.awt.event.MouseEvent ev) {  }
   public void mouseExited(java.awt.event.MouseEvent ev) {  }
   public void mousePressed(java.awt.event.MouseEvent ev) {  }
   public void mouseReleased(java.awt.event.MouseEvent ev) {  }
   public void mouseClicked(java.awt.event.MouseEvent ev) {  }
   public void mouseMoved(java.awt.event.MouseEvent ev) {  }
   public void mouseDragged(java.awt.event.MouseEvent ev) {  }
 //] ---------  implements  MouseInputListener  -------------

 //[ ---------  implements MouseWheelListener  -------------
   public void mouseWheelMoved(java.awt.event.MouseWheelEvent ev) {  }
 //] ---------  implements MouseWheelListener  -------------

 //[ ---------  KeyListener   ------------------------
 //[ should be overridden to proper actions
   @Override public void keyPressed(java.awt.event.KeyEvent ev) {
   // final int keyCode=ev.getKeyCode();  //: 純按鍵, 不分大小寫.
   // tw.fc.Std.cout.hex().p("(.").p(keyCode).p(")");  //: 典型用法
   }
   @Override public void keyReleased(java.awt.event.KeyEvent ev) {
   // final int keyCode=ev.getKeyCode();  //: 純按鍵, 不分大小寫.
   // tw.fc.Std.cout.hex().p("(^").p(keyCode).p(")");  //: 典型用法
   }
   @Override public void keyTyped(java.awt.event.KeyEvent ev) {
   // final char c=ev.getKeyChar();    //: 已解析出charactor
   // tw.fc.Std.cout.p("[").pc((int)c).p(c).p("]");  //: 典型用法
   }


 //[ implements ComponentListener
   @Override public void componentShown(ComponentEvent ev) { }
   @Override public void componentHidden(ComponentEvent ev) { }
   @Override public void componentMoved(ComponentEvent ev) { }
   @Override public void componentResized(ComponentEvent ev) { }  

 //[ implements WindowListener
   @Override public void windowActivated(WindowEvent ev) {
      // requestFocusInWindow();  //: in JComponent
          //: jdkdoc說不一定會成功.
          //: 要等收到 FocusEvent.FOCUS_GAINED 才確定成功.
   //D  System.out.print("!A");
   }
   @Override public void windowClosed(WindowEvent ev) {
      // ?
   }
   @Override public void windowClosing(WindowEvent ev) {
      // if(JOptionPane.YES_OPTION==
      //    JOptionPane.showConfirmDialog(
      //       theFrame, "Really to Exit?", "TwFC",
      //       JOptionPane.YES_NO_OPTION
      //    )
      // ) {
      //    this.flag_stopPulse=true;
      //    System.exit(0);
      // } else {
      //    ;  //:  ignore
      // }
   }
   @Override public void windowDeactivated(WindowEvent ev) { }
   @Override public void windowDeiconified(WindowEvent ev) {
      requestFocusInWindow();  //: in JComponent
        //: jdkdoc說不一定會成功.
        //: 要等收到 FocusEvent.FOCUS_GAINED 才確定成功.
   }
   @Override public void windowIconified(WindowEvent ev) { }
   @Override public void windowOpened(WindowEvent ev) {
      requestFocusInWindow();  //: in JComponent
        //: jdkdoc說不一定會成功.
        //: 要等收到 FocusEvent.FOCUS_GAINED 才確定成功.
   //D  System.out.print("!O");
   }


   //] =========  instance part  ==============================

} //]  class SimplePanel   ===============================
