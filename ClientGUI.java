import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingConstants;

public class ClientGUI{

   private JFrame frame;
   private Client clientManager;
   private JTextField textField_TYPE;
   private JTextField textField_ID;
   private JTextField textField_IP;
   private JTextField textField_PORT;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ClientGUI window = new ClientGUI();
               window.frame.setVisible(true);
               TabsFrame frame=new TabsFrame();//
            } 
            catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public ClientGUI() {
      initialize();

   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frame = new JFrame();
      frame.getContentPane().setBackground(Color.lightGray);
      frame.setBounds(100, 100, 900, 600); // x, y, width, height
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      //
      /* Display chat message */
      JTextArea textArea = new JTextArea();
      textArea.setEditable(false);
      textArea.setDragEnabled(true);
      textArea.setBounds(40, 120, 600, 300);
      frame.getContentPane().add(textArea);    
      textArea.append("an an chat room");
      textArea.setLineWrap(true);//auto change line
    
     /* JTabbedPane tabPane= new JTabbedPane();
      tabPane.setBounds(40, 120, 600, 300);//(40, 120, 600, 300)
      add(tabPane);
      tabPane.addTab("chatroom1",textArea);  */

      /* Public button */
      JRadioButton radioButton_public = new JRadioButton("",true);
      Dimension size=radioButton_public.getPreferredSize();
      radioButton_public.setBounds(40, 460, size.width, size.height);
      frame.getContentPane().add(radioButton_public);
      
      JLabel lblNewLabel_radio = new JLabel("");
      lblNewLabel_radio.setIcon(new ImageIcon("GUIicon/broadcast.png"));
      lblNewLabel_radio.setBounds(65, 450, 40, 40);
      frame.getContentPane().add(lblNewLabel_radio);
      
      /* Private message button */
      JRadioButton radioButton_private = new JRadioButton("",false);
      size=radioButton_private.getPreferredSize();
      radioButton_private.setBounds(110, 460, size.width, size.height);
      frame.getContentPane().add(radioButton_private);
      
      JLabel lblNewLabel_private = new JLabel("");
      lblNewLabel_private.setIcon(new ImageIcon("GUIicon/private.png"));
      lblNewLabel_private.setBounds(135, 450, 40, 40);
      frame.getContentPane().add(lblNewLabel_private);
      
      /* Group the public and private buttons */
      ButtonGroup targetGroup=new ButtonGroup();
      targetGroup.add(radioButton_public);
      targetGroup.add(radioButton_private);

      /* User choosing combo box */
      JComboBox comboBox_ID = new JComboBox();
      comboBox_ID.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
      comboBox_ID.setModel(new DefaultComboBoxModel(new String[] {"ID1", "ID2", "ID3"}));// TODO
      comboBox_ID.setToolTipText("");
      comboBox_ID.setBounds(190, 460, 120, 20);
      frame.getContentPane().add(comboBox_ID);
      
      /* Text entering field */
      textField_TYPE = new JTextField();
      textField_TYPE.setBounds(40, 490, 500, 40);
      frame.getContentPane().add(textField_TYPE);
      textField_TYPE.setColumns(10);
      textField_TYPE.setText("type there");
      
      /* Send message button */
      JButton button_TYPE = new JButton("");
      button_TYPE.setIcon(new ImageIcon("GUIicon/typing_40_40.png"));
      button_TYPE.setBounds(550, 490, 40, 40);
      frame.getContentPane().add(button_TYPE);
      
      /* Send photo button */
      JButton button_PHOTO = new JButton("");
      button_PHOTO.setIcon(new ImageIcon("GUIicon/photo_40_40.png"));
      button_PHOTO.setBounds(600, 490, 40, 40);
      frame.getContentPane().add(button_PHOTO);
      
      /* SMILE button */
      JButton button_SMILE = new JButton("");
      button_SMILE.setIcon(new ImageIcon("GUIicon/Smile.png"));
      button_SMILE.setBounds(330, 450, 40, 40);
      frame.getContentPane().add(button_SMILE);
      
      /* GOOD button */
      JButton button_GOOD = new JButton("");
      button_GOOD.setIcon(new ImageIcon("GUIicon/thumbs-up-icon.png"));
      button_GOOD.setBounds(380, 450, 40, 40);
      frame.getContentPane().add(button_GOOD);
      
      /* ID textfield */
      textField_ID = new JTextField();
      textField_ID.setBounds(170, 30, 240, 25);
      frame.getContentPane().add(textField_ID);
      textField_ID.setColumns(15);
      textField_ID.setText("Anonymous");
      
      JLabel label_ID = new JLabel("ID");
      label_ID.setBounds(120, 30, 45, 25);
      frame.getContentPane().add(label_ID);

      /* IP textfield */ 
      textField_IP = new JTextField();
      textField_IP.setBounds(170, 57, 240, 25);
      frame.getContentPane().add(textField_IP);
      textField_IP.setColumns(10);
      textField_IP.setText("140.112.18.202");
      
      JLabel label_IP = new JLabel("IP");
      label_IP.setBounds(120, 57, 45, 25);
      frame.getContentPane().add(label_IP);
      
      /* Port textfield */
      textField_PORT = new JTextField();
      textField_PORT.setBounds(170, 84, 240, 25);
      frame.getContentPane().add(textField_PORT);
      textField_PORT.setColumns(4);
      textField_PORT.setText("5678");

      JLabel label_PORT = new JLabel("PORT");
      label_PORT.setBounds(120, 84, 45, 25);
      frame.getContentPane().add(label_PORT);
      
      /* */
      JLabel label_user = new JLabel("");
      label_user.setIcon(new ImageIcon("GUIicon/userIcon.png"));
      label_user.setBounds(10, 29, 83, 69);
      frame.getContentPane().add(label_user);
      
      /* Login button */ 
      JButton button_login = new JButton("");
      button_login.setIcon(new ImageIcon("GUIicon/login.png"));
      button_login.setBounds(449, 29, 71, 69);
      frame.getContentPane().add(button_login);
      
      /* Logout button */
      JButton button_logout = new JButton("");
      button_logout.setIcon(new ImageIcon("GUIicon/logout.png"));
      button_logout.setBounds(530, 29, 71, 69);
      frame.getContentPane().add(button_logout);
      
      /* User list */
      JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("ID1");
      chckbxmntmNewCheckItem.setBounds(689, 141, 133, 254);
      frame.getContentPane().add(chckbxmntmNewCheckItem);
      
      JButton button_friend = new JButton("");
      button_friend.setIcon(new ImageIcon("GUIicon/users.png"));
      button_friend.setBounds(723, 70, 71, 60);
      frame.getContentPane().add(button_friend);
   }
}
class TabsFrame extends JFrame{
     TabsFrame(){
      
      setVisible(true);
      setLayout(null);
      setBounds(40, 120, 250, 150);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      /*JScrollPane scroll=new JScrollPane(textArea,
         ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//scroll
      frame.add(scroll);*/

    }
   }

  