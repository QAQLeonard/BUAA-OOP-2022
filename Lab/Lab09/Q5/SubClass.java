import java.io.IOException;
import java.io.FileInputStream;


//SubClass.java


public class SubClass extends SuperClass
{
    public void start() throws IOException
    {
        throw new IOException("Unable to open file");
    }

    public void open(String fileName) throws IOException
    {
        FileInputStream fis = new FileInputStream(fileName);
    }
}
