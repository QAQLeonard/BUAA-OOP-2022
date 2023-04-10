import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//从标准输入中输入两组整数(每行不超过20个整数，每组整数中元素有可能重复)。合并两组整数，去掉在两组整数中都出现的整数，并按从大到小顺序排序输出（即两组整数集"异或"），输出时重复的整数只输出一次。
public class Q2_1
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Set<Integer> set1 = new TreeSet<Integer>();
        Set<Integer> set2 = new TreeSet<Integer>();
        Set<Integer> set3 = new TreeSet<Integer>();
        int n = sc.nextInt();
        for (int num = 0; num < n; num++)
        {
            set1.add(sc.nextInt());
        }
        int m = sc.nextInt();
        for (int num = 0; num < m; num++)
        {
            set2.add(sc.nextInt());
        }
        set3.addAll(set1);
        set3.addAll(set2);
        set1.retainAll(set2);
        set3.removeAll(set1);
        ArrayList<Integer> list = new ArrayList<Integer>(set3);
        list.sort((o1, o2) -> o2 - o1);
        for (int num : list)
        {
            System.out.print(num + " ");
        }

    }
}
