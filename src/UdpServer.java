//แบ่ง packgate แยกทีละอันละมารวมกัน

import java.net.*;
import java.io.*;

public class UdpServer {

    private DatagramSocket ds;
    private DatagramPacket dpIn, dpOut;
    private InetAddress client;//ที่อยู่ผู้ส่ง
    private int clientPort;//ต้องรู้ port ด้วย
    private String msgIn, msgOut;
    private byte[] buffer;//เป็น area ของ byte 

    public UdpServer() {
        try {
            //open port step 1
            ds = new DatagramSocket(8888);
            //step 2 create Buffer
            buffer = new byte[256];
            //step 3 create datagram packet
            dpIn = new DatagramPacket(buffer, buffer.length);//เซอร์เวอร์ต้องรับ packgate มาก่อนนะ
            while (true) {
                //step 4 receive packet รอรับแพ๊คเกต client 
                ds.receive(dpIn);
                //step 5 extract message
                msgIn = new String(dpIn.getData(), 0, dpIn.getLength());
                //step 6 prepaie packet for client
                client = dpIn.getAddress();
                clientPort = dpIn.getPort();
                msgOut = msgIn;
                dpOut = new DatagramPacket(msgOut.getBytes(), msgOut.length(), client, clientPort);

                //STEP 7 SEND PACKET
                ds.send(dpOut);//สร้างท่อเสมือน 
            }
            //step 8 close
           // ds.close();
        } catch (SocketException se) {
            System.out.println(se.getMessage());
            System.out.println("Socket error");
            System.exit(-1);
        } catch (IOException ioe) {

        }
    }

    public static void main(String[] args) {
        new UdpServer();
    }
}
