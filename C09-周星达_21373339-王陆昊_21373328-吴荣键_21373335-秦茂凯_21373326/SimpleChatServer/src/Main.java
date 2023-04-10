public class Main{
    public static void  main(String [] args){
        System.out.println("start");
        new RequestListener().start();
        System.out.println("some things went wrong");//这里的输出语句不会被执行
    }
}