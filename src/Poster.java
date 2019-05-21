public class Poster {
    static int count;
    public static int getCount() {
        return count;
    }
    public static void taken(int amount){
        int a;
        if(getCount()>=amount) {
            a = amount;
        }else {
            a=getCount();
        }
        count-=a;
            System.out.println("Взято "+a+" постеров");
    }
    public static void addPosters(int countOfPosters) {
        count+=countOfPosters;
    }
}
