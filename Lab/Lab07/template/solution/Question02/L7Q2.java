package Question02;


interface Inter
{
    void show();
}

class Outer
{
    class Inner implements Inter
    {
        public void show()
        {
            System.out.println("21373339");
        }
    }
    static Inner in=new Outer().new Inner();
    static Inner method()
    {
        return in;
    }
}

public class L7Q2
{
    public static void main(String[] args)
    {
        Outer.method().show();
    }
}
