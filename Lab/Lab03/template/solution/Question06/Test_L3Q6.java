package Lab.Lab03.template.solution.Question06;

public class Test_L3Q6
{
    public static void main(String[] args)
    {
        IMessage music=new Music("鸡你太美","阴乐");
        IMessage mobile=new Mobile("蹦蹦炸弹14 pro Max","Klee");
        music.print();
        mobile.print();
    }
}
