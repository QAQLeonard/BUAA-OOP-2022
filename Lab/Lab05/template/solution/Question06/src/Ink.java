import java.util.*;
import java.time.*;

public class Ink extends File
{
    File TargetFile;
    filep TargetFileType;

    public Ink(String FileName, LocalDateTime CreationTime, int FileSize,String Path, File TargetFile)
    {
        this.FileName = FileName;
        this.CreationTime = CreationTime;
        this.FileSize = FileSize;
        this.Path = Path;
        this.TargetFile = TargetFile;
        this.TargetFileType = TargetFile.FileType;
        this.FileType = filep.Ink;
    }
    public void Run()
    {
        System.out.println("Running throw ink......");
        TargetFile.Run();
    }
    public void Run(File target)
    {
        System.out.println("Running throw ink......");
        ((EXE)TargetFile).Run(target);
    }
}
