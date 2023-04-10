package Resourse;

import Command.Commands;
import User.Student;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

import static java.lang.Math.min;

public class Task
{
    public String TaskName;
    public String TaskID;
    public LocalDateTime StartTime;
    public LocalDateTime EndTime;
    public int ReceiveNum;
    public TreeMap<String, ArrayList<SCORE>> ReceiveTasks = new TreeMap<>();
    String TaskPath;
    public ArrayList<String> Answer = new ArrayList<>();
    public String Course_ID;

    public Task(String ID, String name, LocalDateTime begin, LocalDateTime end, String path, String Course_ID)
    {
        this.TaskID = ID;
        this.TaskName = name;
        this.StartTime = begin;
        this.EndTime = end;
        this.TaskPath = path;
        this.Course_ID = Course_ID;
        ReceiveNum = 0;
    }

    public String getStartTime()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        return StartTime.format(formatter);
    }

    public String getEndTime()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        return EndTime.format(formatter);
    }

    public void submitWork(String StudentID, String WorkPath)
    {
        File srcFile = new File(WorkPath);
        File destFile = new File("." + File.separator + "data" + File.separator + Course_ID + File.separator + "tasks" + File.separator + TaskID + File.separator + StudentID + ".task");
        if (!destFile.getParentFile().exists())
        {
            try
            {
                Files.createDirectories(destFile.toPath().getParent());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (destFile.exists())
        {
            System.out.println("task already exists, do you want to overwrite it? (y/n)");
            String str = Commands.sc.nextLine();
            if (!(str.equals("y") || str.equals("Y")))
            {
                System.out.println("submit canceled");
                return;
            }
        }

        ArrayList<String> temp = new ArrayList<>();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(srcFile));
            String str;
            while ((str = in.readLine()) != null)
            {
                temp.add(str);
            }
        }
        catch (Exception e)
        {
            System.out.println("file operation failed");
            return;
        }
        try
        {
            FileOperate.copyFileUsingJava7Files(srcFile.getPath(), destFile.getPath());
        }
        catch (IOException e)
        {
            System.out.println("file operation failed");
            return;
        }

        String score = Mark(temp);
        SCORE tempScore = new SCORE(StudentID, score, temp);
        System.out.println("submit success");
        System.out.println("your score is: " + score);

        if (ReceiveTasks.containsKey(StudentID))//have already submitted the task
        {
            ReceiveTasks.get(StudentID).add(tempScore);
        }
        else
        {
            ArrayList<SCORE> tempScoreList = new ArrayList<>();
            tempScoreList.add(tempScore);
            ReceiveTasks.put(StudentID, tempScoreList);
            ReceiveNum++;
        }

    }

    public void addAnswer(String AnswerPath)
    {
        File src = new File(AnswerPath);
        File dest = new File("." + File.separator + "data" + File.separator + Course_ID + File.separator + "answers" + File.separator + TaskID + ".ans");
        if (!src.exists())
        {
            System.out.println("file operation failed");
            return;
        }
        Answer.clear();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(src));
            String str;
            while ((str = in.readLine()) != null)
            {
                Answer.add(str);
            }
        }
        catch (IOException e)
        {
            System.out.println("file operation failed");
            return;
        }
        try
        {
            FileOperate.copyFileUsingJava7Files(AnswerPath, dest.getPath());
        }
        catch (IOException e)
        {
            System.out.println("file operation failed");
            return;
        }
        System.out.println("add answer success");
    }

    private String Mark(ArrayList<String> MarkList)
    {
        if (Answer.isEmpty()) return "None";
        double SCORE = 0.0;
        double ScorePerQuestion = 100.0 / Answer.size();
        int min_Size = min(Answer.size(), MarkList.size());
        for (int i = 0; i < min_Size; i++)
        {
            if (MarkList.get(i).equalsIgnoreCase(Answer.get(i))) SCORE += ScorePerQuestion;
        }
        return String.format("%.1f", SCORE);
    }

    public int showScore(int beginCode)
    {
        if (ReceiveTasks.isEmpty())
        {
            return beginCode;
        }
        Comparator<SCORE> cmp = new MyComparator();
        for (ArrayList<SCORE> temp : ReceiveTasks.values())
        {
            for (SCORE score : temp)
            {
                if (score.Score.equals("None"))
                {
                    score.Score = Mark(score.StudentAnswer);
                }
            }
            temp.sort(cmp);
        }
        ArrayList<SCORE> ScoreList = new ArrayList<>();
        for (ArrayList<SCORE> temp : ReceiveTasks.values())
        {
            ScoreList.add(temp.get(0));
        }

        ScoreList.sort(cmp);

        for (int i = 0; i < ScoreList.size(); i++)
        {
            Student tmpStu = (Student) Commands.USERS.get(ScoreList.get(i).StudentID);
            System.out.println("[" + (beginCode + i) + "] [ID:" + tmpStu.ID + "] [Name:" + tmpStu.LastName + " " + tmpStu.FirstName + "] [Task_ID:" + TaskID + "] [Score:" + ScoreList.get(i).Score + "]");
        }
        ScoreList = null;
        System.gc();
        return beginCode + ReceiveTasks.size();
    }

    public int showScore(String StudentID, int beginCode)
    {
        if (ReceiveTasks.isEmpty())
        {
            return beginCode;
        }
        ArrayList<SCORE> temp = ReceiveTasks.get(StudentID);
        MyComparator cmp = new MyComparator();
        temp.sort(cmp);
        Student tmpStu = (Student) Commands.USERS.get(temp.get(0).StudentID);
        System.out.println("[" + beginCode + "] [ID:" + tmpStu.ID + "] [Name:" + tmpStu.LastName + " " + tmpStu.FirstName + "] [Task_ID:" + TaskID + "] [Score:" + temp.get(0).Score + "]");
        return beginCode + 1;
    }

}

class SCORE
{
    public String StudentID;
    public String Score;

    public ArrayList<String> StudentAnswer;

    public SCORE(String ID, String score, ArrayList<String> StudentAnswer)
    {
        this.StudentID = ID;
        this.Score = score;
        this.StudentAnswer = StudentAnswer;
    }
}

class MyComparator implements Comparator<SCORE>
{
    @Override
    public int compare(SCORE o1, SCORE o2)
    {
        if (o2.Score.equals("None"))//80>70
        {
            return -1;
        }
        else if (o1.Score.equals("None"))
        {
            return 1;
        }
        else
        {
            if (Double.parseDouble(o1.Score) > Double.parseDouble(o2.Score))//80>70
            {
                return -1;
            }
            else if (Double.parseDouble(o1.Score) < Double.parseDouble(o2.Score))
            {
                return 1;
            }
            else
            {
                if (o1.StudentID.compareTo(o2.StudentID) > 0)
                {
                    return 1;
                }
                else if (o1.StudentID.compareTo(o2.StudentID) < 0)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }

}