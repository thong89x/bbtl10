package networking.clientserver;

/* CLIENT */
import java.io.*;
import java.net.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Client {
    public static SocketController socketController;

    public static void main(String args[]) throws IOException {

//        Socket client = null;
        String name = new String("");
        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }
//        client = new Socket(InetAddress.getLocalHost(), portnumber);
        ServerData sv = new ServerData("Niga",InetAddress.getLocalHost(), portnumber);
        socketController = new SocketController("Thong",sv);
        socketController.run();

//        for (int i=0; i <10; i++) {
//            try {
//                String msg = "";
//
//                // Create a client socket
//
//
//
//                // Create an output stream of the client socket
//                OutputStream clientOut = client.getOutputStream();
//                PrintWriter pw = new PrintWriter(clientOut, true);
//
//                // Create an input stream of the client socket
//                InputStream clientIn = client.getInputStream();
//                BufferedReader br = new BufferedReader(new
//                        InputStreamReader(clientIn));
//
//                // Create BufferedReader for a standard input
//                BufferedReader stdIn = new BufferedReader(new
//                        InputStreamReader(System.in));
//                if(name.equals("")){
//                    System.out.println("Enter your name. Type Bye to exit. ");
//
//                    // Read data from standard input device and write it
//                    // to the output stream of the client socket.
//                    msg = stdIn.readLine().trim();
//                    String msgClient [] = msg.split(" ");
//                    name = msg;
//                    if (msgClient[0].equalsIgnoreCase("Hello,")&& msgClient[1].equalsIgnoreCase("my")
//                            && msgClient[2].equalsIgnoreCase("name") && msgClient[3].equalsIgnoreCase("is"))
//                    {
//                        name = msgClient[4];
//                    }
//                    pw.println(msg);
//                    // Read data from the input stream of the client socket.
//                    System.out.println("Message returned from the server = " + br.readLine());
//
//                    pw.close();
//                    br.close();
////                    client.close();
//                }
//                else {
//                    System.out.println("Enter your Message. Type Bye to exit. ");
//
//                    // Read data from standard input device and write it
//                    // to the output stream of the client socket.
//                    msg = name+": " + stdIn.readLine().trim();
//                    pw.println(msg);
//                    // Read data from the input stream of the client socket.
//                    System.out.println("Message returned from the server = " + br.readLine());
//
//                    pw.close();
//                    br.close();
////                    client.close();
//                }
//
//
//
//                // Stop the operation
//                if (msg.equalsIgnoreCase("Bye")) {
//                    break;
//                }
//
//            } catch (IOException ie) {
//                System.out.println("I/O error " + ie);
//                client.close();
//            }
//        }
    }
}


