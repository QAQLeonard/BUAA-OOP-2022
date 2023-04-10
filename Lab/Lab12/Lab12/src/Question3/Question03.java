/*
 *输入一个正整数n（大于等于1，小于100），编程计算并输出n的阶乘。例如，输入30，输出265252859812191058636308480000000
 */
import java.util.Scanner;

public class Question03
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String str;
        str = input.nextLine();
        int n = Integer.parseInt(str);
        int[] array = new int[20001];
        array[0] = 1;
        int digit = 1;
        for (int i = 2; i <= n; i++)
        {
            int num = 0;
            for (int j = 0; j < digit; j++)
            {
                int temp = array[j] * i + num;
                array[j] = temp % 10;
                num = temp / 10;
            }
            while (num != 0)
            {
                array[digit] = num % 10;
                num = num / 10;
                digit++;
            }
        }
        for (int i = digit - 1; i >= 0; i--)
        {
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
