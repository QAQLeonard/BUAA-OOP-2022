import java.util.Scanner;

public class Q2_2
{
    public int cal(int a)//求累和
    {
        int sum = 0;
        for (int i = 1; i <= a; i++)
        {
            sum += i;
        }
        return sum;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans=0;
        Q2_2 m = new Q2_2();
        for (int i = 1; i <= n; i++)
        {
            ans += m.cal(i);
        }
        System.out.println(ans);
    }
}