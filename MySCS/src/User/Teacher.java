package User;

import java.util.*;

import Command.*;
import Resourse.*;
public class Teacher extends Admin
{
    public Teacher(String ID, String FirstName, String LastName, String Email, String Password, int UserType)
    {
        super(ID, FirstName, LastName, Email, Password, UserType);
        this.UserType = 0;
    }

    public TreeMap<String, Course> DirectCourses = new TreeMap<>();

    public void addCourse(String CourseID, String CourseName)
    {
        if (!CourseID.matches(cmp_CourseID))
        {
            System.out.println("course id illegal");
            return;
        }
        if (Commands.COURSES.containsKey(CourseID))
        {
            System.out.println("course id duplication");
            return;
        }
        if (!CourseName.matches(cmp_CourseName))
        {
            System.out.println("course name illegal");
            return;
        }
        Commands.COURSES.put(CourseID, new Course(CourseID, CourseName, this));
        DirectCourses.put(CourseID, Commands.COURSES.get(CourseID));
        Courses.put(CourseID, Commands.COURSES.get(CourseID));
        System.out.println("add course success");
    }

    public void removeCourse(String CourseID)
    {
        if (!CourseID.matches(cmp_CourseID))
        {
            System.out.println("course id illegal");
            return;
        }
        if (!DirectCourses.containsKey(CourseID))
        {
            System.out.println("course id not exist");
            return;
        }
        Course tmpCou = Commands.COURSES.get(CourseID);
        if (SelectedCourse.CourseID.equals(CourseID))
        {
            SelectedCourse = null;
        }
        for (Admin tmpAd : tmpCou.Admins.values())
        {
            if (!tmpAd.Courses.containsKey(CourseID))
            {
                System.out.println("ERROR!!! IN TEACHER.removeCourse");
                System.exit(0);
            }
            tmpAd.Courses.remove(CourseID);
        }
        for (Student tmpStu : tmpCou.Students.values())
        {
            if (!tmpStu.Courses.containsKey(CourseID))
            {
                System.out.println("ERROR!!! IN TEACHER.removeCourse");
                System.exit(0);
            }
            tmpStu.Courses.remove(CourseID);
        }
        Commands.COURSES.remove(CourseID);
        DirectCourses.remove(CourseID);
        Courses.remove(CourseID);
        System.out.println("remove course success");

    }

    public void addAdmin(String... AdminID)
    {
        if (notSelectedCourse()) return;
        for (int i = 1; i < AdminID.length; i++)
        {
            String s = AdminID[i];
            if (!s.matches(cmp_StudentID) && !s.matches(cmp_TeacherID))
            {
                System.out.println("user id illegal");
                return;
            }
            if (!Commands.USERS.containsKey(s))
            {
                System.out.println("user id not exist");
                return;
            }
        }
        Course tmpCou = DirectCourses.get(SelectedCourse.CourseID);
        if (tmpCou == null)
        {
            System.out.println("ERROR!!! IN TEACHER.addAdmin");
            System.exit(0);
        }
        for (int i = 1; i < AdminID.length; i++)
        {
            SelectedCourse.addAdmin(AdminID[i]);
        }
        System.out.println("add admin success");
    }

    public void removeAdmin(String AdminID)
    {
        if (notSelectedCourse()) return;
        SelectedCourse.removeAdmin(AdminID);
    }

    public void listWare()
    {
        if (notSelectedCourse()) return;
        SelectedCourse.listWare();
    }

    public void clearVM(int VMID)
    {
        if(notSelectedCourse()) return;
        SelectedCourse.clearVM(VMID);
    }
}
