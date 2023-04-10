package User;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.util.TreeMap;
import Resourse.*;
import Command.*;

public class Student extends users implements Assistant_Student
{
    public boolean isAssistant = false;

    public Student(String ID, String FirstName, String LastName, String Email, String Password, int UserType)
    {
        super(ID, FirstName, LastName, Email, Password, UserType);
        this.UserType = 1;
    }

    @Override
    public void changeRole(TreeMap<String, users> USERS)
    {
        if (!this.isAssistant)
        {
            System.out.println("permission denied");
        }
        else
        {
            this.Logged = false;
            users tmpAss = USERS.get(ID + "!");
            Commands.LoggedUser = tmpAss;
            tmpAss.Logged = true;
            tmpAss.SelectedCourse = null;
            this.SelectedCourse = null;
            System.out.println("change into User.Assistant success");
        }
    }

    public void submitTask(String TaskID, String WorkPath)
    {
        if (notSelectedCourse()) return;

        if (!SelectedCourse.Tasks.containsKey(TaskID))
        {
            System.out.println("task not found");
            return;
        }
        SelectedCourse.Tasks.get(TaskID).submitWork(ID, WorkPath);
    }

    public void queryScore(String... parameters)
    {
        int temp = 1;
        int sum = 0;
        if (notSelectedCourse()) return;
        if (parameters.length == 1)
        {
            if (parameters[0].matches("T\\d{6}"))//queryScore TaskID
            {
                Task tmpTask = SelectedCourse.Tasks.get(parameters[0]);
                if (tmpTask == null)
                {
                    System.out.println("task not found");
                    return;
                }
                if (tmpTask.ReceiveTasks.containsKey(ID)) sum++;
                System.out.println("total " + sum + " result");
                tmpTask.showScore(ID, temp);
            }
            //queryScore StudentID
            else System.out.println("permission denied");

        }
        //queryScore TaskID StudentID
        else System.out.println("permission denied");


    }

    public void queryScore()
    {
        int temp = 1;
        int sum = 0;
        if (notSelectedCourse()) return;
        for (Task tmpTask : SelectedCourse.Tasks.values())
        {
            if (tmpTask.ReceiveTasks.containsKey(ID)) sum++;
        }
        System.out.println("total " + sum + (sum > 1 ? " results" : " result"));
        TreeMap<String, Task> tm = new TreeMap<>();
        tm.putAll(SelectedCourse.Tasks);
        for (Task tmpTask : tm.values())
        {
            temp = tmpTask.showScore(ID, temp);
        }
    }

    public void requestVM(String VMType)
    {
        if(notSelectedCourse()) return;
        SelectedCourse.requestVM(ID,VMType);
    }

    public void startVM()
    {
        if(notSelectedCourse()) return;
        SelectedCourse.startVM(ID);
    }

    public void logVM()
    {
        if(notSelectedCourse()) return;
        SelectedCourse.logVM(ID);
    }
    public void uploadVM(String destPath) throws IOException
    {
        if(notSelectedCourse()) return;
        SelectedCourse.uploadVM(ID,destPath);
    }
    public void downloadVM(String srcPath) throws IOException, ClassNotFoundException
    {
        if(notSelectedCourse()) return;
        SelectedCourse.downloadVM(ID,srcPath);
    }
}