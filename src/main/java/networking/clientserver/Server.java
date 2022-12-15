package networking.clientserver;

/* SERVER – may enhance to work for multiple clients */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<ClientConnected> connectedClient;
    public static void main(String [] args) {

        ServerSocket server = null;
        Socket client;

        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        // Create Server side socket
        try {
            server = new ServerSocket(portnumber);
            connectedClient = new ArrayList<ClientConnected>();
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket is created " + server);

        // Wait for the data from the client and reply
        while(true) {

            try {

                // Listens for a connection to be made to
                // this socket and accepts it. The method blocks until
                // a connection is made
                System.out.println("Waiting for connect request...");
                while (true) {
                    client = server.accept();
                    /* Create a thread to do the communication, and start it */
                    new Handler(client).start();
                }




            } catch (IOException ie) {
                System.err.println("Could not accept " + ie);
                System.exit(1);
            }
        }
    }
}
