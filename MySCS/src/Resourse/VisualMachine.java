package Resourse;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Command.Commands;
import User.*;

public class VisualMachine implements Serializable
{
    public transient users Owner;
    public transient int VMID;
    public String VMName;
    public String VMPath;
    public ArrayList<String> CommandList;

    public VisualMachine(users owner)
    {
        this.Owner = owner;
        CommandList = new ArrayList<String>();
    }

    public void AddCommand(String Command)
    {
        CommandList.add(Command);
    }

    public String getVMType()
    {
        return (this instanceof VMLinux ? "Linux" : (this instanceof VMMacOS ? "MacOS" : "Windows"));
    }

    public void start()
    {
        if (!(this instanceof VMWindows) && !(this instanceof VMMacOS) && !(this instanceof VMLinux))
        {
            System.out.println("This VM is not supported");
            return;
        }
        System.out.println("welcome to " + getVMType());
        Scanner sc = Commands.sc;
        String temp;
        while (sc.hasNextLine())
        {
            temp = sc.nextLine();
            if (temp.equals("EOF"))
            {
                break;
            }
            else
            {
                AddCommand(temp);
            }
        }
        System.out.println("quit " + getVMType());
    }

    public void clear()
    {
        CommandList.clear();
    }

    public void log()
    {
        if (CommandList.isEmpty())
        {
            System.out.println("no log");
            return;
        }
        for (String temp : CommandList)
        {
            System.out.println(temp);
        }
    }

}
