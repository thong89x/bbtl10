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
            client.sender = new BufferedWriter(new OutputStreamWriter(os));
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
                String msgFromClient = client.receiver.readLine();
                if (msgFromClient == null)
                    throw new IOException();
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
                        ansMsg ="Hello" +name;
                        client.sender.write(ansMsg);
                        client.sender.newLine();
                        String msg = client.userName + " has joined ";
                        sendAll(msg);
                    }
                    for (ClientConnected connectedClient : Server.connectedClient) {
                        System.out.println("Check Exit");
                        System.out.println(connectedClient.userName);
                        System.out.println(client.userName);
                        if (connectedClient.userName.equals(client.userName )) {
                            userNameExisted = true;
                            break;
                        }
                    }

                    if(!userNameExisted){

                        client.sender.write("Pls input your name according to the syntax 'Hello, my name is <name>'");
                        client.sender.newLine();
                        client.sender.flush();


                    }
                    else {
                        for (ClientConnected user : Server.connectedClient) {
                            System.out.println("Send text from " + client.userName );
                            String msg = client.userName + ": "+msgFromClient;
                            sendAll(msg);
                        }
                    }
                }

                // Close sockets
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("Goodbye")) {
                    String ansMsg = "Good bye";
                    client.sender.write(ansMsg);
                    client.sender.newLine();
                    client.socket.close();
                    System.out.println("Socket ENDED: " + client.socket);
                    return;
                }
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    client.socket.close();
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
            user.sender.write(message);
            user.sender.newLine();
//            user.sender.write('\0');
            user.sender.flush();
        }
    }
}