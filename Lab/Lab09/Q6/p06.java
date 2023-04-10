public class p06
{
    public static void main(String[] args)
    {
        try
        {
            methodA();
        }
        catch (Exception e)
        {
            methodB();
        }
    }

    private static void methodA()
    {
        try
        {
            System.out.println("methodA抛出一个异常！");
            throw new RuntimeException();
        }
        finally
        {
            System.out.println("执行methodA的finally!");
        }
    }

    private static void methodB()
    {
        try
        {
            System.out.println("methodB执行！");
        }
        finally
        {
            System.out.println("执行methodB的finally!");
        }
    }
}
