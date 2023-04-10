package User;

import java.util.TreeMap;
import Command.*;
public class Assistant extends Admin implements Assistant_Student
{
    public Assistant(String ID, String FirstName, String LastName, String Email, String Password, int UserType)
    {
        super(ID + "!", FirstName, LastName, Email, Password, UserType);
        this.UserType = 2;
    }

    public Assistant(Student stu)
    {
        super(stu.ID + "!", stu.FirstName, stu.LastName, stu.Email, stu.Password, stu.UserType);
        this.UserType = 2;
    }

    @Override
    public void changeRole(TreeMap<String, users> USERS)
    {
        this.Logged = false;
        //System.out.println(Command.Commands.LoggedID);
        users tmpStu = USERS.get(ID.substring(0, ID.length() - 1));
        Commands.LoggedUser=tmpStu;
        tmpStu.Logged = true;
        tmpStu.SelectedCourse = null;
        this.SelectedCourse=null;
        System.out.println("change into User.Student success");
    }
    public void show()
    {
        System.out.println("Name: " + FirstName + " " + LastName);
        System.out.println("ID: " + ID.substring(0, ID.length() - 1));
        if (UserType == 0)
        {
            System.out.println("Type: Professor");
        }
        else
        {
            System.out.println("Type: User.Student");
        }
        System.out.println("Email: " + Email);
    }
}
