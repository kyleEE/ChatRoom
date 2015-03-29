import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypePanel extends JPanel implements ActionListener{
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
   private JComboBox comboBox_ID;

   private ClientGUI cGui;
   private int room;
   private boolean broadcast;
   private String receiver;
   private int iconIndex;

   public TypePanel(ClientGUI gui){
      setLayout(new BorderLayout(5,5));
      cGui=gui;
      initialize();
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
      comboBox_ID.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
      comboBox_ID.setModel(new DefaultComboBoxModel(new String[] {"ID1", "ID2", "ID3"}));// TODO
      comboBox_ID.setToolTipText("");
      //comboBox_ID.setBounds(190, 460, 120, 20);
      comboBox_ID.setActionCommand("ChooseTarget");
      comboBox_ID.addActionListener(this);
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
      button_sendPicture.setActionCommand("SendPicture");
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
      

      /* */
      this.add(northContainer, BorderLayout.NORTH);
      this.add(centerContainer, BorderLayout.CENTER);
   }

   public void actionPerformed(ActionEvent e){

      String command=String.format( e.getActionCommand() );
      switch(command){
         case "Public": cGui.setPublic(); break;
         case "Private": cGui.setPrivate(); break;
         case "Block": break;
         case "Unblock": break;
         case "QIcon":            
            cGui.iconbar();
            break;
         case "SendPicture": break;
         case "ChooseTarget":
            String target= comboBox_ID.getSelectedItem().toString();
            System.out.println(target);
            break;
         case "Send": // when user pressed the send message button
         default: // when user pressed enter in the typing textfield
            String text = textField_typing.getText().toString();
            textField_typing.setText("");
            cGui.appendText(text);
            System.out.println(text);
            break;
      }
   }

}