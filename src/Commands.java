import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

public class Commands extends Thread {
    Commands() {
    }
    public static String getPossibleCommands() {
        String mes = Arrays.toString(Command.values());
        return mes;
    }

    public String doCommand(String line) throws IOException {
        String message="";
        super.run();
        String number = "";
        String name = "";
        String path = "";
        while (message.length() <= 0) {
            System.out.println(line);
            if (line.equals(Command.start.getCom())) {
                Story.go();
                message="История запущенна";
            } /*else if (line.equals(Command.add_if_min.getCom())) {
                while (name.equals("")) {
                    god.sendMessage("Введите имя");
                    try {
                        name = god.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                while (number.equals("")) {
                    god.sendMessage("Присвойте номер");
                    try {
                        number = god.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Prison.add_if_min(number, name);
                message="Добавлен заключенный "+name+" под номером "+number;
            } */else if (line.equals(Command.clear.getCom())) {
                Prison.clear();
                message="Тюрьма опустела";
            } else if (line.equals(Command._import.getCom())) {
                while (path.equals("")) {
                    God.sendMessage("Укажите адресс файла");
                    try {
                        path = God.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Prison._import(path);
                message="Привезли заключенных";
            } else if (line.equals(Command.show.getCom())) {
                Prison.show();
            } else if (line.equals(Command.add.getCom())) {
                while (name.equals("")) {
                    God.sendMessage("Введите имя");
                    try {
                        name = God.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                while (number.equals("")) {
                    God.sendMessage("Присвойте номер");
                    try {
                        number = God.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Prison.add(number, name);
                name = "";
                number = "";
                message="Вы посадили "+ name+". Ему присвоен номер "+number;
            } else if (line.equals(Command.remove.getCom())) {
                while (name.equals("")) {
                    God.sendMessage("Введите имя");
                    try {
                        name = God.getResivedMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Prison.remove(name);
                message="Заключенный "+name+" оправдан";
            } else if (line.equals(Command.info.getCom())) {
                Prison.info();
            } else if (line.equals(Command.stop.getCom())) {
                try {
                    Story.end();
                    Thread.interrupted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message="Неизвестная команда";
            }
        }
        return message;
    }
}
    enum Command{
        start("start"),all("commands"),add_if_min("add_if_min"),
        clear("clear"),_import("import"),show("show"),add("add"),
        remove("remove"),info("info"),stop("stop");
        private String com;
        Command(String com) {
            this.com=com;
        }
        public String getCom() {
            return com;
        }
    }

