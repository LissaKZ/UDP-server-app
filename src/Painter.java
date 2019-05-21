public class Painter extends People{
    Painter(String name){
        super(name);
        this.role="художник";
    }
    public void paint(){
        Poster.addPosters(productivity);
        System.out.println(name+" нарисовал за сегодня "+productivity+" плакатов");
    }
    @Override
    public void doYourBusiness(){
        super.doYourBusiness();
        paint();
    }
}