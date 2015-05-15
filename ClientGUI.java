import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.*;

public class ClientGUI implements ActionListener{

   private JFrame frame;
   private JFrame chooseFileFrame;
   private Client client;
   private LogPanel northPanel;
   private TypePanel southPanel;
   private ChatPanel centerPanel;
   private ListPanel eastPanel;
   private String ID;

   JComboBox comboBox_ID = new JComboBox();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ClientGUI window = new ClientGUI();
               window.frame.setVisible(true);
               //TabsFrame frame=new TabsFrame();
            } 
            catch (Exception e) {
               e.printStackTrace();              
            }
         }
      });
   }

    /* Constructor */
   public ClientGUI() {
      initialize();
      client=new Client();
      client.setGUI(this);
   }

   public void actionPerformed(ActionEvent e){
   }
   
   private void initialize() {
      frame = new JFrame();
      frame.getContentPane().setBackground(Color.lightGray);
      frame.setBounds(100, 10, 900, 800); // x, y, width, height
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BorderLayout(5,5));
      
      /* North Log Panel */
      northPanel=new LogPanel(this);
      frame.add(northPanel, BorderLayout.NORTH);

      /* South Type Panel */
      southPanel=new TypePanel(this);
      frame.add(southPanel, BorderLayout.SOUTH);

      /* Center Chat Panel */
      centerPanel=new ChatPanel(this);
      frame.add(centerPanel, BorderLayout.CENTER);

      /* East List Panel */
      eastPanel=new ListPanel(this);
      frame.add(eastPanel, BorderLayout.EAST);
      
      
      /* User list 
      JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("ID1");
      chckbxmntmNewCheckItem.setBounds(689, 141, 133, 254);
      frame.getContentPane().add(chckbxmntmNewCheckItem);
      
      JButton button_friend = new JButton("");
      button_friend.setIcon(new ImageIcon("GUIicon/users.png"));
      button_friend.setBounds(723, 70, 71, 60);
      frame.getContentPane().add(button_friend); */
   }
   /* Getter */
   public String getID(){
      return ID;
   }
   
   public String getReceiver(){
      return client.getReceiver();
   }
   /* Setter */
   public void setClient(Client c){
      client=c;
   }

   /* Public function for Client */
   public void appendText(String msg, String type){
      centerPanel.appendText(msg,type);
   }

   public boolean appendIcon(int i){
      centerPanel.appendIcon(i);
      return true;
   }
   /*
   public boolean appendImage(){
      centerPanel.appendImage();
      return true;
   }
   
   public boolean appendFile(){
      centerPanel.appendFile();
      return true;
   }
   */

   /* Public function for Type Panel */
   public void setPublic(){ client.setBroadcast(true);}
   public void setPrivate(){ client.setBroadcast(false);}
   public void setReceiver(String r){ client.setReceiver(r);}

   public void setRoom(int r){
      centerPanel.addRoom(r);  
      centerPanel.changeRoom(r); 
      System.out.println("setRoom function called, room number: "+r);
   }

   public void renewRoom(int r){
      eastPanel.renewRoom(r); 
      centerPanel.roomcheck(r);  
      centerPanel.changeRoom(r);
   }

   public void blockUser(boolean block){}
   /*
   public void chooseFile(){
	client.sendFile()
   }
   */

   public boolean sendrenewRoom(int room){
      client.renewRoom(room);
      return true;
   }

   public boolean sendRoom(int room){
      client.sendRoom(room);
      return true;
   }

   public boolean sendText(String text){
      int k=centerPanel.getchangeRoom();
      centerPanel.changeRoom(k);
      client.sendRoom(k);
      client.sendText(text);
      return true;
   }
   public boolean sendIcon(int ind){
      int k=centerPanel.getchangeRoom();
      centerPanel.changeRoom(k);
      client.sendRoom(k);
      client.sendIcon(ind);
      return true;
   }
   
   public boolean sendFile(File myFile){
      client.sendFile(myFile);
	  //client.sendImage(myFile);
      return true;
   }
   
   public boolean sendImage(File myFile){
      client.sendImage(myFile);
      return true;
   }
   
   public void setUserList(Vector<String> v){
      southPanel.setUserList(v);
      eastPanel.setUserList(v);
   }

   /* Public function for Log Panel */
   public boolean tryLogin(String id, String ip, int port){
      client.setLogInfo(id,ip,port);
      if( client.start() ){
         ID=id;
         return true;
      }
      return false;
   }
   
   public boolean tryLogout(){
      client.logOut();
      return true;
   }
	public void appendImage(String filePath){
		centerPanel.appendImage(filePath);
	}
}

  