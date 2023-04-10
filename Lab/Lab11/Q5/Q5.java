public class Q5
{
    public static void main(String[] args)
    {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}

class SyncThread implements Runnable
{
    private static int count;

    public SyncThread()
    {
        count = 0;
    }

    public synchronized void run()
    {
        for (int i = 0; i < 5; i++)
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);//【1】
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public int getCount()
    {
        return count;
    }
}
