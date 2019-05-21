import java.util.Iterator;

public class Caretaker extends People {
    Caretaker(String name) {
        super(name);
        this.role="надзиратель";
    }
    public void takeCare(){
        System.out.println(name+" заботится о заключенных");
        Iterator<String> iter=Prison.prisoners.iterator();
        while (iter.hasNext()){
            Prisoner prisoner= People.GSON.fromJson(iter.next(),Prisoner.class);
            prisoner.mood+=2;
        }
    }
    @Override
    public void doYourBusiness(){
        super.doYourBusiness();
        takeCare();
    }

}
