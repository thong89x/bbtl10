package networking.clientserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientConnected {
    public String userName;
    public int port;
    public Socket socket;
    public BufferedReader receiver;
    public PrintWriter sender;

    public ClientConnected() {
    }

//    public static ClientConnected findClient(List<ClientConnected> clientList, String userName) {
//        for (ClientConnected client : clientList) {
//            if (client.userName.equals(userName))
//                return client;
//        }
//        return null;
//    }
}