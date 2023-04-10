package Lab.Lab01;
import java.io.*;
public class Question2
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("21373339"+"\n"+"周星达");
        BufferedReader buf;
        buf=new BufferedReader(new InputStreamReader(System.in));
        String str;
        while(true)
        {
            str=buf.readLine();
            if(str.equals("QUIT"))
            {
                System.out.println("----- Good Bye! -----");
                System.exit(0);
                break;
            }
            System.out.println("Hello, World!");
        }
    }
}
