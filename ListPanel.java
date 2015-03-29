import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class ListPanel extends JPanel implements ActionListener{
   private ClientGUI cGui;
   private JButton button_t;

   public ListPanel(ClientGUI gui){
      setLayout(null);
      cGui=gui;
      initialize();
   }
   
   private void initialize(){

   
      JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("ID1");
      chckbxmntmNewCheckItem.setBounds(9, 141, 8, 8);
      //eastContainer.setBounds(30, 10, 100, 300);
      add(chckbxmntmNewCheckItem);

      button_t = new JButton(new ImageIcon("GUIicon/typing_40_40.png"));
      button_t.setBounds(70, 20, 40, 35);
      add(button_t);

   }

   public void actionPerformed(ActionEvent e){
   }
}