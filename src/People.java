import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class People extends Thread implements Runnable {
    String name;
    private transient int tern;
    transient int mood;
    String role;
    transient int productivity;
    public static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();


    People(String name) {
        this.name = name;
        this.tern = 1;
        this.mood = 80;
        this.productivity=10;
        System.out.println("person " + this.name + " started");
        this.start();
    }
    private void die() {
        System.out.println(this.name + " не выдержал стресса и покончил с собой");
    }
    public void doYourBusiness() {
        this.mood--;
        this.tern++;
        this.productivity+=(int)(this.tern*0.2);
        if (!this.role.equals("заключенный")) {
            System.out.println(name + " усердно трудится");
        }
        else {
            System.out.println(name+" отбывает срок");
        }
    }
    @Override
    public void run() {
        while (this.isAlive()) {
            if (God.stat) {
                try {
                    if (!this.role.equals("заключенный")) {
                        if (this.mood <= 40 && Math.random() * 100 >= 60) {
                            System.out.println(this.name + " устал и ушел в отпуск.");
                            this.mood += 30;
                            Thread.sleep(500);
                        } else if (this.mood <= 20 && Math.random() * 100 >= 30) {
                            System.out.println(this.name + " очень сильно устал, он решил изменить свою жизнь и уехать в Италию");
                            Thread.interrupted();
                        } else if (this.mood <= 10) {
                            die();
                            Thread.interrupted();
                        } else {
                            this.doYourBusiness();
                            Thread.sleep(500);
                        }
                    } else {
                        if (this.mood <= 40 && Math.random() * 100 >= 60) {
                            System.out.println(this.name + " чувствует себя комфортно и в заключении.");
                            this.mood += 30;
                            Thread.sleep(500);
                        } else if (this.mood <= 20 && Math.random() * 100 >= 30) {
                            System.out.println(this.name + " поссорился с сокамерниками.");
                            Thread.interrupted();
                            Prison.remove(this.getName());
                        } else if (this.mood <= 10) {
                            die();
                            Thread.interrupted();
                        } else {
                            this.doYourBusiness();
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}