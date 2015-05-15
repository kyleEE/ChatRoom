import java.io.*;
import java.util.*;

public class Message implements Serializable{
   private enum_MessageType type;
   private String sender;
   private String receiver; 
   private String message;
   private String fileName;
   private int iconIndex;
   private int room;
   private boolean broadcast;
   private Vector<String> userList;
   private byte[] file;
   private Vector<String> blockList;
   
   public Message(enum_MessageType p_type, String p_sender, String p_message){
      type=p_type;
      sender=p_sender;
      message=p_message;
      initialize();
   }

   public Message(enum_MessageType p_type, String p_sender){
      this(p_type, p_sender, "");
   }
   
   public Message(enum_MessageType p_type, String p_sender, String p_fileName, byte[] buffer){
      type=p_type;
      sender=p_sender;
      fileName=p_fileName;
      file= buffer;
      initialize();
   }

   private void initialize(){
      broadcast=true;
      userList=null;
      blockList=null;
      receiver="";
      iconIndex=-1;
      room=1;
   }
   /* Setter */
   public void setReceiver(String name){ receiver=name;}
   public void setIconIndex(int i){ iconIndex=i;}
   public void setRoom(int i){ room=i; }
   public void setPrivate(){ broadcast=false;}
   public void setUserList(Vector<String> v){ userList=v;}
   public void setBlockList(Vector<String> v){ blockList=v;} 
   public void setFileName(String p_fileName){ fileName=p_fileName;}
//   void setByteLength(int size){byteSize=size;}
   /* Getter */
   enum_MessageType getType(){ return type;}
   public String getSender(){ return sender;}
   public String getReceiver(){ return receiver;}
   public String getMessage(){ return message;}
   public String getFileName(){ return fileName;}
   public int getRoom(){ return room; }
   public int getIconIndex(){ return iconIndex;}
   public boolean getBroadcast(){ return broadcast;}
   public Vector<String> getUserList(){ return userList;}
   public byte[] getFile(){ return file;}
//   int getByteLength() { return byteSize;}
}
