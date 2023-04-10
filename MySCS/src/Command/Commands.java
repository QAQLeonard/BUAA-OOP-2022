package Command;

import Resourse.*;
import User.*;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.util.*;

public class Commands
{
    public String cmd;
    public OP op;
    public static TreeMap<String, users> USERS = new TreeMap<>();
    public static TreeMap<String, Course> COURSES = new TreeMap<>();
    public static users LoggedUser = null;
    public static Scanner sc;

    String cmp_StudentID = "^(((17|18|19|20|21|22)(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d\\d)|(0[1-9]\\d)|(00[1-9])))|((((SY|ZY)(19|20|21|22))|(BY(17|18|19|20|21|22)))(([1-3]\\d)|(0[1-9])|(4[0-3]))[1-6](([1-9]\\d)|(0[1-9]))))$";
    String cmp_TeacherID = "^(([1-9]\\d\\d\\d\\d)|(0[1-9]\\d\\d\\d)|(00[1-9]\\d\\d)|(000[1-9]\\d)|(0000[1-9]))$";
    String cmp_Email = "^\\w+@\\w+(\\.\\w+)+$";
    String cmp_Password = "^[a-zA-Z]\\w{7,15}$";
    String cmp_Name = "^[A-Z][a-z]{0,19}$";

    public Commands(String cmd, Scanner sc)
    {
        this.cmd = cmd;
        Commands.sc = sc;
    }

    public void run() throws Exception
    {
        String[] parameters = cmd.split(" +");
        switch (parameters[0])
        {
            case "":
                break;
            case "register":
                op = OP.REGISTER;
                register(parameters);
                break;
            case "login":
                op = OP.LOGIN;
                login(parameters);
                break;
            case "logout":
                op = OP.LOGOUT;
                logout(parameters);
                break;
            case "printInfo":
                op = OP.PRINTINFO;
                printInfo(parameters);
                break;
            case "QUIT":
                op = OP.QUIT;
                QUIT(parameters);
                break;
            case "addCourse":
                addCourse(parameters);
                break;
            case "removeCourse":
                removeCourse(parameters);
                break;
            case "listCourse":
                listCourse(parameters);
                break;
            case "selectCourse":
                selectCourse(parameters);
                break;
            case "addAdmin":
                addAdmin(parameters);
                break;
            case "removeAdmin":
                removeAdmin(parameters);
                break;
            case "listAdmin":
                listAdmin(parameters);
                break;
            case "changeRole":
                changeRole(parameters);
                break;
            case "addWare":
                addWare(parameters);
                break;
            case "removeWare":
                removeWare(parameters);
                break;
            case "listWare":
                listWare(parameters);
                break;
            case "addTask":
                addTask(parameters);
                break;
            case "removeTask":
                removeTask(parameters);
                break;
            case "listTask":
                listTask(parameters);
                break;
            case "addStudent":
                addStudent(parameters);
                break;
            case "removeStudent":
                removeStudent(parameters);
                break;
            case "listStudent":
                listStudent(parameters);
                break;
            case "downloadFile":
                downloadFile(parameters);
                break;
            case "openFile":
                openFile(parameters);
                break;
            case "submitTask":
                //System.out.println("submitTask");
                submitTask(parameters);
                break;
            case "addAnswer":
                addAnswer(parameters);
                break;
            case "queryScore":
                queryScore(parameters);
                break;
            case "requestVM":
                requestVM(parameters);
                break;
            case "startVM":
                startVM(parameters);
                break;
            case "logVM":
                logVM(parameters);
                break;
            case  "clearVM":
                clearVM(parameters);
                break;
            case "uploadVM":
                uploadVM(parameters);
                break;
            case "downloadVM":
                downloadVM(parameters);
                break;
            default:
                System.out.println("command '" + parameters[0] + "' not found");
                break;
        }
    }

    public boolean check(char lengthType, int length, String LogState, String UserType, String[] parameters)
    {
        switch (lengthType)
        {
            case '=':
                if (parameters.length != length)
                {
                    System.out.println("arguments illegal ");
                    return true;
                }
                break;
            case '<':
                if (parameters.length >= length)
                {
                    System.out.println("arguments illegal ");
                    return true;
                }
                break;
            case '>':
                if (parameters.length <= length)
                {
                    System.out.println("arguments illegal ");
                    return true;
                }
                break;
            case '0':
                break;
            default:
                System.out.println("ERROR!!!");
                System.exit(0);
                return true;
        }
        switch (LogState)
        {
            case "logged":
                if (LoggedUser == null)
                {
                    System.out.println("not logged in");
                    return true;
                }
                break;
            case "NotLogged":
                if (LoggedUser != null)
                {
                    System.out.println("already logged in");
                    return true;
                }
                break;
            case "All":
                break;
            default:
                System.out.println("ERROR!!!");
                System.exit(0);
        }
        if (LoggedUser != null)
        {
            switch (UserType)
            {
                case "User.Student":
                    if (LoggedUser.UserType != 1)
                    {
                        System.out.println("permission denied");
                        return true;
                    }
                    break;
                case "User.Teacher":
                    if (LoggedUser.UserType != 0)
                    {
                        System.out.println("permission denied");
                        return true;
                    }
                    break;
                case "User.Admin":
                    if (LoggedUser.UserType != 2 && LoggedUser.UserType != 0)
                    {
                        System.out.println("permission denied");
                        return true;
                    }
                    break;
                case "All":
                    break;
                default:
                    System.out.println("ERROR!!!");
                    System.exit(0);
            }
        }
        return false;
    }

    public boolean check(char lengthType, int length, String LogState, String UserType, String[] parameters, String Path, boolean append)
    {
        switch (lengthType)
        {
            case '=':
                if (parameters.length != length)
                {
                    FileOperate.outputFileUsingUsingBuffer(Path, "arguments illegal\n", append);
                    return true;
                }
                break;
            case '<':
                if (parameters.length >= length)
                {
                    FileOperate.outputFileUsingUsingBuffer(Path, "arguments illegal\n", append);
                    return true;
                }
                break;
            case '>':
                if (parameters.length <= length)
                {
                    FileOperate.outputFileUsingUsingBuffer(Path, "arguments illegal\n", append);
                    return true;
                }
                break;
            case '0':
                break;
            default:
                System.out.println("ERROR!!!");
                System.exit(0);
                return true;
        }
        switch (LogState)
        {
            case "logged":
                if (LoggedUser == null)
                {
                    FileOperate.outputFileUsingUsingBuffer(Path, "not logged in\n", append);
                    return true;
                }
                break;
            case "NotLogged":
                if (LoggedUser != null)
                {
                    FileOperate.outputFileUsingUsingBuffer(Path, "already logged in\n", append);
                    return true;
                }
                break;
            case "All":
                break;
            default:
                System.out.println("ERROR!!!");
                System.exit(0);
        }
        if (LoggedUser != null)
        {
            switch (UserType)
            {
                case "User.Student":
                    if (LoggedUser.UserType != 1)
                    {
                        FileOperate.outputFileUsingUsingBuffer(Path, "permission denied\n", append);
                        return true;
                    }
                    break;
                case "User.Teacher":
                    if (LoggedUser.UserType != 0)
                    {
                        FileOperate.outputFileUsingUsingBuffer(Path, "permission denied\n", append);
                        return true;
                    }
                    break;
                case "User.Admin":
                    if (LoggedUser.UserType != 2 && LoggedUser.UserType != 0)
                    {
                        FileOperate.outputFileUsingUsingBuffer(Path, "permission denied\n", append);
                        return true;
                    }
                    break;
                case "All":
                    break;
                default:
                    System.out.println("ERROR!!!");
                    System.exit(0);
            }
        }
        return false;
    }

    public void register(String[] parameters)
    {
        if (check('=', 7, "NotLogged", "All", parameters)) return;
        int type;
        if (parameters[1].matches(cmp_StudentID))
        {
            type = 1;
        }
        else if (parameters[1].matches(cmp_TeacherID))
        {
            type = 0;
        }
        else
        {
            System.out.println("user id illegal");
            return;
        }
        if (USERS.containsKey(parameters[1]))
        {
            System.out.println("user id duplication");
            return;
        }
        if (!parameters[2].matches(cmp_Name) || !parameters[3].matches(cmp_Name))
        {
            System.out.println("user name illegal");
            return;
        }

        if (!parameters[4].matches(cmp_Email))
        {
            System.out.println("email address illegal");
            return;
        }

        if (!parameters[5].matches(cmp_Password))
        {
            System.out.println("password illegal");
            return;
        }

        if (!parameters[6].equals(parameters[5]))
        {
            System.out.println("passwords inconsistent");
            return;
        }
        if (type == 0)
        {
            USERS.put(parameters[1], new Teacher(parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], 0));
        }
        else
        {
            USERS.put(parameters[1], new Student(parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], 1));
        }
        System.out.println("register success");
    }

    public void login(String[] parameters)
    {
        if (check('=', 3, "NotLogged", "All", parameters)) return;
        if (!parameters[1].matches(cmp_StudentID) && !parameters[1].matches(cmp_TeacherID))
        {
            System.out.println("user id illegal");
            return;
        }
        if (!USERS.containsKey(parameters[1]))
        {
            System.out.println("user id not exist");
            return;
        }
        users user = USERS.get(parameters[1]);
        if (user.Password.equals(parameters[2]))
        {
            LoggedUser = user;
            user.Logged = true;
            LoggedUser.SelectedCourse = null;
            if (user.UserType == 0)
            {
                System.out.println("Hello Professor " + user.LastName + "~");
            }
            else
            {
                System.out.println("Hello " + user.FirstName + "~");
            }
        }
        else
        {
            System.out.println("wrong password");
        }
    }

    public void logout(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        LoggedUser.Logged = false;
        LoggedUser.SelectedCourse = null;
        LoggedUser = null;
        System.out.println("Bye~");
    }

    public void printInfo(String[] parameters)
    {
        if (parameters.length > 2)
        {
            System.out.println("arguments illegal");
            return;
        }

        if (LoggedUser == null)
        {
            System.out.println("login first");
            return;
        }

        if (parameters.length == 1)
        {
            LoggedUser.show();
        }
        else if (parameters.length == 2)
        {
            if (LoggedUser.UserType != 0)
            {
                System.out.println("permission denied");
                return;
            }

            if (!parameters[1].matches(cmp_StudentID) && !parameters[1].matches(cmp_TeacherID))
            {
                System.out.println("user id illegal");
                return;
            }

            if (USERS.containsKey(parameters[1]))
            {
                USERS.get(parameters[1]).show();
            }
            else
            {
                System.out.println("user id not exist");
            }

        }
    }

    public void QUIT(String[] parameters)
    {
        if (check('=', 1, "All", "All", parameters)) return;
        System.out.println("----- Good Bye! -----");
        System.exit(0);
    }

    public void addCourse(String[] parameters)
    {
        if (check('=', 3, "logged", "User.Teacher", parameters)) return;
        ((Teacher) LoggedUser).addCourse(parameters[1], parameters[2]);
    }

    public void removeCourse(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Teacher", parameters)) return;
        ((Teacher) LoggedUser).removeCourse(parameters[1]);
    }

    public void listCourse(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        LoggedUser.listCourse();
    }

    public void selectCourse(String[] parameters)
    {
        if (check('=', 2, "logged", "All", parameters)) return;
        LoggedUser.selectCourse(parameters[1]);
    }

    public void addAdmin(String[] parameters)
    {
        if (check('>', 1, "logged", "User.Teacher", parameters)) return;
        ((Teacher) LoggedUser).addAdmin(parameters);
    }

    public void removeAdmin(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Teacher", parameters)) return;
        ((Teacher) LoggedUser).removeAdmin(parameters[1]);
    }

    public void listAdmin(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        LoggedUser.listAdmin();
    }

    public void changeRole(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        if (LoggedUser.UserType == 0)
        {
            System.out.println("permission denied");
        }
        else if (LoggedUser.UserType == 1)
        {
            ((Student) LoggedUser).changeRole(USERS);
        }
        else if (LoggedUser.UserType == 2)
        {
            ((Assistant) LoggedUser).changeRole(USERS);
        }

    }

    public void addWare(String[] parameters)
    {
        if (check('=', 3, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).addWare(parameters[1], parameters[2]);
    }

    public void removeWare(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).removeWare(parameters[1]);
    }

    public void listWare(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        LoggedUser.listWare();
    }

    public void addTask(String[] parameters)
    {
        if (check('=', 5, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).addTask(parameters[1], parameters[2], parameters[3], parameters[4]);
    }

    public void removeTask(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).removeTask(parameters[1]);
    }

    public void listTask(String[] parameters)
    {
        if (check('=', 1, "logged", "All", parameters)) return;
        LoggedUser.listTask();
    }

    public void addStudent(String[] parameters)
    {
        if (check('>', 1, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).addStudent(parameters);
    }

    public void removeStudent(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).removeStudent(parameters[1]);
    }

    public void listStudent(String[] parameters)
    {
        if (check('=', 1, "logged", "User.Admin", parameters)) return;
        ((Admin) LoggedUser).listStudent();
    }

    public void downloadFile(String[] parameters) throws IOException
    {
        int ReDirect = FileOperate.getPosition(parameters, ">", ">>");
        if (ReDirect == parameters.length - 1)
        {
            System.out.println("please input the path to redirect the file");
            return;
        }
        if (ReDirect == -1) //downloadFile destPath FileID
        {
            if (check('=', 3, "logged", "All", parameters)) return;
            LoggedUser.downloadFile(parameters[2], 0, parameters[1]);
        }
        else
        {
            if ((ReDirect > 2) && (parameters[ReDirect + 1].compareTo(parameters[ReDirect - 2]) == 0))
            {
                System.out.println("input file is output file");
                return;
            }

            if (ReDirect == 2)//downloadFile FileID >|>> FilePath
            {
                if (check('=', 4, "logged", "All", parameters, parameters[ReDirect + 1], parameters[ReDirect].equals(">>")))
                    return;
                LoggedUser.downloadFile(parameters[1], (parameters[ReDirect].equals(">>") ? 4 : 3), parameters[3]);
            }
            else if (ReDirect == 3)//downloadFile FilePath FileID >|>> FilePath
            {
                if (check('=', 5, "logged", "All", parameters, parameters[ReDirect + 1], parameters[ReDirect].equals(">>")))
                    return;
                LoggedUser.downloadFile(parameters[2], (parameters[ReDirect].equals(">>") ? 2 : 1), parameters[1], parameters[4]);
            }
            else
                FileOperate.outputFileUsingUsingBuffer(parameters[ReDirect + 1], "arguments illegal\n", parameters[ReDirect].equals(">>"));

        }
    }

    public void openFile(String[] parameters)
    {
        int ReDirect = FileOperate.getPosition(parameters, "<");
        if (ReDirect == -1)
        {
            if (parameters.length < 2)
            {
                System.out.println("please input the path to open the file");
                return;
            }
            if (check('=', 2, "All", "All", parameters)) return;
            try
            {
                FileOperate.printFile(parameters[1]);
            }
            catch (IOException e)
            {
                System.out.println("file open failed");
            }
        }
        else
        {
            if (ReDirect == parameters.length - 1)
            {
                System.out.println("please input the path to redirect the file");
                return;
            }
            if (ReDirect == 1)
            {
                if (check('=', 3, "All", "All", parameters)) return;
                try
                {
                    FileOperate.printFile(parameters[2]);
                }
                catch (IOException e)
                {
                    System.out.println("file open failed");
                }
            }
            else if (ReDirect == 2)
            {
                if (check('=', 4, "All", "All", parameters)) return;
                try
                {
                    FileOperate.printFile(parameters[1]);
                }
                catch (IOException e)
                {
                    System.out.println("file open failed");
                }
            }
            else
            {
                System.out.println("arguments illegal");
            }
        }

    }

    public void submitTask(String[] parameters)
    {
        int ReDirect = FileOperate.getPosition(parameters, "<");
        if (ReDirect == -1)// submitTask TaskPath TaskID
        {
            if (check('=', 3, "logged", "All", parameters)) return;
            if (LoggedUser.UserType != 1)
            {
                System.out.println("operation not allowed");
                return;
            }
            ((Student) LoggedUser).submitTask(parameters[2], parameters[1]);
        }
        else
        {
            if (ReDirect == parameters.length - 1)
            {
                System.out.println("please input the path to redirect the file");
                return;
            }
            if (ReDirect == 2)// submitTask TaskID < TaskPath
            {
                if (check('=', 4, "logged", "All", parameters)) return;
                if (LoggedUser.UserType != 1)
                {
                    System.out.println("operation not allowed");
                    return;
                }
                ((Student) LoggedUser).submitTask(parameters[1], parameters[3]);
            }
            else if (ReDirect == 3)// submitTask TaskPath TaskID < TaskPath
            {
                if (check('=', 5, "logged", "All", parameters)) return;
                if (LoggedUser.UserType != 1)
                {
                    System.out.println("operation not allowed");
                    return;
                }
                ((Student) LoggedUser).submitTask(parameters[2], parameters[1]);
            }
            else
            {
                System.out.println("arguments illegal");
            }
        }
    }

    public void addAnswer(String[] parameters)
    {
        int ReDirect = FileOperate.getPosition(parameters, "<");
        if (ReDirect == -1)// addAnswer AnswerPath TaskID
        {
            if (check('=', 3, "logged", "User.Admin", parameters)) return;
            ((Admin) LoggedUser).addAnswer(parameters[2], parameters[1]);
        }
        else
        {
            if (ReDirect == parameters.length - 1)
            {
                System.out.println("please input the path to redirect the file");
                return;
            }
            if (ReDirect == 2)// addAnswer TaskID < AnswerPath
            {
                if (check('=', 4, "logged", "User.Admin", parameters)) return;
                ((Admin) LoggedUser).addAnswer(parameters[1], parameters[3]);
            }
            else if (ReDirect == 3)// submitTask AnswerPath TaskID < AnswerPath
            {
                if (check('=', 5, "logged", "User.Admin", parameters)) return;
                ((Admin) LoggedUser).addAnswer(parameters[2], parameters[1]);
            }
            else
            {
                System.out.println("arguments illegal");
            }
        }
    }

    public void queryScore(String[] parameters)
    {
//        System.out.println("queryScore");
        if (parameters.length == 1)//queryScore
        {
            if (check('=', 1, "logged", "All", parameters)) return;
            LoggedUser.queryScore();
        }
        else if (parameters.length == 2)//queryScore TaskID /queryScore StudentID
        {
            if (check('=', 2, "logged", "All", parameters)) return;
            LoggedUser.queryScore(parameters[1]);
        }
        else if (parameters.length == 3)//queryScore TaskID StudentID
        {
            if (check('=', 3, "logged", "All", parameters)) return;
            LoggedUser.queryScore(parameters[1], parameters[2]);
        }
        else
        {
            System.out.println("arguments illegal");
        }
    }

    public void requestVM(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Student", parameters)) return;
        ((Student) LoggedUser).requestVM(parameters[1]);
    }

    public void startVM(String[] parameters)
    {
        if (check('=', 1, "logged", "User.Student", parameters)) return;
        ((Student) LoggedUser).startVM();
    }

    public void clearVM(String[] parameters)
    {
        if (check('=', 2, "logged", "User.Teacher", parameters)) return;
        ((Teacher) LoggedUser).clearVM(Integer.parseInt(parameters[1]));
    }
    public void logVM(String[] parameters)
    {
        if (check('=', 1, "logged", "User.Student", parameters)) return;
        ((Student) LoggedUser).logVM();
    }
    public void uploadVM(String[] parameters) throws IOException
    {
        if (check('=', 2, "logged", "User.Student", parameters)) return;
        ((Student) LoggedUser).uploadVM(parameters[1]);
    }
    public void downloadVM(String[] parameters) throws IOException,ClassNotFoundException
    {
        if (check('=', 2, "logged", "User.Student", parameters)) return;
        ((Student) LoggedUser).downloadVM(parameters[1]);
    }
}