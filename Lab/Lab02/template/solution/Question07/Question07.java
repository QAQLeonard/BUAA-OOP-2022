package Lab.Lab02.template.solution.Question07;

public class Question07
{
    public static void main(String[] args)
    {
        int n=Integer.parseInt(args[0]);
        //int n=5;
        int num=n*n;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print((i*5+j+1)+" ");
            }
            System.out.print("\n");
        }
    }
}
