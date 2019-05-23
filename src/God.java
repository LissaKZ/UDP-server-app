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
    static boolean stat=false;
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
            try {
               doCommand(received);
            } catch (IOException e) {
                System.out.println("Не удалось выполнить команду");
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
    public String doCommand(String command) throws IOException {
        String comm;
        switch (command) {
            case "commands":
                comm = Command.all.getCom();
                sendMessage("Доступные команды: " + Commands.getPossibleCommands());
                break;
            case "add":
                Prison.add(getNumber(), getNameOf());
                comm = Command.add.getCom();
                break;
            case "add if min":
                Prison.add_if_min(getNumber(), getNameOf());
                comm = Command.add_if_min.getCom();
                break;
            case "show":
                comm = Command.show.getCom();
                Prison.show();
                break;
            case "remove":
                comm = Command.remove.getCom();
                Prison.remove(getNameOf());
                break;
            case "info":
                comm = Command.info.getCom();
                Prison.info();
                break;
            case "stop":
                end();
                comm = Command.stop.getCom();
                break;
            case "import":
                Prison._import(getPath());
                comm = Command._import.getCom();
                break;
            case "clear":
                Prison.clear();
                comm = Command.clear.getCom();
                break;
            case "start":
                go();
                comm = Command.start.getCom();
                break;
            default:
                comm = "Неизвестная команда";
                sendMessage(comm);
                break;
        }
        return comm;
    }
    public String getNameOf(){
        String name = "";
        try {
            sendMessage("Введите имя");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (name.equals("")) {
            try {
                name = getResivedMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    static String getNumber() throws IOException {
        String number = "";
        sendMessage("Присвойте номер");
        while (number.equals("")) {
            number = getResivedMessage();
        }
        return number;
    }

    static String getPath() throws IOException {
        String path = "";
        sendMessage("Укажите адресс файла");
        while (path.equals("")) {
            path = getResivedMessage();
        }
        return path;
    }
    public static void go() {
        stat=true;
        System.out.println("GO");
    }

    public static boolean end() throws IOException {
        Prison.writeToFile();
        System.exit(0);
        return false;
    }
}