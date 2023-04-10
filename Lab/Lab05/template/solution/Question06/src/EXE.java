import java.util.*;
import java.time.*;

public class EXE extends File
{
    public EXE(String FileName, LocalDateTime CreationTime, int FileSize,String Path)
    {
        this.FileName = FileName;
        this.CreationTime = CreationTime;
        this.FileSize = FileSize;
        this.Path = Path;
        this.FileType = filep.EXE;
    }
    public void Run()
    {
        System.out.println("Running " + FileName+"...");
    }
    public void Run(File target)
    {
        System.out.print("Running " + FileName + " on " + target.FileName+"...");
        System.out.println(target.Content);
    }
}

