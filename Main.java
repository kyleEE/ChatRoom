import java.net.*;
import java.io.*;
import java.util.*;

public class Main{
   public static void main(String[] args){
      int portNumber=5678;
      String serverAddress="140.112.18.202";
      String userName="Anonymous";
      switch(args.length){
         case 3:
            serverAddress=args[2];
         case 2:
            try{
               portNumber=Integer.parseInt(args[1]);
            }
            catch(Exception e){
               System.out.println("Invalid port number");
               System.out.println("Usage is: >java Client [username] [portNumber] [serverAddress]");
               return;
            }
         case 1:
            userName=args[0];
            break;
         default:
            System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
            return;
      }
      Client client=new Client(serverAddress, portNumber, userName);
      if(!client.start())
         return;
      Scanner scan=new Scanner(System.in);
      while (true){
         System.out.print("> ");
         // read message from user
         String message= scan.nextLine();
         Message msgObj=new Message(enum_MessageType.TEXT, userName, message);
         // logout if message is LOGOUT
         if(message.equalsIgnoreCase("LOGOUT")) {
            client.sendMessage(new Message(enum_MessageType.LOGOUT, userName, message));
            break; // break to do the disconnect
         }
         else {}            // default to ordinary message
         client.sendMessage(msgObj);
      } // end of while
      client.disconnect();
   }// end of method main()
}
