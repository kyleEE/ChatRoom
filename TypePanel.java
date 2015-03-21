import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypePanel extends JPanel{
   JRadioButton radioButton_public;
   JRadioButton radioButton_private;
   JLabel labelIcon_public;
   JLabel labelIcon_private;

   public TypePanel(){
      setLayout(new BorderLayout(5,5));
      initialize();
   }

   private void initialize(){
      /* Inner panel container */
      JPanel northContainer=new JPanel(new FlowLayout(FlowLayout.LEFT));
      JPanel centerContainer=new JPanel(new FlowLayout(FlowLayout.LEFT));

      /* Public message button */
      radioButton_public = new JRadioButton("",true);
      northContainer.add(radioButton_public);

      labelIcon_public = new JLabel("");
      labelIcon_public.setIcon(new ImageIcon("GUIicon/broadcast.png"));
      northContainer.add(labelIcon_public);
      
      /* Blank block */
      JLabel betweenPublicPrivate=new JLabel("  ");
      northContainer.add(betweenPublicPrivate);

      /* Private message button */
      radioButton_private = new JRadioButton("",false);
      northContainer.add(radioButton_private);
      
      labelIcon_private = new JLabel("");
      labelIcon_private.setIcon(new ImageIcon("GUIicon/private.png"));
      northContainer.add(labelIcon_private);

      /* Group the public and private buttons */
      ButtonGroup targetGroup=new ButtonGroup();
      targetGroup.add(radioButton_public);
      targetGroup.add(radioButton_private);

      /* User choosing combo box */
      JComboBox comboBox_ID = new JComboBox();
      comboBox_ID.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
      comboBox_ID.setModel(new DefaultComboBoxModel(new String[] {"ID1", "ID2", "ID3"}));// TODO
      comboBox_ID.setToolTipText("");
      //comboBox_ID.setBounds(190, 460, 120, 20);
      northContainer.add(comboBox_ID);

      /* */
      this.add(northContainer, BorderLayout.NORTH);
   }
}