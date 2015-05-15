import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Client{
   private ObjectInputStream sInput;
   private ObjectOutputStream sOutput;
   private Socket socket;
   private ClientGUI cGUI;
   private String server,userName;
   private int port;
   /* Variables defining message */
   private int room;
   private String receiver;
   private boolean broadcast;
   
   /* Constructor */
   Client(){
      server="140.112.18.202";
      port=5678;
      userName="Anonymous";
      initialize();
   }

   Client(String s,int p,String u){
      server=s;
      port=p;
      userName=u;
      initialize();
   }

   private void initialize(){
      cGUI=null;
      room=1;
      receiver="All";
      broadcast=true;
   }

   /* Getter */
   public int getPort(){ return port;}
   public String getServer() { return server;}
   public String getUserName() { return userName;}
   public String getReceiver(){ return receiver;}
   /* Setter */
   public void setLogInfo(String user_id, String server_ip, int port){
      userName=user_id;
      server=server_ip;
      this.port=port;
   }
   public void setPort(int p){ port=p;}
   public void setServer(String u){ server=u;}
   public void setUserName(String user) { userName=user;}
   public void setGUI(ClientGUI cg){ cGUI=cg;}
   public void setReceiver(String r){ receiver=r;}
   public void setRoom(int r){ room=r;}
   public void setBroadcast(boolean b){ broadcast=b;}
   
   /* Login to the server */
   public boolean start(){
      display("<- User "+userName+" trying to login to "+server+" at port "+port+" ->","bold");
      // new socket
      try{
         socket=new Socket(server,port);
      }
      catch(Exception ec){
         display("Error connecting to server: "+ec);
         return false;
      }
      String message="<- Connection accepted, welcome! ->";
      display(message,"bold");
      try{
         sInput=new ObjectInputStream(socket.getInputStream());
         sOutput=new ObjectOutputStream(socket.getOutputStream());
      }
      catch(IOException eIO){
         display("Error creating new I/O Streams: " + eIO);
         return false;
      }
      new ListenFromServer().start();
      try{
         sOutput.writeObject(userName);
      }
      catch(IOException eIO){
         display("Error doing login: " + eIO);
         disconnect();
         return false;
      }
      return true;
   }

   /* Public functions for clinetGUI */

   public void renewRoom(int p_room){
      Message msg=new Message(enum_MessageType.RENEWROOM,userName);
      msg.setRoom(p_room);
      sendMessage(msg);
   }
  
   public void sendRoom(int p_room){
      Message msg=new Message(enum_MessageType.CREATROOM,userName);
      msg.setRoom(p_room);
      msg.setReceiver(receiver);
      sendMessage(msg);
   }

   public void sendText(String text){
      Message msg=new Message(enum_MessageType.TEXT,userName,text);
      msg.setRoom(room);
      sendMessage(msg);
   }

   public void sendIcon(int index){
      Message msg=new Message(enum_MessageType.ICON,userName);
      msg.setIconIndex(index);
      sendMessage(msg);
   }
   
   public void sendImage(File myFile){
      try{
      byte[] data = Files.readAllBytes(Paths.get(myFile.getPath()));
      Message msg=new Message(enum_MessageType.IMAGE,userName,myFile.getName(),data);
      sendMessage(msg);
      }
      catch(IOException eIO){
        display("Error sending image: " + eIO);
      }
   }
   
   public void sendFile(File myFile){
      try{
      byte[] data = Files.readAllBytes(Paths.get(myFile.getPath()));
      Message msg=new Message(enum_MessageType.FILE,userName,myFile.getName(),data);
      sendMessage(msg);
	  }
      catch(IOException eIO){
        display("Error sending file: " + eIO);
      }
   }

   /* Send Message */
   public void sendMessage(Message msg){
      if(!broadcast) msg.setPrivate();
      msg.setReceiver(receiver);
      try{
         sOutput.writeObject(msg);
      }
      catch(IOException e){
         display("Error writing to server" +  e);
      }
   }
   /* Display text in command line or GUI */
   private void display(String msg){
      String type="regular";
      if(cGUI==null)
         System.out.println(msg);
      else{
         cGUI.appendText(msg,type);
      }
   }
   private void display(String msg, String type){
      if(cGUI==null)
         System.out.println(msg);
      else{
         cGUI.appendText(msg,type);
      }
   }

   /* Logout and Disconnect */
   public void logOut(){
      Message msg=new Message(enum_MessageType.LOGOUT, userName);
      sendMessage(msg);
      disconnect();
   }
   public void disconnect(){
      try{
         if(sInput!=null)
            sInput.close();
      }
      catch(Exception e){}
      try{
         if(sOutput!=null)
            sOutput.close();
      }
      catch(Exception e){}
      try{
         if(socket!=null)
            socket.close();
      }
      catch(Exception e){}
   }

   private boolean handleMessage(Message msg){
      enum_MessageType type=msg.getType();
      String sender=msg.getSender();
      String receiver=msg.getReceiver();
      String message=msg.getMessage();
      int p_room=msg.getRoom();
      boolean broadcast=msg.getBroadcast();
      int iconIndex=msg.getIconIndex();
      String text="";
      // string type
      String textType="regular";
      if(sender.equals("SERVER")) textType="bold";
      // private message
      if(!broadcast && !userName.equals(receiver) && !userName.equals(sender) ) return false;
      if(!broadcast) text=" [PRIVATE] ";
      // header
      if( !receiver.equals("All") && !receiver.equals("") ) text=text+sender+" --> "+receiver+": ";
      else text=text+sender+": ";
      // if console mode print the message and add back the prompt
      if(cGUI == null) {
         System.out.println(sender+": "+msg.getMessage());
         System.out.print("> ");
      }
      else {
         switch(type){
            case RENEWROOM:
               cGUI.renewRoom(p_room);
               break;
            case ANNOUNCE:
            case TEXT:
               text=text+message;
               display(text,textType); 
               break;
            case ICON:
               display(text,textType);
               cGUI.appendIcon(iconIndex); 
               break;
            case LIST:
               Vector<String> list=msg.getUserList();
               cGUI.setUserList( list );
               break;
            case CREATROOM:
               if( !sender.equals(userName) && receiver.equals(userName) ) cGUI.setRoom(p_room);
               break; 
            case IMAGE:
               try{
               String filePath = "downloads/image/"+msg.getFileName();
               File file = new File(filePath);
               if(!file.exists()){
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(filePath);
                    fos.write(msg.getFile());
                    fos.close();
                    display("You have a image:"+msg.getFileName(),"italic");
					cGUI.appendImage(filePath);
               }
//               cGUI.appendImage();
               }
               catch(IOException eIO){
               display("Error getting a image" +  eIO);
               }
               break;
            case FILE:
               try{
               String filePath2 = "downloads/"+msg.getFileName();
               File file2 = new File(filePath2);
               if(!file2.exists()){
                    file2.createNewFile();
                    FileOutputStream fos2 = new FileOutputStream(filePath2);
                    fos2.write(msg.getFile());
                    fos2.close();
                    display("You have a file:"+msg.getFileName(),"regular");
                }
               }
               catch(IOException eIO){
               display("Error getting a file" +  eIO);
               }
               break;
			default:
			   display("Default","regular");
			   break;
         }
      }
      return true;
   }// end of function handle message
   /* Class ListenFromServer */
   class ListenFromServer extends Thread {

      public void run() {
         while(true) {
            try {
               Message msg = (Message) sInput.readObject();
               handleMessage(msg);
            }
            catch(IOException e){
               display("Server has close the connection: " + e,"bold");
               if(cGUI != null){}
               break;
            }
            // can't happen with a String object but need the catch anyhow
            catch(ClassNotFoundException e2) {}
         }
      }
   } // end of class ListenFromServer
}

