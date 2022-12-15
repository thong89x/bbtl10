package org.example;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static int serverPort = 998;
    public static int clientPort = 999;
    public static int buffer_size = 1024;
    public static DatagramSocket ds;
    public static byte buffer[] = new byte[buffer_size];
    public static void TheServer() throws Exception {
        int pos = 0;
        while (true) {
            int c = System.in.read();
            switch (c) {
                case -1: System.out.println("Server Quits.");
                    ds.close(); return;
                case '\r': break;
                case '\n':
                    ds.send(new DatagramPacket(buffer, pos,
                            InetAddress.getLocalHost(), clientPort));
                    pos = 0; break;
                default: buffer[pos++] = (byte) c;
            }
        }
    }
    public static void TheClient() throws Exception {
        while (true) {
            DatagramPacket p = new DatagramPacket(buffer,
                    buffer.length);
            ds.receive(p);
            System.out.println(new String(p.getData(), 0,
                    p.getLength()));
        }
    }
    public static void main(String[] args) throws Exception {
        ds = new DatagramSocket(clientPort);
        TheClient();
    }
}