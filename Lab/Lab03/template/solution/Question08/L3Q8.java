package Lab.Lab03.template.solution.Question08;

import java.util.Scanner;

public class L3Q8
{

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String temp;
        while(sc.hasNextLine())
        {
            temp=sc.nextLine();
            int flag=0;
            if(!temp.matches("^[1-9][0-9]*"))
            {
                System.out.println(temp+"不是无前导0的无符号整数捏");
                continue;
            }

            if(!temp.matches("[0-9]*[1-9]$"))
            {
                System.out.println(temp+"不是回文数");
                continue;
            }

            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)!=temp.charAt(temp.length()-i-1))
                {
                    flag=1;
                }
            }
            if(flag==0) System.out.println(temp+"是回文数");
            else System.out.println(temp+"不是回文数");

        }
    }
}
