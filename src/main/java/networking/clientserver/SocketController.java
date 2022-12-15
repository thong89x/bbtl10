package networking.clientserver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketController {

    String userName;
    ServerData connectedServer;
    Socket s;
    BufferedReader receiver;
    PrintWriter sender;

    Thread receiveAndProcessThread;



    public SocketController(String name, ServerData connectedServer) {

        try {
            this.userName = name;
            this.connectedServer = connectedServer;
            s = new Socket(connectedServer.ip, connectedServer.port);
            InputStream is = s.getInputStream();
            receiver = new BufferedReader(new InputStreamReader(is));
            OutputStream os = s.getOutputStream();
            sender = new PrintWriter(os, true);
            System.out.println("Client socket is created " + s);
        } catch (IOException e1) {
//            Client.connectServerScreen.loginResultAction("closed");
        }
    }

    public void run() {

        System.out.println("Send simple  msg");
        sender.println("Hello, my name is "+userName);
        System.out.println("Nhan simple  msg");

        receiveAndProcessThread = new Thread(() -> {
        try {
            while (true) {

                System.out.println("Read msg");
                String msg = receiver.readLine();
                System.out.println("Msg: " + msg);
                if (msg == null)
                    throw new IOException();
                System.out.println(msg);
            }
        } catch (IOException e) {
//                        JOptionPane.showMessageDialog(Main.mainScreen, "Server đã đóng, ứng dụng sẽ thoát", "Thông báo",
//                                JOptionPane.INFORMATION_MESSAGE);
                try {
                    Client.socketController.s.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.exit(0);
            }

        });
        receiveAndProcessThread.start();

    }

//    public void sendTextToRoom(int roomID, String content) {
//        try {
//            sender.write("text to room");
//
//            sender.write("" + roomID);
//            sender.newLine();
//            sender.write(content);
//            sender.write('\0');
//            sender.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean serverOnline(String ip, int port) {
//        try {
//            Socket s = new Socket();
//            s.connect(new InetSocketAddress(ip, port), 300);
//            s.close();
//            return true;
//        } catch (IOException ex) {
//            return false;
//        }
//    }
//
//    public static String serverName(String ip, int port) {
//
//        if (!serverOnline(ip, port))
//            return "";
//
//        try {
//            Socket s = new Socket(ip, port);
//            InputStream is = s.getInputStream();
//            BufferedReader receiver = new BufferedReader(new InputStreamReader(is));
//            OutputStream os = s.getOutputStream();
//            BufferedWriter sender = new BufferedWriter(new OutputStreamWriter(os));
//
////            sender.write("get name");
////            sender.newLine();
////            sender.flush();
//
//            String name = receiver.readLine();
//
//            s.close();
//            return name;
//        } catch (IOException ex) {
//            return "";
//        }
//    }
//
//    public static int serverConnectedAccountCount(String ip, int port) {
//        try {
//            Socket s = new Socket(ip, port);
//            InputStream is = s.getInputStream();
//            BufferedReader receiver = new BufferedReader(new InputStreamReader(is));
//            OutputStream os = s.getOutputStream();
//            BufferedWriter sender = new BufferedWriter(new OutputStreamWriter(os));
//
//            sender.write("get connected count");
//            sender.newLine();
//            sender.flush();
//
//            int count = Integer.parseInt(receiver.readLine());
//
//            s.close();
//            return count;
//        } catch (IOException ex) {
//            return 0;
//        }
//    }
}
