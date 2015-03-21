import java.awt.*;
import javax.swing.*;

public class testFrame extends JFrame{
   public testFrame(){
      super( "This is a frame for testing" );
      setLayout(new BorderLayout(5,5));
      LogPanel logPanel=new LogPanel();
      add(logPanel, BorderLayout.NORTH);
      TypePanel typePanel=new TypePanel();
      add(typePanel, BorderLayout.SOUTH);
   }
   public static void main(String[] args){
      testFrame tf=new testFrame();
      tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      tf.setSize(1000,600);
      tf.setVisible(true);
   }
}