public class Q7
{
    public static void main(String[] args)
    {
        Object object = new Object();
        new Thread(new Number(object)).start();
        new Thread(new Letter(object)).start();
    }
}

class Number implements Runnable
{
    public Object object;

    public Number(Object object)
    {
        this.object = object;
    }

    public void run()
    {
        synchronized (object)
        {
            for (int i = 1; i <= 52; i++)
            {
                if (i > 1 && i % 2 == 1)
                {
                    System.out.print(" ");
                }
                System.out.print(i);
                if (i % 2 == 0)
                {
                    object.notifyAll();
                    try
                    {
                        object.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Letter implements Runnable
{
    public Object object;

    public Letter(Object object)
    {
        this.object = object;
    }

    public void run()
    {
        synchronized (object)
        {
            for (char i = 'A'; i <= 'Z'; i++)
            {
                System.out.print(i);
                object.notifyAll();
                if (i < 'Z')
                {
                    try
                    {
                        object.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}