import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Prison implements Serializable{
    static File file=new File("C:\\Users\\Alissa\\Desktop\\zz.csv");
    static CopyOnWriteArraySet<String> prisoners = new CopyOnWriteArraySet<>();
    static Date date_of_initialization = new Date();
    public static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();


    static void add_if_min(String number, String name) {
        Prisoner p = new Prisoner(number, name);
        if (!prisoners.contains(p)) {
            Prison.add(number, name);
        } else {
            System.out.println("Заключенный не добавлен");
        }
    }

    static void clear() {
        prisoners.clear();
    }

    static void _import(String path) {
        File file = new File(path);
        Prison.file=file;
        if (file.exists()) {
            String s = Prison.readFile(file);
            ArrayList<String> list = new ArrayList<>();
            String k = "";
            System.out.println(s);
            for (int i = 0; i < s.length(); i++) {
                int c = Integer.valueOf(s.charAt(i));
                System.out.println(c);
                if (c == 59|| c < 40) {
                    if (k != "") {
                        list.add(k);
                    }
                    k = "";
                } else {
                    k += s.charAt(i);
                }
            }
            for (int i = 0; i < list.size()-1; i += 2) {
                Prison.add(list.get(i), list.get(i + 1));
            }
        }
        else{
                System.out.println("Файл не существует");
            }
    }

    static String show() {
        System.out.println(prisoners);
        return prisoners.toString();
    }

    static void add(String name, String number) {
        Prisoner prisoner=new Prisoner(number,name);
        System.out.println("prison 65");
        String json=GSON.toJson(prisoner);
        System.out.println("2");
        prisoners.add(json);
    }

    static String info() {
        return "LinkedHashSet; " + date_of_initialization + "; " + prisoners.size() + " elements;";
    }

    static void remove(String name) {
        Iterator<String> iter=prisoners.iterator();
        while (iter.hasNext()){
            System.out.println("3");
            Prisoner prisoner= GSON.fromJson(iter.next(),Prisoner.class);
            System.out.println("4");
            if(prisoner.name.equals(name)){
                iter.remove();
            }
        }
    }

    static String readFile(File file) {
            StringBuilder sb = new StringBuilder();
            try {
                int bufferSize = 1024;
                byte[] bytes = new byte[bufferSize];
                ArrayList<String> list = new ArrayList<>();
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                //System.out.println("Содержимое файла:");
                int amountData;
                while ((amountData = in.read(bytes, 0, bufferSize)) != -1)
                    sb.append(new String(bytes, 0, amountData, StandardCharsets.UTF_8));
            } catch (FileNotFoundException ex) {
                System.out.println("Файл не существует");
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл");
                e.printStackTrace();
            }
            String s = sb.toString();
            s = s.replaceAll("\\t", ";");
            return s;
    }
    static void writeToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Iterator<String> iter=prisoners.iterator();
        for(int i=0;i<prisoners.size();i++){
            System.out.println("5");
            Prisoner p= GSON.fromJson(iter.next(),Prisoner.class);
            System.out.println("6");
            fileOutputStream.write((p.name+";"+p.number).getBytes());
            fileOutputStream.write(10);
        }
        fileOutputStream.close();
    }
}

