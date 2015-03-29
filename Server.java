import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server{
   private Vector<ClientThread> clientThreadVec;
   //private ServerGUI gui;
   private SimpleDateFormat time;
   private int port;
   private boolean terminate;
   private int num_client;
   private ServerSocket serverSocket;

   public Server(){
      port=5678;
      time=new SimpleDateFormat("HH:mm:ss");
      terminate=false;
      num_client=0;
      clientThreadVec=new Vector<ClientThread>();
   }

   public static void main(String[] args){
      Server server=new Server();
      server.start();
   }
	
   public void start(){
      try{
         serverSocket=new ServerSocket(port);
         while(!terminate){
            display("Server waiting for Clients on port "+ port + "...");
            Socket socket=serverSocket.accept();
            if(terminate) break;
            ClientThread ct=new ClientThread(socket);
            clientThreadVec.add(ct);
            ct.start();
         }
         try{
            serverSocket.close();
            for(int i=0; i<clientThreadVec.size(); i++){
               ClientThread ct=clientThreadVec.get(i);
               try{
                  ct.sInput.close();
                  ct.sOutput.close();
                  ct.socket.close();
               }
               catch(IOException ioE){}
            }
         }
         catch(Exception e){
            display("Exception closing the server and clients: "+e);
         }
      }
      catch(IOException e){
         display("Exception on new ServerSocket: "+e+"\n");
      }
   } // end of method start()

   private void display(String message){
      String currTime="["+time.format(new Date())+"]  "+message;
      System.out.println(currTime);
   }
   
   /* Try to stop */
   private void stop(){
      try{
         serverSocket.close();
	 for(int i=0; i<clientThreadVec.size(); i++){
	    ClientThread ct=clientThreadVec.get(i);
	    try{
	       ct.sInput.close();
	       ct.sOutput.close();
	       ct.socket.close();
	    }
	    catch(IOException ioE){
	       display("Exception closing ClientThread: "+ioE);
	    }
	 }
      }
      catch(Exception e){
         display("Exception closing ServerSocket: "+e);
      }
   }
   
   /* Kick a user out */
   private synchronized boolean kickUser(int id){
      for(int i=0; i<clientThreadVec.size(); i++){
         ClientThread ct=clientThreadVec.get(i);
	 if(ct.id==id){
	    clientThreadVec.remove(i);
	    return true;
	 }
      }
      return false;
   }

   private synchronized void broadcast(Message msg){
      for(int i=0; i<clientThreadVec.size(); i++){
         ClientThread ct=clientThreadVec.get(i);
         if(msg.getSender().equals(ct.getUsername())) continue;
         if(!ct.writeMessage(msg)){
            clientThreadVec.remove(i);
            display("Disconnect Client "+ct.username+" removed from list.");
         }
      }
   }

   class ClientThread extends Thread{
      private Socket socket;
      private ObjectInputStream sInput;
      private ObjectOutputStream sOutput;
      private int id;
      private String username;
      private String time;
      private Message msg;

      /* Getter */
      public String getUsername(){ return username;}

      /* Constructor */
      public ClientThread(Socket socket){
	 id=num_client;
	 num_client++;
	 this.socket=socket;
	 System.out.println("Thread trying to create Object I/O Streams: ");
	 try{
	    sOutput=new ObjectOutputStream(socket.getOutputStream());
	    sInput=new ObjectInputStream(socket.getInputStream());
	    username=(String)sInput.readObject();
	    display(username+" just connected.");
	 }
	 catch(IOException ioE){
	    display("Exception creating new I/O Streams: " + ioE);
	    return;
	 }
	 catch(ClassNotFoundException cnfE){}
	 time=new Date().toString()+"\n";
      } // end of constructor

      public void run(){
         boolean terminate=false;
         while(!terminate){
            try{
               msg=(Message) sInput.readObject();
            }
            catch(IOException e){
               display(username+" Exception reading Streams: "+e);
               break;
            }
            catch(ClassNotFoundException e){}
            enum_MessageType type=msg.getType();
            switch(type){
               case LOGOUT:
                  display(username+" disconnected with LOGOUT message.");
                  terminate=true;
                  break;
               case TEXT:
               case ICON:
               case PICTURE:
                  broadcast(msg);
                  break;
               case CREATROOM:
                  break;
            } // end of switch
         } // end of while
         kickUser(id);
         close();
      } // end of method run()
      
      private void close(){
         try{
            if(sOutput!=null) sOutput.close();
         }
         catch(Exception e){}
         try{
            if(sInput!=null) sInput.close();
         }
         catch(Exception e){}
         try{
            if(socket!=null) socket.close();
         }
         catch(Exception e){}
      } // end of method close()

      private boolean writeMessage(Message msg){
         if(!socket.isConnected()){
            close();
            return false;
         }
         try{
            sOutput.writeObject(msg);
         }
         catch(IOException e){
            display("Error sending message to "+username);
            display(e.toString());
         }
         return true;
      }
   } // end of inner class ClientThread
}
