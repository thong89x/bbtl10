package networking.clientserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.List;

public class ClientConnected {
    public String userName;
    public int port;
    public Socket socket;
    public BufferedReader receiver;
    public BufferedWriter sender;

    public ClientConnected(String userName, int port, Socket socket, BufferedReader receiver, BufferedWriter sender) {
        this.userName = userName;
        this.port = port;
        this.socket = socket;
        this.receiver = receiver;
        this.sender = sender;
    }

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