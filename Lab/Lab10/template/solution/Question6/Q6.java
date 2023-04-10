import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Q6
{
    public static void copyDirectiory(String p1, String p2) throws IOException
    {
        File srcFile = new File(p1);
        File destFile = new File(p2);
        if(srcFile.isDirectory())
        {
            if(!destFile.exists())
            {
                destFile.mkdir();
            }
            String[] children = srcFile.list();
            for(String child : children)
            {
                copyDirectiory(p1 + File.separator + child, p2 + File.separator + child);
            }
        }
        else
        {
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    public static void main(String[] args) throws IOException
    {
        copyDirectiory("template/solution/Question6/input", "template/solution/Question6/out");
    }
}
