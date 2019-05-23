/*import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Story {
    static DatagramChannel server;

    {
        try {
            server = DatagramChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    InetSocketAddress iAddr = new InetSocketAddress(9100);
    static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException{
        System.out.println("Сервер запущен");
        Story story=new Story();
        story.run();

    public static go() {
        System.out.println("GO");
    }

    public static boolean end() throws IOException {
        Prison.writeToFile();
        System.exit(0);
        return false;
    }

    public static String getMessage() throws IOException {
        buffer.clear();
        SocketAddress remoteAddr = server.receive(buffer);
        String mes = new String(buffer.array());
        return mes;
    }

    public static void sendMessage(String s) throws IOException {
        //SocketAddress remoteAddr = server.receive(buffer);
        for (int i = 0; i < s.length(); i++) {
            buffer.put((byte) s.charAt(i));
        }
        buffer.flip();
        server.send(buffer, remoteAddr);
    }
    @Override
 public void run(){
        try {
            server.bind(iAddr);
        } catch (IOException e) {
            System.out.println("Не удается запустить сервер");
        }
        while(true){
            try {
                if (!getMessage().equals("succsess")) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sendMessage("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            try {
                //System.out.println(getMessage());
                doCommand(getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static String getName() throws IOException {
        String name = "";
        sendMessage("Введите имя");
        while (name.equals("")) {
            name = getMessage();
        }
        return name;
    }

    static String getNumber() throws IOException {
        String number = "";
        sendMessage("Присвойте номер");
        while (number.equals("")) {
            number = getMessage();
        }
        return number;
    }

    static String getPath() throws IOException {
        String path = "";
        sendMessage("Укажите адресс файла");
        while (path.equals("")) {
            path = getMessage();
        }
        return path;
    }

    public static String doCommand(String command) throws IOException {
        String comm;
        switch (command) {
            case "commands":
                comm = Command.all.getCom();
                sendMessage("Доступные команды: " + Commands.getPossibleCommands());
                break;
            case "add":
                Prison.add(getNumber(), getName());
                comm = Command.add.getCom();
                break;
            case "add if min":
                Prison.add_if_min(getNumber(), getName());
                comm = Command.add_if_min.getCom();
                break;
            case "show":
                comm = Command.show.getCom();
                Prison.show();
                break;
            case "remove":
                comm = Command.remove.getCom();
                Prison.remove(getName());
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
}
*/