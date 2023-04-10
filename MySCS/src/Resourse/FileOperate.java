package Resourse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileOperate
{
    public static void copyFileUsingFileStreams(String p1, String p2, boolean append) throws IOException
    {
        File srcFile = new File(p1);
        File destFile = new File(p2);
        InputStream input;
        OutputStream output;
        input = new FileInputStream(srcFile);
        output = new FileOutputStream(destFile, append);
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buf)) > 0)
        {
            output.write(buf, 0, bytesRead);
        }
        input.close();
        output.close();
    }

    public static void copyFileUsingJava7Files(String p1, String p2) throws IOException
    {
        File srcFile = new File(p1);
        File destFile = new File(p2);
        if (!destFile.exists())
        {
            CreateFileUsingJava7Files(destFile);
        }
        Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void outputFileUsingUsingBuffer(String p1, String str, boolean append)
    {
        File tmp = new File(p1);
        if (!tmp.exists())
        {
            try
            {
                Files.createFile(tmp.toPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(p1, append));
            out.write(str);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void printFile(String p1) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(p1));
        String str;
        while ((str = in.readLine()) != null)
        {
            System.out.println(str);
        }
        in.close();
    }

    public static int getPosition(String[] parameters, String... target)
    {
        int ans = -1;
        for (int i = 0; i < parameters.length; i++)
        {
            for (String s : target)
            {
                if (parameters[i].equals(s))
                {
                    ans = i;
                    break;
                }
            }
        }
        return ans;
    }

    public static void CreateFileUsingJava7Files(File file) throws IOException
    {
        if (file.exists())
        {
            return;
        }
        if (file.toPath().getParent() != null)
        {
            Files.createDirectories(file.toPath().getParent());
        }
        Files.createFile(file.toPath());
    }

    public static void DeleteFileUsingJava7Files(File file) throws IOException
    {
        if (file.isFile())
        {
            Files.delete(file.toPath());
        }
        else
        {
            File[] files = file.listFiles();
            if (files != null)//if file contains files, then delete them
            {
                for (File value : files)
                {
                    if (value.isFile())
                    {
                        Files.delete(value.toPath());//delete child file
                    }
                    else if (value.isDirectory())
                    {
                        DeleteFileUsingJava7Files(value);//delete child directory
                    }
                }
            }
            Files.delete(file.toPath());//删除当前目录
        }
    }

    public static void mySerialize(Object obj, File destFile) throws IOException
    {
        String path = destFile.getPath();
        OutputStream out = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(obj);
        objOut.close();
    }

    public static Object myDeserialize(File srcFile) throws IOException, ClassNotFoundException
    {
        String path = srcFile.getPath();
        InputStream in = new FileInputStream(path);
        ObjectInputStream objIn = new ObjectInputStream(in);
        Object obj = objIn.readObject();
        return obj;
    }

}
