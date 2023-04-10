import java.util.*;
import java.time.*;

public class Main
{
    public static String Pwd;
    public static void main(String[] args)
    {
        Folder folder = new Folder("root", LocalDateTime.now(), "root");
        Folder folder1 = new Folder("folder1", LocalDateTime.now(), "root/folder1");
        EXE exe = new EXE("exe", LocalDateTime.now(), 100, "root/exe");
        Ink ink = new Ink("ink", LocalDateTime.now(), 100, "root/ink", exe);
        NonExecutableFile nef = new NonExecutableFile("nef", LocalDateTime.now(), 100, "root/nef");
        EXE exe1 = new EXE("exe1", LocalDateTime.now(), 100, "root/folder1/exe1");
        folder1.AddFile(exe1);
        folder.AddFile(folder1);
        folder.AddFile(exe);
        folder.AddFile(ink);
        folder.AddFile(nef);
        folder.PrintFiles();
        exe.Run();
        ink.Run();
        nef.Open();
        nef.Open(exe);
        folder1.PrintFiles();
        exe1.Run();
        folder.RemoveFile(exe);
        folder.PrintFiles();
    }
}