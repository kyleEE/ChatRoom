import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class ChatPanel extends JPanel implements ChangeListener{
   private ClientGUI cGui;
   private JTextPane textPane1;
   private SimpleAttributeSet attrset1;
   private JTextPane textPane2;
   private SimpleAttributeSet attrset2;
   private JTextPane textPane3;
   private SimpleAttributeSet attrset3;
   private JTextPane textPane4;
   private SimpleAttributeSet attrset4;
   private JTextPane textPane5;
   private SimpleAttributeSet attrset5;
   private JTabbedPane tabPane;
   private int useroom;
   private int useroom_user; //for the user
   private int checkroomadd;
   private int no1;
   private int no2;
   private int no3;
   private int no4;
   private int no5;
   JScrollPane scroll_1;
   JScrollPane scroll_2;
   JScrollPane scroll_3;
   JScrollPane scroll_4;
   JScrollPane scroll_5;
   JScrollBar vertical; 


   public ChatPanel(ClientGUI gui){
      setLayout(null);
      cGui=gui;
      initialize();
   }
   
   private void initialize(){
      textPane1= new JTextPane();
      textPane2= new JTextPane();
      textPane3= new JTextPane();
      textPane4= new JTextPane();
      textPane5= new JTextPane();
      attrset1= new SimpleAttributeSet();
      attrset2= new SimpleAttributeSet();
      attrset3= new SimpleAttributeSet();
      attrset4= new SimpleAttributeSet();
      attrset5= new SimpleAttributeSet();

      StyledDocument doc = textPane1.getStyledDocument();
      StyledDocument doc2 = textPane2.getStyledDocument();
      StyledDocument doc3 = textPane3.getStyledDocument();
      StyledDocument doc4 = textPane4.getStyledDocument();
      StyledDocument doc5 = textPane5.getStyledDocument();
      addStylesToDocument(doc);
      addStylesToDocument(doc2);
      addStylesToDocument(doc3);
      addStylesToDocument(doc4);
      addStylesToDocument(doc5);

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

      /* TextPane4 */ 
      StyleConstants.setFontSize(attrset4,12);
      StyleConstants.setBackground(attrset4,Color.WHITE);
      StyleConstants.setForeground(attrset4,Color.BLACK);
      StyleConstants.setFontFamily(attrset4,"serif");
      StyleConstants.setBold(attrset4,false);
      StyleConstants.setItalic(attrset4,false);
      textPane4.setEditable(false);

      /* TextPane5 */ 
      StyleConstants.setFontSize(attrset5,12);
      StyleConstants.setBackground(attrset5,Color.WHITE);
      StyleConstants.setForeground(attrset5,Color.BLACK);
      StyleConstants.setFontFamily(attrset5,"serif");
      StyleConstants.setBold(attrset5,false);
      StyleConstants.setItalic(attrset5,false);
      textPane5.setEditable(false);

      scroll_1=new JScrollPane(textPane1,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane1
      scroll_1.setName("1");    

      scroll_2=new JScrollPane(textPane2,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane2
      scroll_2.setName("2");  

      scroll_3=new JScrollPane(textPane3,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane3
      scroll_3.setName("3");

      scroll_4=new JScrollPane(textPane4,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane4
      scroll_4.setName("4");  

      scroll_5=new JScrollPane(textPane5,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);//scroll for textPane5
      scroll_5.setName("5");  
        
      tabPane= new JTabbedPane();
      tabPane.addChangeListener(this);

                  
      tabPane.setBounds(30, 10, 500, 500);
      add(tabPane);
      addRoom(1);
      useroom=1;
      useroom_user=1;
      no1=0;
      no2=0;
      no3=0;
      no4=0;
      no5=0; 
   }
   public void roomcheck(int i){
   if (i==1&&no1==0){no1=2;
     // textPane1.setVisible(false);
   } 
   if (i==2&&no2==0){no2=2;
      //textPane2.setVisible(false);
   } 
   if (i==3&&no3==0){no3=2;
      //textPane3.setVisible(false);
   } 
   if (i==4&&no4==0){no4=2;
      //textPane4.setVisible(false);
   } 
   if (i==5&&no5==0){no5=2;
      //textPane5.setVisible(false);
   } 
   }

   public void addRoom(int i){
      if(i==1){
         tabPane.addTab("chatroom1",scroll_1);
      } 

      if(i==2){
         tabPane.addTab("chatroom2",scroll_2);
      }
  
      if(i==3){
         tabPane.addTab("chatroom3",scroll_3);
      }
   
      if(i==4){
         tabPane.addTab("chatroom4",scroll_4);
      }
    
      if(i==5){
         tabPane.addTab("chatroom5",scroll_5);
      }
   }
   
   public void changeRoom(int i){
      useroom=i;
   }
   
   public int getchangeRoom(){
      return useroom_user;
   }

   public void appendText(int room, String msg,String type){
      try{
         if(room==1){
         StyledDocument doc=textPane1.getStyledDocument();
         doc.insertString(doc.getLength(), msg+"\n" , doc.getStyle(type));
         textPane1.setCaretPosition(textPane1.getDocument().getLength());
         }
         if(room==2){
         StyledDocument doc2=textPane2.getStyledDocument();
         doc2.insertString(doc2.getLength(), msg+"\n" , doc2.getStyle(type));
         textPane2.setCaretPosition(textPane2.getDocument().getLength());
         }
         if(room==3){
         StyledDocument doc3=textPane3.getStyledDocument();
         doc3.insertString(doc3.getLength(), msg+"\n" , doc3.getStyle(type));
         textPane3.setCaretPosition(textPane3.getDocument().getLength());
         }
         if(room==4){
         StyledDocument doc4=textPane4.getStyledDocument();
         doc4.insertString(doc4.getLength(), msg+"\n" , doc4.getStyle(type));
         textPane4.setCaretPosition(textPane4.getDocument().getLength());
         }
         if(room==5){
         StyledDocument doc5=textPane5.getStyledDocument();
         doc5.insertString(doc5.getLength(), msg+"\n" , doc5.getStyle(type));
         textPane5.setCaretPosition(textPane5.getDocument().getLength());
         }

      }
      catch(BadLocationException ee){
         System.out.println("Fail to append text in ChatPanel: " + ee);
      }
   }

   public void appendIcon(int room,int i){
      try{
         if(room==1){
            textPane1.setCaretPosition(textPane1.getDocument().getLength());
            StyledDocument doc = textPane1.getStyledDocument();
            doc.insertString(doc.getLength(), "\n",doc.getStyle("icon"+i));
         }
         if(room==2){
            textPane2.setCaretPosition(textPane2.getDocument().getLength());
            StyledDocument doc2 = textPane2.getStyledDocument();
            doc2.insertString(doc2.getLength(), "\n",doc2.getStyle("icon"+i));
         }
         if(room==3){
            textPane3.setCaretPosition(textPane3.getDocument().getLength());
            StyledDocument doc3 = textPane3.getStyledDocument();
            doc3.insertString(doc3.getLength(), "\n",doc3.getStyle("icon"+i));
         }
         if(room==4){
            textPane4.setCaretPosition(textPane4.getDocument().getLength());
            StyledDocument doc4 = textPane4.getStyledDocument();
            doc4.insertString(doc4.getLength(), "\n",doc4.getStyle("icon"+i));
         }
         if(room==5){
            textPane5.setCaretPosition(textPane5.getDocument().getLength());
            StyledDocument doc5 = textPane5.getStyledDocument();   
            doc5.insertString(doc5.getLength(), "\n",doc5.getStyle("icon"+i));       
         }
         
      }
      catch(BadLocationException ee){
         System.out.println("Fail to append icon in ChatPanel: " + ee);
      }
   }
   /*
   public void appendImage(){
    try{
         StyledDocument doc = textPane1.getStyledDocument();
         doc.insertString(doc.getLength(), "\n",doc.getStyle("image"));
         textPane1.setCaretPosition(textPane1.getDocument().getLength());
    }
    catch(BadLocationException ee){
         System.out.println("Fail to append image in ChatPanel: " + ee);
    }
   }
   
   public void appendFile(){
      try{
         StyledDocument doc = textPane1.getStyledDocument();
         doc.insertString(doc.getLength(), "\n",doc.getStyle("file"));
         textPane1.setCaretPosition(textPane1.getDocument().getLength());
      }
      catch(BadLocationException ee){
         System.out.println("Fail to append file in ChatPanel: " + ee);
      }
   }
*/
 public void stateChanged(ChangeEvent e){
     Component c= tabPane.getSelectedComponent();
     String tab = c.getName();    
     int tab1 =Integer.parseInt(tab);
     useroom_user=tab1;

    System.out.println(c.getName());
}


   protected void addStylesToDocument(StyledDocument doc) {
      // Default style
      Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

      Style regular = doc.addStyle("regular", def);
      StyleConstants.setFontSize(regular, 14);
      StyleConstants.setFontFamily(def, "SansSerif");
      // Italic
      Style s = doc.addStyle("italic", regular);
      StyleConstants.setItalic(s, true);
      // Bold
      s = doc.addStyle("bold", regular);
      StyleConstants.setBold(s, true);
      // Small
      s = doc.addStyle("small", regular);
      StyleConstants.setFontSize(s, 10);
      // Large
      s = doc.addStyle("large", regular);
      StyleConstants.setFontSize(s, 16);
      // Icon
      for(int i=0; i<12; i++){
         s=doc.addStyle("icon"+i, regular);
         StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
         ImageIcon icon = new ImageIcon("QIcon/"+i+".png");
         if (icon != null) StyleConstants.setIcon(s, icon);
      }
      // Image
      s = doc.addStyle("image",regular);
      StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
      /*
      ImageIcon image = new ImageIcon("image");
      StyleConstants.setIcon(s, image);
*/
      // File      
      s = doc.addStyle("file",regular);
      StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
//      ImageIcon file = new ImageIcon("QIcon/"+i+".png");
//      if (file != null) StyleConstants.setIcon(s, icon);
      /* Button
      s = doc.addStyle("button", regular);
      StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
      ImageIcon soundIcon = createImageIcon("images/sound.gif",
                                              "sound icon");
      JButton button = new JButton();
      if (soundIcon != null) {
         button.setIcon(soundIcon);
      } else {
         button.setText("BEEP");
      }
      button.setCursor(Cursor.getDefaultCursor());
      button.setMargin(new Insets(0,0,0,0));
      button.setActionCommand(buttonString);
      button.addActionListener(this);
      StyleConstants.setComponent(s, button);
      */
   }
	public void appendImage(String filePath){
		textPane1.setCaretPosition(textPane1.getDocument().getLength());
		textPane1.insertIcon( new ImageIcon ( filePath ) );
		//textPane1.setCaretPosition(textPane1.getDocument().getLength());
		StyledDocument doc = textPane1.getStyledDocument();
		//doc.insertString(doc.getLength(), "\n",doc.getStyle("regular"));
      appendText(1, "", "regular");
	}
}