import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;

public class Bird extends Thread{
    int count;

    @Override
    public void run() {
        System.out.println("birds " + this.count + " started");
        while (isAlive()) {
            if (God.stat) {
                fly();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void fly(){
        System.out.println("Птицы летают");
        if(Poster.count>0) {
            Poster.taken(this.count);
            if (Math.random() * 100 < 70) {
                drop();
            }
        }
    }
    public void drop(){
        System.out.println("Птица выронила плакат");
        Iterator<String> iter=Prison.prisoners.iterator();
        while (iter.hasNext()) {
            Prisoner prisoner= Prison.GSON.fromJson(iter.next(),Prisoner.class);
            if (Math.random() * 100 < 60) {
                prisoner.tryToEscape();
            }
        }
    }
    public Bird(int count){
        this.count=count;
        this.start();
    }
}