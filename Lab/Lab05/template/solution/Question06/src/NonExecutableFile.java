import java.util.*;
import java.time.*;

public class NonExecutableFile extends File
{
    public NonExecutableFile(String FileName, LocalDateTime CreationTime, int FileSize, String Path)
    {
        this.FileName = FileName;
        this.CreationTime = CreationTime;
        this.FileSize = FileSize;
        this.Path = Path;
        this.FileType = filep.NEF;
        Content = "This is a non-executable file.";
    }

    public void Open()
    {
        System.out.println("Cannot open " + FileName);
    }

    public void Open(File targetWay)
    {
        ((EXE) targetWay).Run(this);
    }
}
