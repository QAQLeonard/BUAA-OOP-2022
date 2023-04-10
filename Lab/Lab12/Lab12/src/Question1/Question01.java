import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/*
 *输入一组无序的整数，编程输出其中出现次数最多的整数及其出现次数。若出现次数最多的整数有多个，则按照整数升序分行输出
 */
public class Question01
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String str;
        str = input.nextLine();
        int n = Integer.parseInt(str);
        str = input.nextLine();
        String[] strArray = str.split(" ");
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for (int i = 0; i < strArray.length; i++)
        {
            int key = Integer.parseInt(strArray[i]);
            if (map.containsKey(key))
            {
                int value = map.get(key);
                map.put(key, value + 1);
            }
            else
            {
                map.put(key, 1);
            }
        }
        int max_value=0;
        for (int key : map.keySet())
        {
            int value = map.get(key);
            if(value>max_value)
            {
                max_value=value;
            }
        }
        ArrayList<Integer> list=new ArrayList<Integer>();

        for (int key : map.keySet())
        {
            int value = map.get(key);
            if(value==max_value)
            {
                list.add(key);
            }
        }
        list.sort(null);
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i)+" "+max_value);
        }
    }
}
