import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import javax.swing.filechooser.*;

public class TypePanel extends JPanel implements ActionListener{
   /* for GUI layout */
   private JRadioButton radioButton_public;
   private JRadioButton radioButton_private;
   private JLabel labelIcon_public;
   private JLabel labelIcon_private;
   private JButton button_block;
   private JButton button_unBlock;
   private JButton button_sendQIcon;
   private JButton button_sendPicture;
   private JButton button_sendFile;
   private JTextField textField_typing;
   private JButton button_sendMsg;
   private JTextField textField_room;
   private JButton button_room;
   private JComboBox comboBox_ID;
   /* */
   private ClientGUI cGui;
   private int room;
   private boolean broadcast;
   private String receiver;
   private int iconIndex;
   private boolean syncUserList;

   /* for QIcon */
   private JFrame frameicon;
  // private JFileChooser fileChooser;
   private QIconListener ql;
   private int button_choose;
   private JButton button_icon0;
   private JButton button_icon1;
   private JButton button_icon2;
   private JButton button_icon3;
   private JButton button_icon4;
   private JButton button_icon5;
   private JButton button_icon6;
   private JButton button_icon7;
   private JButton button_icon8;
   private JButton button_icon9;
   private JButton button_icon10;
   private JButton button_icon11;

   public TypePanel(ClientGUI gui){
      setLayout(new BorderLayout(5,5));
      cGui=gui;
      initialize();
      ql=new QIconListener();
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               frameicon = new JFrame();
         } 
            catch (Exception e) {
               e.printStackTrace();              
            }
         }
      });
   }

   private void initialize(){
      /* Inner panel container */
      JPanel northContainer=new JPanel(new FlowLayout(FlowLayout.LEFT));
      //JPanel centerContainer=new JPanel(new FlowLayout(FlowLayout.LEFT));
      JPanel centerContainer=new JPanel(null);
      centerContainer.setPreferredSize(new Dimension(800,50));

      /* Blank block */
      JLabel beforePublic=new JLabel("            ");
      northContainer.add(beforePublic);

      /* Public message button */
      radioButton_public = new JRadioButton("",true);
      radioButton_public.setActionCommand("Public");
      radioButton_public.addActionListener(this);
      northContainer.add(radioButton_public);

      labelIcon_public = new JLabel("");
      labelIcon_public.setIcon(new ImageIcon("GUIicon/broadcast.png"));
      northContainer.add(labelIcon_public);
      
      /* Blank block */
      JLabel betweenPublicPrivate=new JLabel("  ");
      northContainer.add(betweenPublicPrivate);

      /* Private message button */
      radioButton_private = new JRadioButton("",false);
      radioButton_private.setActionCommand("Private");
      radioButton_private.addActionListener(this);
      northContainer.add(radioButton_private);
      
      labelIcon_private = new JLabel("");
      labelIcon_private.setIcon(new ImageIcon("GUIicon/private.png"));
      northContainer.add(labelIcon_private);

      /* Group the public and private buttons */
      ButtonGroup targetGroup=new ButtonGroup();
      targetGroup.add(radioButton_public);
      targetGroup.add(radioButton_private);

      /* Blank block */
      JLabel betweenPrivaetID=new JLabel("  ");
      northContainer.add(betweenPrivaetID);

      /* User choosing combo box */
      comboBox_ID = new JComboBox();
      comboBox_ID.setEditable(true);
      comboBox_ID.setActionCommand("ChooseTarget");
      comboBox_ID.addActionListener(this);
      syncUserList=false;
      northContainer.add(comboBox_ID);
      
      /* Blank block */
      JLabel betweenIDBlock=new JLabel("   ");
      northContainer.add(betweenIDBlock);
      
      /* Block and unblock user icon */
      button_block=new JButton(new ImageIcon("GUIicon/block.png"));
      button_block.setActionCommand("Block");
      button_block.addActionListener(this);
      northContainer.add(button_block);

      button_unBlock=new JButton(new ImageIcon("GUIicon/unBlock.png"));
      button_unBlock.setActionCommand("Unblock");
      button_unBlock.addActionListener(this);
      northContainer.add(button_unBlock);

      /* QIcon */
      button_sendQIcon=new JButton(new ImageIcon("GUIicon/QIcon.png"));
      button_sendQIcon.setActionCommand("QIcon");
      button_sendQIcon.addActionListener(this);
      northContainer.add(button_sendQIcon);

      /* Send picture */
      button_sendPicture=new JButton(new ImageIcon("GUIicon/photo.png"));
      button_sendPicture.setActionCommand("SendImage");
      button_sendPicture.addActionListener(this);
      northContainer.add(button_sendPicture);

      /* Send file */
      button_sendFile=new JButton(new ImageIcon("GUIicon/sendFile.png"));
      button_sendFile.setActionCommand("SendFile");
      button_sendFile.addActionListener(this);
      northContainer.add(button_sendFile);

      /* Text entering field */
      textField_typing = new JTextField();
      textField_typing.setColumns(30);
      textField_typing.setHorizontalAlignment(JTextField.RIGHT);
      textField_typing.setBounds(60, 0, 400, 35);
      textField_typing.addActionListener(this);
      centerContainer.add(textField_typing);

      /* Send message button */
      button_sendMsg = new JButton(new ImageIcon("GUIicon/typing_40_40.png"));
      button_sendMsg.setBounds(470, 0, 40, 35);
      button_sendMsg.setActionCommand("Send");
      button_sendMsg.addActionListener(this);
      centerContainer.add(button_sendMsg);

      /* Room number */
      textField_room = new JTextField();
      textField_room.setColumns(30);
      textField_room.setHorizontalAlignment(JTextField.LEFT);
      textField_room.setBounds(520, 0, 40, 35);
      textField_room.addActionListener(this);
      centerContainer.add(textField_room);

      /* Send message button */
      button_room = new JButton(new ImageIcon("GUIicon/typing_40_40.png"));
      button_room.setBounds(570, 0, 40, 35);
      button_room.setActionCommand("Room");
      button_room.addActionListener(this);
      centerContainer.add(button_room);
      

      /* */
      this.add(northContainer, BorderLayout.NORTH);
      this.add(centerContainer, BorderLayout.CENTER);
   }

   public void setUserList(Vector<String> v){
      syncUserList=true;
      comboBox_ID.removeAllItems();
      for(int i=0; i<v.size(); i++){
         if( v.get(i).equals( cGui.getID() ) ) continue;
         comboBox_ID.addItem(v.get(i));
      }
      syncUserList=false;
   }

   public void actionPerformed(ActionEvent e){

      String command=String.format( e.getActionCommand() );
      switch(command){
         case "Room"  : 
            String textroom = textField_room.getText().toString();
            int textroom1 =Integer.parseInt(textroom);
            cGui.setRoom(textroom1); 
            break;
         case "QIcon"  : iconbar(); break;
         case "Public" : cGui.setPublic();  break;
         case "Private": cGui.setPrivate(); break;
         case "Block"  : cGui.blockUser(true ); break;
         case "Unblock": cGui.blockUser(false); break;
         case "SendImage" : 
            imageChoose();
            break;
         case "SendFile":
            //cGui.chooseFile();
            fileChoose();
            break;
         case "ChooseTarget":
            if(!syncUserList){
               String target= comboBox_ID.getSelectedItem().toString();
               cGui.setReceiver(target);
               //System.out.println(target); // for DEBUG
            }
            break;
         case "Send": // when user pressed the send message button
         default: // when user pressed enter in the typing textfield
            String text = textField_typing.getText().toString();
            if(text.equals("")) break;
            textField_typing.setText("");
            cGui.sendText(text);
            //cGui.appendText(text,"regular");// for DEBUG
            //System.out.println(text);// for DEBUG
            break;
      }
   }

   public class QIconListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String command=String.format( e.getActionCommand() );
         switch(command){
            case "Q0" : button_choose=0; break;
            case "Q1" : button_choose=1; break;
            case "Q2" : button_choose=2; break;
            case "Q3" : button_choose=3; break;
            case "Q4" : button_choose=4; break;
            case "Q5" : button_choose=5; break;
            case "Q6" : button_choose=6; break;
            case "Q7" : button_choose=7; break;
            case "Q8" : button_choose=8; break;
            case "Q9" : button_choose=9; break;
            case "Q10": button_choose=10; break;
            case "Q11": button_choose=11; break;
         }
         //cGui.appendIcon(button_choose);// for DEBUG
         cGui.sendIcon(button_choose);
         frameicon.dispose();
      }
   }

   private void iconbar(){
      frameicon.getContentPane().setBackground(Color.lightGray);
      frameicon.setVisible(true);
      frameicon.setBounds(750, 50, 600, 700); // x, y, width, height
      frameicon.getContentPane().setLayout(new FlowLayout());
      button_icon0=new JButton(new ImageIcon("QIcon/0.png"));
      frameicon.getContentPane().add(button_icon0);
      button_icon1=new JButton(new ImageIcon("QIcon/1.png"));
      frameicon.getContentPane().add(button_icon1);
      button_icon2=new JButton(new ImageIcon("QIcon/2.png"));
      frameicon.getContentPane().add(button_icon2);
      button_icon3=new JButton(new ImageIcon("QIcon/3.png"));
      frameicon.getContentPane().add(button_icon3);
      button_icon4=new JButton(new ImageIcon("QIcon/4.png"));
      frameicon.getContentPane().add(button_icon4);
      button_icon5=new JButton(new ImageIcon("QIcon/5.png"));
      frameicon.getContentPane().add(button_icon5);
      button_icon6=new JButton(new ImageIcon("QIcon/6.png"));
      frameicon.getContentPane().add(button_icon6);
      button_icon7=new JButton(new ImageIcon("QIcon/7.png"));
      frameicon.getContentPane().add(button_icon7);
      button_icon8=new JButton(new ImageIcon("QIcon/8.png"));
      frameicon.getContentPane().add(button_icon8);
      button_icon9=new JButton(new ImageIcon("QIcon/9.png"));
      frameicon.getContentPane().add(button_icon9);
      button_icon10=new JButton(new ImageIcon("QIcon/10.png"));
      frameicon.getContentPane().add(button_icon10);
      button_icon11=new JButton(new ImageIcon("QIcon/11.png"));
      frameicon.getContentPane().add(button_icon11);       
                    
      button_icon0.addActionListener(ql);
      button_icon1.addActionListener(ql);
      button_icon2.addActionListener(ql);
      button_icon3.addActionListener(ql);
      button_icon4.addActionListener(ql);
      button_icon5.addActionListener(ql);
      button_icon6.addActionListener(ql);
      button_icon7.addActionListener(ql);
      button_icon8.addActionListener(ql);
      button_icon9.addActionListener(ql);
      button_icon10.addActionListener(ql);
      button_icon11.addActionListener(ql);
      button_icon0.setActionCommand("Q0");
      button_icon1.setActionCommand("Q1");
      button_icon2.setActionCommand("Q2");
      button_icon3.setActionCommand("Q3");
      button_icon4.setActionCommand("Q4");
      button_icon5.setActionCommand("Q5");
      button_icon6.setActionCommand("Q6");
      button_icon7.setActionCommand("Q7");
      button_icon8.setActionCommand("Q8");
      button_icon9.setActionCommand("Q9");
      button_icon10.setActionCommand("Q10");
      button_icon11.setActionCommand("Q11");
   }
   
   private void fileChoose(){
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new java.io.File("."));
   fileChooser.setDialogTitle("Choose a file");
   if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): "+ fileChooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : "+ fileChooser.getSelectedFile());
        File myFile= new File(fileChooser.getSelectedFile().getAbsolutePath());
        cGui.sendFile(myFile);
   }
   }
   private void imageChoose(){
    JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new java.io.File("."));
   fileChooser.setDialogTitle("Choose a image");
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
	fileChooser.setFileFilter(filter);
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): "+ fileChooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : "+ fileChooser.getSelectedFile());
        File myFile= new File(fileChooser.getSelectedFile().getAbsolutePath());
        cGui.sendImage(myFile);
   }
   }
}



















