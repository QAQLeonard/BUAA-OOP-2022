/*
 *从标准输入中读入两个整数集，整数集中数据无序，且可能有重复数据。当两个数据集中数据完全相同（去掉重复数据，顺序不一定相同），则两个数据集相同。编写一程序判断输入的两数据集是否相同：用1表示相同，用0表示不同。
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Question02
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String str;
        str = input.nextLine();
        int n1 = Integer.parseInt(str);
        str = input.nextLine();
        String[] strArray = str.split(" ");
        HashSet<Integer> set1 = new HashSet<Integer>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < strArray.length; i++)
        {
            int key = Integer.parseInt(strArray[i]);
            set1.add(key);
        }
        list1.addAll(set1);
        str = input.nextLine();
        int n2 = Integer.parseInt(str);
        str = input.nextLine();
        strArray = str.split(" ");
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < strArray.length; i++)
        {
            int key = Integer.parseInt(strArray[i]);
            set2.add(key);
        }
        if(set1.equals(set2))
        {
            System.out.println(1);
        }
        else
        {
            System.out.println(0);
        }
        list1.sort(null);
        for(int i=0;i<list1.size();i++)
        {
            System.out.print(list1.get(i)+" ");
        }
    }
}
