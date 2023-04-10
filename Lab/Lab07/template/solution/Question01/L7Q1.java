package Question01;

class Outer
{
    public int num = 10;

    class Inner
    {
        public int num = 20;

        public void show()
        {
            int num = 30;
            System.out.println(num);
            System.out.println(this.num);
            System.out.println(Outer.this.num);
        }
    }
}

public class L7Q1
{
    public static void main(String[] args)
    {
        Outer.Inner oi = new Outer().new Inner();;
        oi.show();
    }
}