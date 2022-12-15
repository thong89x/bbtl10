package networking.clientserver;

import java.io.*;
import java.net.Socket;

class Handler extends Thread {
    ClientConnected client;
    Handler(Socket s) {
        try {
            client = new ClientConnected();
            client.socket = s;
            OutputStream os = s.getOutputStream();
            client.sender = new PrintWriter(os, true);

            InputStream is = s.getInputStream();
            client.receiver = new BufferedReader(new InputStreamReader(is));
            client.port = s.getPort();
        } catch (IOException e) {

        }
    }
    public void run() {
        System.out.println("Connect request is accepted...");
        String clientHost = client.socket.getInetAddress().getHostAddress();
        int clientPort = client.socket.getPort();
        System.out.println("Client host = " + clientHost + " Client port = " + clientPort);


        try {
            while (true) {
                InputStream clientIn = client.socket.getInputStream();
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(clientIn));
                System.out.println("Doi tin nhan");
                String msgFromClient = br.readLine();

//                String msgFromClient = client.receiver.readLine();
                /*if (msgFromClient == null)
                    throw new IOException();*/
                System.out.println("Nhan tin");
                System.out.println("Message received from client = " + msgFromClient);

                if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye")) {

                    String ansMsg = "Hello, " + msgFromClient;
                    String msgClient [] = msgFromClient.split(" ");
                    boolean userNameExisted = false;

                    if (msgClient[0].equalsIgnoreCase("Hello,")&& msgClient[1].equalsIgnoreCase("my")
                            && msgClient[2].equalsIgnoreCase("name") && msgClient[3].equalsIgnoreCase("is"))
                    {
                        System.out.println("Input Name");
                        String name = msgClient[4];
                        client.userName = name;
                        Server.connectedClient.add(client);
                        ansMsg ="Hello " +name;
                        client.sender.println(ansMsg);

                    }
                    for (ClientConnected connectedClient : Server.connectedClient) {
                        System.out.println("Check Exist");
                        System.out.println(connectedClient.userName);
                        System.out.println(client.userName);
                        if (connectedClient.userName.equals(client.userName )) {
                            userNameExisted = true;
                            break;
                        }
                    }

                    if(!userNameExisted){
                        client.sender.println("Pls input your name according to the syntax 'Hello, my name is <name>'");
                    }
                    else {
                        String msg = client.userName + " has joined ";
                        sendAll(msg);
                    }
                }

                // Close sockets
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("Goodbye")) {
                    String ansMsg = "Good bye";
                    client.sender.println(ansMsg);
                    client.socket.close();
                    Server.connectedClient.remove(client);
                    System.out.println("Socket ENDED: " + client.socket);
                    return;
                }
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    client.socket.close();
                    Server.connectedClient.remove(client);
                    System.out.println("Socket ENDED: " + client.socket);
                    return;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void sendAll(String message) throws IOException {
        for (ClientConnected user : Server.connectedClient) {
            System.out.println("Send text from " + client.userName );
            user.sender.println(message);
//            user.sender.write('\0');
        }
    }
}