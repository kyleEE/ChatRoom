import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;

public class ListPanel extends JPanel implements ActionListener{
   private JButton button_addfriend;
   private Vector<JCheckBoxMenuItem> userList;

   private ClientGUI cGui;
   private int room;
   private int usernum;
   private String receiver;
   private JPanel northContainer;
   private JPanel centerContainer;

   public ListPanel(ClientGUI gui){
      setLayout(new BorderLayout(5,5));
      //setPreferredSize(new Dimension(200,500));
      cGui=gui;
      initialize();
   }
   
   private void initialize(){
      
      room=1;
      northContainer=new JPanel(new FlowLayout(FlowLayout.CENTER));
      northContainer.setPreferredSize(new Dimension(300,80));
      centerContainer=new JPanel(new FlowLayout(FlowLayout.LEFT,30,5));
      centerContainer.setPreferredSize(new Dimension(300,500));

      button_addfriend=new JButton(new ImageIcon("GUIicon/users.png"));
      button_addfriend.setActionCommand("RoomAdd");
      button_addfriend.addActionListener(this);
      northContainer.add(button_addfriend);

      userList= new Vector<JCheckBoxMenuItem>(); 
      usernum=0;

      this.add(northContainer, BorderLayout.NORTH);
      this.add(centerContainer, BorderLayout.CENTER);
   }

    public void setUserList(Vector<String> v){
      centerContainer.removeAll();
      userList.clear();
      usernum=v.size()-1;
      for(int i=1; i<v.size(); i++){
         if( v.get(i).equals( cGui.getID() ) ) continue;
         userList.add( new JCheckBoxMenuItem(v.get(i)) );
      }
      for(int i=0; i<userList.size(); i++) centerContainer.add(userList.get(i));
      centerContainer.revalidate();
    }

   public void renewRoom(int r){
      room=r;
   }


   public void actionPerformed(ActionEvent e){
      String originReceiver=cGui.getReceiver();
      String command=String.format( e.getActionCommand() );
      switch(command){
         case "RoomAdd":
            room++; 
            cGui.setRoom(room);
            for(int i=0; i<userList.size(); i++){
               if( userList.get(i).isSelected() )
               {
                  String target= userList.get(i).getText().toString();
                  System.out.println(target);
                  cGui.setReceiver(target);
                  cGui.sendRoom(room); 
               }      
            }  
         cGui.setReceiver("All"); 
         cGui.sendrenewRoom(room);
         cGui.setReceiver(originReceiver);
         break;
         
      }

   }
}