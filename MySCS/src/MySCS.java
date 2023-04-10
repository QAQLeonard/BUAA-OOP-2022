import Command.Commands;
import Resourse.FileOperate;

import java.io.*;
import java.util.Scanner;

public class MySCS
{

    public static void main(String[] args) throws Exception
    {
        String temp;
        Scanner sc = new Scanner(System.in);
        Commands.LoggedUser = null;
        File tmpFile = new File("data");
        if (tmpFile.exists())
        {
            FileOperate.DeleteFileUsingJava7Files(tmpFile);
        }
        while (sc.hasNextLine())
        {
            temp = sc.nextLine();
            System.out.println("命令是:  " + temp);
            Commands CMD = new Commands(temp, sc);
            CMD.run();
        }
        System.exit(0);
    }
}
