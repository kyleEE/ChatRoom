import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ClientGUI implements ActionListener{

   private JFrame frame;
   private Client client;
   private LogPanel northPanel;
   private TypePanel southPanel;
   private ChatPanel centerPanel;
   private ListPanel eastPanel;
   private boolean broadcast;
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
      frame.setBounds(100, 100, 1000, 600); // x, y, width, height
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

   /* Setter */
   public void setClient(Client c){
      client=c;
   }

   /* Public function for Client */
   public void appendText(String msg){
      centerPanel.appendText(msg);
   }

   public boolean appendIcon(int i){
      centerPanel.appendIcon(i);
      return true;
   }

   public boolean iconbar(){
      centerPanel.iconbar();
      return true;
   }

   /* Public function for Type Panel */
   public void setPublic(){ broadcast=true;}
   public void setPrivate(){ broadcast=false;}
   public void blockUser(boolean block, String user){}
   public boolean sendText(String text){
      client.sendText(text);
      return true;
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
      return true;
   }
}

  