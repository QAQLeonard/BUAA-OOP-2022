package Lab.Lab02.template.solution.Question11;

import java.io. *;
import java.util. *;

public class Question11
{
    static int[] arr={12,45,67,89,123,-45,67};

    static void BubbleSortPLUS(int[] a,int n)
    {
        int flag;
        for(int i=0; i<n-1; i++)
        {
            flag=0;
            for(int j=0; j<n-1-i; j++)
            {
                if(a[j]>a[j + 1])
                {
                    int temp=a[j];
                    a[j]=a[j + 1];
                    a[j+1]=temp;
                    flag=1;
                }
            }
            if(flag==0) break;
        }
    }

    public static void main(String[] args)
    {
        BubbleSortPLUS(arr,arr.length);
        for(int i=0;i<7;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        int temp=0;
        int flag=0;
        while(sc.hasNextInt())
        {
            flag=0;
            temp= sc.nextInt();
            for(int i=0;i<7;i++)
            {
                if(temp==arr[i])
                {
                    System.out.println(temp+" is in the array");
                    flag=1;
                    break;
                }
            }
            if(flag==0) System.out.println(temp+" isn't in the array");
        }
    }

}
