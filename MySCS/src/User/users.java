package User;

import java.io.IOException;
import java.util.*;
import Resourse.*;

public abstract class users
{
    public String ID;
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public int UserType;
    public boolean Logged;

    public TreeMap<String, Course> Courses = new TreeMap<>();
    String cmp_CourseID = "^C(17|18|19|20|21|22)(([1-9]\\d)|(0[1-9]))";
    String cmp_CourseName = "^\\w{6,16}";
    String cmp_StudentID = "^(((17|18|19|20|21|22)(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d\\d)|(0[1-9]\\d)|(00[1-9])))|((((SY|ZY)(19|20|21|22))|(BY(17|18|19|20|21|22)))(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d)|(0[1-9]))))";
    String cmp_TeacherID = "^(([1-9]\\d\\d\\d\\d)|(0[1-9]\\d\\d\\d)|(00[1-9]\\d\\d)|(000[1-9]\\d)|(0000[1-9]))";
    public Course SelectedCourse;

    public users(String ID, String FirstName, String LastName, String Email, String Password, int UserType)
    {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.UserType = UserType;
        this.Logged = false;
        SelectedCourse = null;
    }

    public void show()
    {
        System.out.println("Name: " + FirstName + " " + LastName);
        System.out.println("ID: " + ID);
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

    public boolean notSelectedCourse()
    {
        if (this.SelectedCourse == null)
        {
            System.out.println("no course selected");
            return true;
        }
        else
        {
            return false;
        }
    }

    public void listCourse()
    {
        if (Courses.isEmpty())
        {
            System.out.println("course not exist");
            return;
        }

        TreeMap<String, Course> tm = new TreeMap<>();
        tm.putAll(Courses);
        for (String key : tm.keySet())
        {
            Course tmp = tm.get(key);
            System.out.println("[ID:" + tmp.CourseID + "] [Name:" + tmp.CourseName + "] [TeacherNum:" + tmp.TeacherNum + "] [AssistantNum:" + tmp.AssistantNum + "] [StudentNum:" + tmp.StudentNum + "]");
        }
    }

    public void selectCourse(String CourseID)
    {
        if (!CourseID.matches(cmp_CourseID))
        {
            System.out.println("course id illegal");
            SelectedCourse= null;
            return;
        }
        if (!Courses.containsKey(CourseID))
        {
            System.out.println("course id not exist");
            SelectedCourse= null;
            return;
        }
        SelectedCourse = Courses.get(CourseID);
        System.out.println("select course success");
    }

    public void listWare()
    {
        if (notSelectedCourse()) return;
        SelectedCourse.listWare();
    }

    public void listAdmin()
    {
        if (notSelectedCourse()) return;
        SelectedCourse.listAdmins(UserType);
    }

    public void listTask()
    {
        if (notSelectedCourse()) return;
        SelectedCourse.listTask(UserType, ID);
    }

    public void downloadFile(String FileID, int OP, String... destPath) throws IOException
    {
        /*OP=0:Overlay without redirect
        OP=1:Overlay with redirectOverlay
        OP=2:Overlay with redirectAppend
        OP=3:redirectOverlay
        OP=4:redirectAppend
        */
        if (OP == 0)
        {
            if (notSelectedCourse()) return;
        }
        else
        {
            if (this.SelectedCourse == null)
            {
                FileOperate.outputFileUsingUsingBuffer(destPath[1], "no course selected", OP % 2 == 0);
                return;
                //System.out.println("no course selected");
            }
        }
        SelectedCourse.downloadFile(FileID, OP, destPath);
    }

    public abstract void queryScore(String... parameters);

    public abstract void queryScore();
}
