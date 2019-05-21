import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class God extends Thread implements Runnable{
    public static void main(String[] args) throws SocketException {
        God server=new God();
        server.start();
    }
    private static DatagramSocket socket;
    private static byte[] buf = new byte[256];
    private static InetAddress address;
    private static int port;
    public God() throws SocketException {
        socket = new DatagramSocket(4445);
    }
    public void run() {
        boolean running = true;
        while (running) {
            buf=new byte[256];
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            address = packet.getAddress();
            port = packet.getPort();
            //packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());
            Commands command=new Commands();
            try {
                sendMessage(command.doCommand(received));
                received=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (received.equals("end")) {
                System.out.println(1);
                try {
                    sendMessage("Введено end");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("Rs mes: "+getResivedMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                running = false;
            }
        }
        socket.close();
    }
    public static String getResivedMessage() throws IOException{
        buf=new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        String resived="";
        while (resived.equals("")){
            socket.receive(packet);
            resived= new String(packet.getData(), 0, packet.getLength());
        }
        return resived;
    }
    public static void sendMessage(String message) throws IOException {
        buf=message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
        buf= new byte[256];
    }

}