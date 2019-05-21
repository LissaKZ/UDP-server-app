import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Servant extends Thread{
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;

    public static void main(String[] args) throws SocketException, UnknownHostException {
        Servant servant=new Servant();
        Runnable output= () -> {
            while (true) try {
                System.out.println(servant.receiveEcho());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Runnable input= () -> {
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            while (true) try {
                servant.sendEcho(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread Sender=new Thread(output);
        Thread Reader=new Thread(input);
        Sender.start();
        Reader.start();
    }
    private Servant() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    private void sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
    }
    private String receiveEcho() throws IOException{
        buf=new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(
                packet.getData(), 0, packet.getLength());
    }
}
