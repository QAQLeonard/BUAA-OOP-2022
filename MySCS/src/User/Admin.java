package User;

import Resourse.*;
import Command.*;
import java.util.*;

public class Admin extends users
{
    public Admin(String ID, String FirstName, String LastName, String Email, String Password, int UserType)
    {
        super(ID, FirstName, LastName, Email, Password, UserType);
    }

    public void addWare(String WareID, String WarePath)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.addWare(WareID, WarePath);
    }

    public void removeWare(String WareID)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.removeWare(WareID);
    }

    public void addTask(String TaskID, String TaskPath, String StartTime, String EndTime)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.addTask(TaskID, TaskPath, StartTime, EndTime);
    }

    public void removeTask(String TaskID)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.removeTask(TaskID);
    }


    public void addStudent(String... StudentID)
    {
        if (notSelectedCourse()) return;
        for (int i = 1; i < StudentID.length; i++)
        {
            if (!StudentID[i].matches(cmp_StudentID) && !StudentID[i].matches(cmp_TeacherID))
            {
                System.out.println("user id illegal");
                return;
            }
            if (!Commands.USERS.containsKey(StudentID[i]))
            {
                System.out.println("user id not exist");
                return;
            }
            if (StudentID[i].matches(cmp_TeacherID))
            {
                System.out.println("I'm professor rather than student!");
                return;
            }
        }
        for (int i = 1; i < StudentID.length; i++)
        {
            SelectedCourse.addStudent(StudentID[i]);
        }
        System.out.println("add student success");
    }

    public void removeStudent(String StudentID)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.removeStudent(StudentID);

    }

    public void listStudent()
    {
        if (notSelectedCourse()) return;
        SelectedCourse.listStudent();
    }

    public void addAnswer(String TaskID, String AnswerPath)
    {
        if (notSelectedCourse())return;
        if(!SelectedCourse.Tasks.containsKey(TaskID))
        {
            System.out.println("task not found");
            return;
        }
        SelectedCourse.Tasks.get(TaskID).addAnswer(AnswerPath);
    }
    @Override
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
                sum= tmpTask.ReceiveTasks.size();
                System.out.println("total " + sum + (sum>1?" results":" result"));
                tmpTask.showScore(1);
            }
            else//queryScore StudentID
            {
                if (!SelectedCourse.Students.containsKey(parameters[0]))
                {
                    System.out.println("student not found");
                    return;
                }
                for (Task tmpTask : SelectedCourse.Tasks.values())
                {
                    if (tmpTask.ReceiveTasks.containsKey(parameters[0]))
                    {
                        sum++;
                    }
                }
                System.out.println("total " + sum + (sum>1?" results":" result"));
                TreeMap<String, Task> tm = new TreeMap<>();
                tm.putAll(SelectedCourse.Tasks);
                for (Task tmpTask : tm.values())
                {
                    temp = tmpTask.showScore(parameters[0], temp);
                }
            }
        }
        else//queryScore TaskID StudentID
        {
            Task tmpTask = SelectedCourse.Tasks.get(parameters[0]);
            if (tmpTask == null)
            {
                System.out.println("task not found");
                return;
            }
            if (!SelectedCourse.Students.containsKey(parameters[1]))
            {
                System.out.println("student not found");
                return;
            }
            if (tmpTask.ReceiveTasks.containsKey(parameters[1]))
            {
                sum++;
            }
            System.out.println("total " + sum + " result");
            tmpTask.showScore(parameters[1], 1);
        }
    }
    @Override
    public void queryScore()
    {
        int temp = 1;
        int sum = 0;
        if (notSelectedCourse()) return;
        for(Task tmpTask : SelectedCourse.Tasks.values())
        {
            sum += tmpTask.ReceiveTasks.size();
        }
        System.out.println("total " + sum + (sum>1?" results":" result"));
        TreeMap<String, Task> tm = new TreeMap<>();
        tm.putAll(SelectedCourse.Tasks);
        for (Task tmpTask : tm.values())
        {
            temp = tmpTask.showScore(temp);
        }
    }
}
