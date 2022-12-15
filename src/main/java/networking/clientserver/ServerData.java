package networking.clientserver;

import java.net.InetAddress;

public class ServerData {
    public String nickName;
    public String realName;
    public InetAddress ip;
    public int port;
    public boolean isOpen;
    public int connectAccountCount;

    public ServerData(String name, InetAddress ip, int port) {
        this.nickName = name;
        this.realName = "";
        this.ip = ip;
        this.port = port;
        this.isOpen = false;
        this.connectAccountCount = 0;
    }


}
