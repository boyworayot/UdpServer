
import java.net.*;
import java.io.*;

public class UdpServer {
    private DatagramSocket ds;
    private DatagramPacket dpIn,pdOut;
    public UdpServer() {

    }

    public static void main(String[] args) {
        new UdpServer();
    }
}
