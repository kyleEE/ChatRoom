import java.io.*;

public class Message implements Serializable{
   private enum_MessageType type;
   private String sender;
   private String receiver; 
   private String message;
   private int iconIndex;
   private int room;
   private boolean broadcast;

   public Message(enum_MessageType p_type, String p_sender, String p_message){
      type=p_type;
      sender=p_sender;
      message=p_message;
      receiver="";
      iconIndex=-1;
      room=0;
      broadcast=true;
   }

   public Message(enum_MessageType p_type, String p_sender){
      this(p_type, p_sender, "");
   }
   
   /* Setter */
   void setReceiver(String name){ receiver=name;}
   void setIconIndex(int i){ iconIndex=i;}
   void setRoom(int i){ room=i; }
   void setPrivate(){ broadcast=false;}
   
   /* Getter */
   enum_MessageType getType(){ return type;}
   String getSender(){ return sender;}
   String getReceiver(){ return receiver;}
   String getMessage(){ return message;}
   int getRoom(){ return room; }
   int getIconIndex(){ return iconIndex;}
   boolean getBroadcast(){ return broadcast;}
}
