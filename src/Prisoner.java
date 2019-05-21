public class Prisoner extends People {
    String number;
    Prisoner(String number,String name) {
        super(name);
        this.number=number;
        this.role="заключенный";
        System.out.println(number+" "+name);
    }
    void tryToEscape(){
        if(Math.random()*100<30){
            Prison.prisoners.remove(person);
        }
    }
    @Override
    public void doYourBusiness(){
        super.doYourBusiness();
        tryToEscape();
    }
}
