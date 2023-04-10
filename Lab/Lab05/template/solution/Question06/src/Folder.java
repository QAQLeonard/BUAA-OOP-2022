import java.util.*;
import java.time.*;

public class Folder extends File
{
    public ArrayList<File> Files;
    public Folder(String FileName, LocalDateTime CreationTime,String Path)
    {
        this.FileName = FileName;
        this.CreationTime = CreationTime;
        this.FileSize = 0;
        Files = new ArrayList<>();
        this.Path = Path;
        this.FileType = filep.Folder;
    }
    public void AddFile(File file)
    {
        Files.add(file);
        FileSize += file.FileSize;
    }
    public void RemoveFile(File file)
    {
        Files.remove(file);
        FileSize -= file.FileSize;
    }
    public void PrintFiles()
    {
        for (File file : Files)
        {
            System.out.println(file.FileName);
        }
    }
    public void Run()
    {
        for (File file : Files)
        {
            file.Run();
        }
    }
    public void Open()
    {
        Main.Pwd=Path;

    }
}
