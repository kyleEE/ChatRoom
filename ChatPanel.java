import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class ChatPanel extends JPanel implements ActionListener{
   private ClientGUI cGui;
   private JTextPane textPane1;
   private SimpleAttributeSet attrset1;
   private JTextPane textPane2;
   private SimpleAttributeSet attrset2;
   private JTextPane textPane3;
   private SimpleAttributeSet attrset3;
   private JFrame frameicon;
   
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

   public ChatPanel(ClientGUI gui){
      setLayout(null);
      cGui=gui;
      initialize();
   }
   
   private void initialize(){
      textPane1= new JTextPane();
      textPane2= new JTextPane();
      textPane3= new JTextPane();
      attrset1= new SimpleAttributeSet();
      attrset2= new SimpleAttributeSet();
      attrset3= new SimpleAttributeSet();

      /* TextPane1 */
      StyleConstants.setFontSize(attrset1,12);
      StyleConstants.setBackground(attrset1,Color.WHITE);
      StyleConstants.setForeground(attrset1,Color.BLACK);
      StyleConstants.setFontFamily(attrset1,"serif");
      StyleConstants.setBold(attrset1,false);
      StyleConstants.setItalic(attrset1,false);
      textPane1.setEditable(false);
      
      /* TextPane2 */ 
      StyleConstants.setFontSize(attrset2,12);
      StyleConstants.setBackground(attrset2,Color.WHITE);
      StyleConstants.setForeground(attrset2,Color.BLACK);
      StyleConstants.setFontFamily(attrset2,"serif");
      StyleConstants.setBold(attrset2,false);
      StyleConstants.setItalic(attrset2,false);
      textPane2.setEditable(false);

      /* TextPane3 */ 
      StyleConstants.setFontSize(attrset3,12);
      StyleConstants.setBackground(attrset3,Color.WHITE);
      StyleConstants.setForeground(attrset3,Color.BLACK);
      StyleConstants.setFontFamily(attrset3,"serif");
      StyleConstants.setBold(attrset3,false);
      StyleConstants.setItalic(attrset3,false);
      textPane3.setEditable(false);

      JScrollPane scroll_1=new JScrollPane(textPane1,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane1

      JScrollPane scroll_2=new JScrollPane(textPane2,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane2

      JScrollPane scroll_3=new JScrollPane(textPane3,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane3
        
      JTabbedPane tabPane= new JTabbedPane();
      tabPane.setBounds(30, 10, 600, 300);
      add(tabPane);
      tabPane.addTab("chatroom1",scroll_1);
      tabPane.addTab("chatroom2",scroll_2);
      tabPane.addTab("chatroom3",scroll_3);
   }
   
   public void actionPerformed(ActionEvent e){
      
      String command=String.format( e.getActionCommand() );
      switch(command){
         case "Q0":          
         button_choose=0;
         break;
         case "Q1":          
         button_choose=1;
         break;
          case "Q2":          
         button_choose=2;
         break;
          case "Q3":          
         button_choose=3;
         break;
          case "Q4":          
         button_choose=4;
         break;
          case "Q5":          
         button_choose=5;
         break;
          case "Q6":          
         button_choose=6;
         break;
          case "Q7":          
         button_choose=7;
         break;
          case "Q8":          
         button_choose=8;
         break;
          case "Q9":          
         button_choose=9;
         break;
          case "Q10":          
         button_choose=10;
         break;
          case "Q11":          
         button_choose=11;
         break;

        
      }
      appendIcon(button_choose);
   }

   public void appendText(String msg){
      try{
         Document docs_a1=textPane1.getDocument();         
         docs_a1.insertString(docs_a1.getLength(),"\n" +msg, attrset1);
      }
      catch(BadLocationException ee){
         System.out.println("false");
      }
   }

   public void appendIcon(int i){
      try{
         Document docs_a2=textPane1.getDocument();           
         docs_a2.insertString(docs_a2.getLength(),"\n",attrset1); 
         ImageIcon icon_a1 = new ImageIcon("QIcon/"+i+".png");
         textPane1.insertIcon(icon_a1); 
         docs_a2.insertString(docs_a2.getLength(),"\n",attrset1); 
      }
      catch(BadLocationException ee){
         System.out.println("false");
      }
   }

    public void iconbar(){

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
               
        frameicon.getContentPane().setBackground(Color.lightGray);
        frameicon.setVisible(true);
        frameicon.setBounds(100, 100, 600, 400); // x, y, width, height
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
                    
        button_icon0.addActionListener(this);
        button_icon1.addActionListener(this);
        button_icon2.addActionListener(this);
        button_icon3.addActionListener(this);
        button_icon4.addActionListener(this);
        button_icon5.addActionListener(this);
        button_icon6.addActionListener(this);
        button_icon7.addActionListener(this);
        button_icon8.addActionListener(this);
        button_icon9.addActionListener(this);
        button_icon10.addActionListener(this);
        button_icon11.addActionListener(this);
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

}