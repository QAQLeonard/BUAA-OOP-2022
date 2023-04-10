import java.util.HashMap;
import java.util.Map;

public class EmailSplit
{
    public static void main(String[] args)
    {
        String str = "aa@sohu.com,bb@163.com,cc@sina.com";
        Map<String, String> emailMap = new HashMap<String, String>();
        String[] emails = str.split(",");
        for (String email : emails)
        {
            String[] emailArray = email.split("@");
            emailMap.put(emailArray[0], emailArray[1]);
        }
        System.out.println(emailMap.toString());
    }
}
