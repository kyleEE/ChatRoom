import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class LogPanel extends JPanel implements ActionListener{
   private JLabel label_userIcon;
   private JTextField textField_ID;
   private JTextField textField_IP;
   private JTextField textField_PORT;
   private JLabel label_ID;
   private JLabel label_IP;
   private JLabel label_PORT;
   private JButton button_login;
   private JButton button_logout;
   private ClientGUI cGUI;
   private boolean isIn; // wether the user is logged in now
   private String ID;
   private String IP;
   private int PORT;

   public LogPanel(ClientGUI gui){
      setLayout(new FlowLayout(FlowLayout.LEFT));
      //setPreferredSize(new Dimension(400,150));
      cGUI=gui;
      isIn=false;
      initialize();
      /* User input ID */  
      String IDinput=JOptionPane.showInputDialog("Please input your ID:","");
      if( !IDinput.equals("") ){
         ID=IDinput;
         textField_ID.setText(ID);
      }
   }

   private void initialize(){
      /* Inner panel container */
      JPanel iconPanel=new JPanel(null);
      iconPanel.setPreferredSize(new Dimension(120,120));
      JPanel infoPanel=new JPanel(null);
      infoPanel.setPreferredSize(new Dimension(260,120));
      JPanel buttonPanel=new JPanel(null);
      buttonPanel.setPreferredSize(new Dimension(200,120));

      /* User icon */
      JLabel label_userIcon = new JLabel("");
      int i=1;
      label_userIcon.setIcon(new ImageIcon("QIcon/"+i+".png"));
      label_userIcon.setBounds(30, 30, 80, 80);
      iconPanel.add(label_userIcon);
      
      /* ID field */
      label_ID = new JLabel("ID");
      infoPanel.add(label_ID);
      label_ID.setBounds(10, 30, 40, 25);

      textField_ID = new JTextField("Anonymous",15); // specify the number of columns in the field
      textField_ID.setBounds(55, 30, 200, 25);
      infoPanel.add(textField_ID);

      /* IP field */ 
      label_IP = new JLabel("IP");
      label_IP.setBounds(10, 57, 40, 25);
      infoPanel.add(label_IP);
      
      textField_IP = new JTextField("140.112.18.202",15);
      textField_IP.setBounds(55, 57, 200, 25);
      infoPanel.add(textField_IP);

      /* Port field */
      label_PORT = new JLabel("PORT");
      label_PORT.setBounds(10, 84, 40, 25);
      infoPanel.add(label_PORT);

      //textField_PORT = new JTextField(4);
      textField_PORT=new JFormattedTextField(createFormatter("####"));
      textField_PORT.setBounds(55, 84, 200, 25);
      textField_PORT.setText("5678");
      infoPanel.add(textField_PORT);
      
      /* Login button */ 
      button_login = new JButton(new ImageIcon("GUIicon/doorIn.png"));
      button_login.setBounds(10, 30, 80, 80);
      button_login.setActionCommand("logIn");
      button_login.addActionListener(this);
      buttonPanel.add(button_login);
      
      /* Logout button */
      button_logout = new JButton(new ImageIcon("GUIicon/doorOut.png"));
      button_logout.setBounds(100, 30, 80, 80);
      button_logout.setActionCommand("logOut");
      button_logout.setEnabled(false);
      button_logout.addActionListener(this);
      buttonPanel.add(button_logout);

      this.add(iconPanel);
      this.add(infoPanel);
      this.add(buttonPanel);
   }
   
   protected MaskFormatter createFormatter(String s) {
      MaskFormatter formatter = null;
         try {
            formatter = new MaskFormatter(s);
         } 
         catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
         }
      return formatter;
   }

   public void actionPerformed(ActionEvent e){
      String command=String.format( e.getActionCommand() );
      switch(command){
         case "logIn":
            if( getInfo() ){
               if( cGUI.tryLogin(ID,IP,PORT) ) modifyButtonState(true);
            }
            break;
         case "logOut":
            int confirm=JOptionPane.showConfirmDialog(null,"Are you sure to leave the chatroom?","Confirm",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
               if( cGUI.tryLogout() ) modifyButtonState(false);
            }
            break;
      }
   }

   private boolean getInfo(){
      ID=textField_ID.getText().toString();
      IP=textField_IP.getText().toString();
      String str_PORT=textField_PORT.getText().toString();
      PORT=Integer.parseInt(str_PORT);
      return true;
   }

   private void modifyButtonState(boolean isLogin){
      button_login.setEnabled(!isLogin);
      button_logout.setEnabled(isLogin);
      textField_ID.setEditable(!isLogin);
      textField_IP.setEditable(!isLogin);
      textField_PORT.setEditable(!isLogin);
   }

}
