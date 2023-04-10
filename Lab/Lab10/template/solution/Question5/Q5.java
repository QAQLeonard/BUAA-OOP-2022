import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Q5
{
    public static void copyFile(String p1, String p2) throws IOException
    {
        File srcFile = new File(p1);
        File destFile = new File(p2);
        Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    public static void main(String[] args) throws IOException
    {
        copyFile("template/solution/Question5/input.txt", "template/solution/Question5/out.txt");
    }
}
