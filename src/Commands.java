import java.util.Arrays;

public class Commands extends Thread{
    Commands(){
        Thread client=new Thread();
    }
    public static String getPossibleCommands() {
        String mes = Arrays.toString(Command.values());
        return mes;
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

