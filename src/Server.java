import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server implements Runnable{
    DatagramChannel channel = null;
    public static void main(String[] args)throws IOException {
        Thread server=new Thread();
        server.start();
        server.run();
        System.out.println(13);
    }
    @Override
    public void run() {
        System.out.println(17);
        while (true) {
            System.out.println(18);
            try {
                channel = DatagramChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(25);
                channel.socket().bind(new InetSocketAddress(9100));
            } catch (SocketException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(31);
                while (!getMessage().equals("true")) {
                    sendMessage("true");
                }
            } catch (IOException e) {
                System.out.println("Ошибка подключения");
            }
        }
    }
    public void sendMessage(String message) throws IOException{
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(message.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress("localhost", 9100));
    }
    public String getMessage() throws IOException{
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);
        String s=buf.array().toString();
        return s;
    }


}
