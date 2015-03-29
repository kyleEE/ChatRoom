import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
   private ObjectInputStream sInput;
   private ObjectOutputStream sOutput;
   private Socket socket;
   private ClientGUI cGUI;
   private String server,userName;
   private int port;
   
   /* Constructor */
   Client(){
      server="140.112.18.202";
      port=5678;
      userName="Anonymous";
      cGUI=null;
   }

   Client(String s,int p,String u,ClientGUI cg){
      server=s;
      port=p;
      userName=u;
      cGUI=cg;
   }

   Client(String s,int p,String u){
      server=s;
      port=p;
      userName=u;
      cGUI=null;
   }

   /* Getter */
   public int getPort(){ return port;}
   public String getServer() { return server;}
   public String getUserName() { return userName;}
   
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
   
   /* Login to the server */
   public boolean start(){
      display("User "+userName+" trying to login to "+server+" at port "+port+"...");
      // new socket
      try{
         socket=new Socket(server,port);
      }
      catch(Exception ec){
         display("Error connecting to server: "+ec);
         return false;
      }
      String message="Connection accepted " + socket.getInetAddress()+ " "+socket.getPort();
      display(message);
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
   public void sendText(String text){
      Message msg=new Message(enum_MessageType.TEXT,userName,text);
      sendMessage(msg);
   }

   public void sendIcon(int index){
      Message msg=new Message(enum_MessageType.ICON,userName);
      msg.setIconIndex(index);
      sendMessage(msg);
   }

   /* Send Message */
   public void sendMessage(Message msg){
      try{
         sOutput.writeObject(msg);
      }
      catch(IOException e){
         display("Error writing to server" +  e);
      }
   }

   private void display(String msg){
      if(cGUI==null)
         System.out.println(msg);
      else{
         cGUI.appendText(msg);
      }
   }

   /* Disconnect */
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
      if(cGUI!=null); //need to discussion
         //cGUI.connectionFailed();
   }

   private void handleMessage(Message msg){
      enum_MessageType type=msg.getType();
      String sender=msg.getSender();
      String receiver=msg.getReceiver();
      String message=msg.getMessage();
      int room=msg.getRoom();
      boolean broadcast=msg.getBroadcast();
      int iconIndex=msg.getIconIndex();
      String text;
      // header
      if( !receiver.equals("") ) text=sender+" --> "+receiver+": ";
      else text=sender+": ";
      
      // if console mode print the message and add back the prompt
      if(cGUI == null) {
         System.out.println(sender+": "+msg.getMessage());
         System.out.print("> ");
      }
      else {
         switch(type){
            case TEXT:
               text=text+msg;
               display(text); 
               break;
            case ICON:
               display(text);
               cGUI.appendIcon(iconIndex);
               break;
         }
      }
   }
   /* Class ListenFromServer */
   class ListenFromServer extends Thread {

      public void run() {
         while(true) {
            try {
               Message msg = (Message) sInput.readObject();
               handleMessage(msg);
            }
            catch(IOException e){
               display("Server has close the connection: " + e);
               if(cGUI != null){}
               break;
            }
            // can't happen with a String object but need the catch anyhow
            catch(ClassNotFoundException e2) {}
         }
      }
   } // end of class ListenFromServer
}

