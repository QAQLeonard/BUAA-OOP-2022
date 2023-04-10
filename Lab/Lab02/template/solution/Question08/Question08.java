package Lab.Lab02.template.solution.Question08;

public class Question08
{
    public static void main(String[] args)
    {
        int n=Integer.parseInt(args[0]);
        //int n=5;
        for(int i=0;i<=n/2+1;i++)//0,1,2
        {
            for(int j=0;j<n/2-i+1;j++)
            {
                System.out.print(" ");
            }
            for(int j=0;j<2*i-1;j++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
        }

        for(int i=n/2+1;i<n;i++)//3,4
        {
            for(int j=0;j<i-n/2;j++)
            {
                System.out.print(" ");
            }
            for(int j=0;j<2*(n-i)-1;j++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
