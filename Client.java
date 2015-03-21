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
   //public void setPort(int p){ port=p;}
   //public void setServer(String u){ server=u;}
   //public void setUserName(String user) { userName=user;}
   public void setGUI(ClientGUI cg){ cGUI=cg;}
   
   public boolean start(){
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
         // TODO
         // cGUI.append(msg+"\n");
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

   /* Class ListenFromServer */
   class ListenFromServer extends Thread {

      public void run() {
         while(true) {
            try {
               Message msg = (Message) sInput.readObject();
               enum_MessageType type=msg.getType();
               String sender=msg.getSender();
               int room=msg.getRoom();
               // if console mode print the message and add back the prompt
               if(cGUI == null) {
                  System.out.println(sender+": "+msg.getMessage());
                  System.out.print("> ");
               }
               else {//need disccussion
                  //cGUI.append(msg);
               }
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

