package Resourse;

import Command.*;
import User.*;

import javax.lang.model.type.NullType;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Course
{
    public String CourseName;
    public String CourseID;
    public int TeacherNum;
    public int AssistantNum;
    public int StudentNum;
    public String cmp_StudentID = "^(((17|18|19|20|21|22)(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d\\d)|(0[1-9]\\d)|(00[1-9])))|((((SY|ZY)(19|20|21|22))|(BY(17|18|19|20|21|22)))(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d)|(0[1-9]))))";
    public String cmp_TeacherID = "^(([1-9]\\d\\d\\d\\d)|(0[1-9]\\d\\d\\d)|(00[1-9]\\d\\d)|(000[1-9]\\d)|(0000[1-9]))";
    public String cmp_WareID;
    public String cmp_WareName = "^\\w+\\.[A-Za-z\\d]+$";
    public String cmp_TaskID;
    public String cmp_TaskName = "^\\w+\\.[A-Za-z\\d]+$";
    public TreeMap<String, Admin> Admins = new TreeMap<>();
    public TreeMap<String, Student> Students = new TreeMap<>();
    public TreeMap<String, Ware> Wares = new TreeMap<>();
    public TreeMap<String, Task> Tasks = new TreeMap<>();

    public TreeMap<String, VisualMachine> VMs = new TreeMap<>();
    public ArrayList<VisualMachine> VMList = new ArrayList<>();

    public Course(String ID, String name, Admin Creator)
    {
        CourseName = name;
        CourseID = ID;
        TeacherNum = 1;
        AssistantNum = 0;
        StudentNum = 0;
        Admins.put(Creator.ID, Creator);
        cmp_WareID = "^W" + CourseID.substring(CourseID.length() - 4) + "((0[1-9])|([1-9]\\d))$";
        cmp_TaskID = "^T" + CourseID.substring(CourseID.length() - 4) + "((0[1-9])|([1-9]\\d))$";
    }

    public void addAdmin(String AdminID)
    {
        if (AdminID.matches(cmp_TeacherID))
        {
            if (!Admins.containsKey(AdminID))
            {
                Admins.put(AdminID, (Admin) Commands.USERS.get(AdminID));
                Commands.USERS.get(AdminID).Courses.put(CourseID, this);
                TeacherNum++;
            }
        }
        else
        {
            if (!Commands.USERS.containsKey(AdminID + "!") || !((Student) Commands.USERS.get(AdminID)).isAssistant)//"!" to differ assistant and student
            {
                ((Student) Commands.USERS.get(AdminID)).isAssistant = true;
                Assistant tmp = new Assistant((Student) Commands.USERS.get(AdminID));
                Commands.USERS.put(AdminID + "!", tmp);
            }
            if (!Admins.containsKey(AdminID + "!"))
            {
                Assistant tmp = (Assistant) Commands.USERS.get(AdminID + "!");
                Admins.put(AdminID + "!", tmp);
                tmp.Courses.put(CourseID, this);
                AssistantNum++;
            }
        }
    }

    public void removeAdmin(String AdminID)
    {
        if (AdminID.matches(cmp_StudentID))
        {
            if (!Admins.containsKey(AdminID + "!"))
            {
                System.out.println("user id not exist");
                return;
            }

            Assistant tmpAss = (Assistant) Admins.get(AdminID + "!");
            Admins.remove(AdminID + "!");
            tmpAss.Courses.remove(CourseID);
            AssistantNum--;
            if (tmpAss.Courses.isEmpty())
            {
                Commands.USERS.remove(AdminID + "!");
                ((Student) Commands.USERS.get(AdminID)).isAssistant = false;
            }
            System.out.println("remove admin success");
        }
        else if (AdminID.matches(cmp_TeacherID))
        {
            if (!Admins.containsKey(AdminID))
            {
                System.out.println("user id not exist");
                return;
            }
            Teacher tmpTea = (Teacher) Admins.get(AdminID);
            Admins.remove(AdminID);
            tmpTea.Courses.remove(CourseID);
            TeacherNum--;
            System.out.println("remove admin success");
        }
        else
        {
            System.out.println("user id illegal");
        }
    }

    public void listAdmins(int UserType)
    {
        if (Admins.isEmpty())
        {
            return;
        }
        TreeMap<String, users> tm = new TreeMap<>();
        tm.putAll(Admins);
        for (String key : tm.keySet())
        {
            users tmp = tm.get(key);
            if (key.matches(cmp_TeacherID))
            {
                System.out.println((UserType == 1 ? "" : ("[ID:" + tmp.ID + "] ")) + "[Name:" + tmp.LastName + " " + tmp.FirstName + "] [Type:Professor] [Email:" + tmp.Email + "]");
            }
            else
            {
                //delete the "!" when print
                System.out.println((UserType == 1 ? "" : ("[ID:" + tmp.ID.substring(0, tmp.ID.length() - 1)) + "] ") + "[Name:" + tmp.LastName + " " + tmp.FirstName + "] [Type:User.Assistant] [Email:" + tmp.Email + "]");
            }
        }
    }

    public void addWare(String WareID, String WarePath)
    {
        if (!WareID.matches(cmp_WareID))
        {
            System.out.println("ware id illegal");
            //System.out.println("ware id should be W" + CourseID.substring(CourseID.length() - 4) + "xx");
            return;
        }
        String WareName = WarePath.substring(WarePath.lastIndexOf("/") + 1);
        if (!(WareName.matches(cmp_WareName) && WareName.length() >= 6 && WareName.length() <= 16))
        {
            System.out.println("ware name illegal");
            return;
        }
        File srcFile = new File(WarePath);
        File destFile = new File("." + File.separator + "data" + File.separator + CourseID + File.separator + "wares" + File.separator + WareID + "_" + WareName);
        if (!srcFile.exists())
        {
            System.out.println("ware file does not exist");
            return;
        }
        if (Wares.containsKey(WareID))
        {
            File delFile = new File(Wares.get(WareID).WarePath);
            if (delFile.exists())
            {
                try
                {
                    FileOperate.DeleteFileUsingJava7Files(delFile);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return;
                }
            }
        }
        try
        {
            FileOperate.copyFileUsingJava7Files(WarePath, destFile.getPath());
        }
        catch (IOException e)
        {
            System.out.println("ware file operation failed");
            return;
        }
        catch (Exception e)
        {
            System.out.println("unexpected error");
            return;
        }
        Wares.put(WareID, new Ware(WareID, WareName, "." + File.separator + "data" + File.separator + CourseID + File.separator + "wares" + File.separator + WareID + "_" + WareName));
        System.out.println("add ware success");
    }

    public void removeWare(String WareID)
    {
        if (!Wares.containsKey(WareID))
        {
            System.out.println("ware not found");
            return;
        }
        String WarePath = Wares.get(WareID).WarePath;
        File file = new File(WarePath);
        try
        {
            FileOperate.DeleteFileUsingJava7Files(file);
        }
        catch (Exception e)
        {
            System.out.println("delete file failed");
            return;
        }
        Wares.remove(WareID);
        System.out.println("remove ware success");
    }

    public void listWare()
    {
        if (Wares.isEmpty())
        {
            System.out.println("total 0 ware");
            return;
        }
        TreeMap<String, Ware> tm = new TreeMap<>();
        tm.putAll(Wares);
        for (String key : tm.keySet())
        {
            Ware tmp = tm.get(key);
            System.out.println("[ID:" + tmp.WareID + "] [Name:" + tmp.WareName + "]");
        }
    }

    public void addTask(String TaskID, String TaskPath, String StartTime, String EndTime)
    {
        if (!TaskID.matches(cmp_TaskID))
        {
            System.out.println("task id illegal");
            return;
        }
        String TaskName = TaskPath.substring(TaskPath.lastIndexOf("/") + 1);
        if (!(TaskName.matches(cmp_TaskName) && TaskName.length() >= 6 && TaskName.length() <= 16))
        {
            System.out.println("task name illegal");
            return;
        }

        LocalDateTime start, end;
        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
            start = LocalDateTime.parse(StartTime, formatter);
            end = LocalDateTime.parse(EndTime, formatter);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("task time illegal");
            return;
        }
        if (start.getYear() < 1900 || end.getYear() < 1900 || start.getYear() > 9999 || end.getYear() > 9999)
        {
            System.out.println("task time illegal");
            return;
        }
        if (!end.isAfter(start))
        {
            System.out.println("task time illegal");
            return;
        }


        File srcFile = new File(TaskPath);
        File destFile = new File("." + File.separator + "data" + File.separator + CourseID + File.separator + "tasks" + File.separator + TaskID + File.separator + TaskName);
        File destDir = new File("." + File.separator + "data" + File.separator + CourseID + File.separator + "tasks" + File.separator + TaskID);
        File[] files = destDir.listFiles();
        if (!srcFile.exists())
        {
            System.out.println("task file not found");
            return;
        }
        if (destFile.exists() && files != null)
        {
            for (File file : files)
            {
                try
                {
                    FileOperate.DeleteFileUsingJava7Files(file);
                }
                catch (Exception e)
                {
                    System.out.println("delete old task file failed");
                    return;
                }
            }
        }
        try
        {
            FileOperate.copyFileUsingJava7Files(srcFile.getPath(), destFile.getPath());
        }
        catch (IOException e)
        {
            System.out.println("task file operation failed");
            return;
        }
        catch (Exception e)
        {
            System.out.println("unexpected error");
            return;
        }
        Tasks.put(TaskID, new Task(TaskID, TaskName, start, end, "." + File.separator + "data" + File.separator + CourseID + File.separator + "tasks" + File.separator + TaskID + File.separator + TaskName, CourseID));
        System.out.println("add task success");
    }

    public void removeTask(String TaskID)
    {
        if (!Tasks.containsKey(TaskID))
        {
            System.out.println("task not found");
            return;
        }
        File destDir = new File("." + File.separator + "data" + File.separator + CourseID + File.separator + "tasks" + File.separator + TaskID);
        try
        {
            FileOperate.DeleteFileUsingJava7Files(destDir);
        }
        catch (Exception e)
        {
            System.out.println("delete file failed");
            return;
        }
        Tasks.remove(TaskID);
        System.out.println("remove task success");
    }

    public void listTask(int UserType, String ID)
    {
        if (Tasks.isEmpty())
        {
            System.out.println("total 0 task");
            return;
        }
        TreeMap<String, Task> tm = new TreeMap<>();
        tm.putAll(Tasks);
        for (String key : tm.keySet())
        {
            Task tmp = tm.get(key);
            System.out.println("[ID:" + tmp.TaskID + "] [Name:" + tmp.TaskName + "] [" + (UserType == 1 ? ("Status:" + (tmp.ReceiveTasks.containsKey(ID) ? "done" : "undone")) : ("SubmissionStatus:" + tmp.ReceiveNum + "/" + Students.size())) + "] [StartTime:" + tmp.getStartTime() + "] [EndTime:" + tmp.getEndTime() + "]");
        }
    }

    public void addStudent(String StudentID)
    {
        if (!Students.containsKey(StudentID))
        {
            Students.put(StudentID, (Student) Commands.USERS.get(StudentID));
            Commands.USERS.get(StudentID).Courses.put(CourseID, this);
            StudentNum++;
        }
    }

    public void removeStudent(String StudentID)
    {
        if (!StudentID.matches(cmp_StudentID))
        {
            System.out.println("user id illegal");
            return;
        }
        if (!Students.containsKey(StudentID))
        {
            System.out.println("user id not exist");
            return;
        }
        Students.get(StudentID).Courses.remove(CourseID);
        Students.remove(StudentID);
        StudentNum--;
        System.out.println("remove student success");
    }

    public void listStudent()
    {
        if (Students.isEmpty())
        {
            System.out.println("no student");
            return;
        }
        TreeMap<String, users> tm = new TreeMap<>();
        tm.putAll(Students);
        for (String key : tm.keySet())
        {
            users tmp = tm.get(key);
            System.out.println("[ID:" + tmp.ID + "] [Name:" + tmp.LastName + " " + tmp.FirstName + "] [Email:" + tmp.Email + "]");
        }
    }

    public void downloadFile(String FileID, int OP, String... destPath) throws IOException
    {
        File srcFile;
        File destFile;
        if (Wares.containsKey(FileID)) srcFile = new File(Wares.get(FileID).WarePath);
        else if (Tasks.containsKey(FileID)) srcFile = new File(Tasks.get(FileID).TaskPath);
        else
        {
            if (OP == 0) System.out.println("file not found");
            else
            {
                FileOperate.outputFileUsingUsingBuffer(destPath[destPath.length - 1], "file not found\n", OP % 2 == 0);
            }
            return;
        }

        if (!srcFile.exists())
        {
            System.out.println("file not found");
            return;
        }

        if (OP <= 2)//download to destFile
        {
            destFile = new File(destPath[0]);
            try
            {
                FileOperate.copyFileUsingJava7Files(srcFile.getPath(), destFile.getPath());
            }
            catch (IOException e)
            {
                if (OP == 0) System.out.println("file operation failed");
                else
                {
                    FileOperate.outputFileUsingUsingBuffer(destPath[destPath.length - 1], "file operation failed\n", OP == 2);
                }
            }
        }
        if (OP != 0)
        {
            try
            {
                FileOperate.copyFileUsingFileStreams(srcFile.getPath(), destPath[destPath.length - 1], OP % 2 == 0);
            }
            catch (IOException e)
            {
                FileOperate.outputFileUsingUsingBuffer(destPath[destPath.length - 1], "file operation failed\n", OP % 2 == 0);
            }
        }
        else FileOperate.printFile(destPath[0]);

    }

    public void addVM(String StudentID, VisualMachine VM)
    {
        if (VMs.containsKey(StudentID))
        {
            for (VisualMachine vm : VMList)
            {
                if (vm != null && vm.Owner.ID.equals(StudentID))
                {
                    vm = null;
                    break;
                }
            }
        }
        VM.VMID = VMList.size() + 1;
        VMList.add(VM);
        VMs.put(StudentID, VM);
    }

    public void requestVM(String StudentID, String VMtype)
    {
        if (!Students.containsKey(StudentID))
        {
            System.out.println("student not found");
            return;
        }
        VisualMachine tmp;
        switch (VMtype)
        {
            case "Linux":
                tmp = new VMLinux(Commands.LoggedUser);
                break;
            case "Windows":
                tmp = new VMWindows(Commands.LoggedUser);
                break;
            case "MacOS":
                tmp = new VMMacOS(Commands.LoggedUser);
                break;
            default:
                System.out.println("VM type not found");
                return;
        }

        addVM(StudentID, tmp);
        System.out.println("requestVM success");
    }

    public void startVM(String StudentID)
    {
        if (!Students.containsKey(StudentID))
        {
            System.out.println("student not found");
            return;
        }
        if (!VMs.containsKey(StudentID))
        {
            System.out.println("no VM");
            return;
        }
        VMs.get(StudentID).start();
    }

    public void clearVM(int VMID)
    {
        if (VMID > VMList.size())
        {
            System.out.println("VM not found");
            return;
        }
        VisualMachine tmp = VMList.get(VMID - 1);
        tmp.clear();
        System.out.println("clear " + tmp.getVMType() + " success");
    }

    public void logVM(String StudentID)
    {
        if (!Students.containsKey(StudentID))
        {
            System.out.println("student not found");
            return;
        }
        if (!VMs.containsKey(StudentID))
        {
            System.out.println("no log");
            return;
        }
        VMs.get(StudentID).log();
    }

    public void uploadVM(String StudentID, String destPath) throws IOException
    {
        if (!Students.containsKey(StudentID))
        {
            System.out.println("student not found");
            return;
        }
        if (!VMs.containsKey(StudentID))
        {
            System.out.println("no VM");
            return;
        }
        File destFile = new File(destPath);
        try
        {
            FileOperate.CreateFileUsingJava7Files(destFile);
        }
        catch (IOException e)
        {
            System.out.println("file operation failed");
        }
        try
        {
            FileOperate.mySerialize(VMs.get(StudentID), destFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("uploadVM success");

    }

    public void downloadVM(String StudentID, String srcPath) throws IOException, ClassNotFoundException
    {
        if (!Students.containsKey(StudentID))
        {
            System.out.println("student not found");
            return;
        }
        File srcFile = new File(srcPath);
        if (!srcFile.exists())
        {
            System.out.println("file not found");
            return;
        }
        VisualMachine VM = null;
        VM = (VisualMachine) FileOperate.myDeserialize(srcFile);
        assert VM != null;
        VM.Owner = Students.get(StudentID);
        addVM(StudentID, VM);
        System.out.println("downloadVM success");
    }
}