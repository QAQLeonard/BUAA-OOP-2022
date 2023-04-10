package Model;

import Controller.Controller;
import Model.Data.MsgData;
import View.MainWindow;

import javafx.scene.control.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class ChatManager
{
    private String ip;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainWindow mainWindow;

    private ChatManager()
    {
    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance()
    {
        return instance;
    }

    public void setMainWindow(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }

    public void connect(String ip, String account)
    {
        this.ip = ip;
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    socket = new Socket(ip, 2347);
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(account);
                    String line;
                    while ((line = in.readUTF()) != null)
                    {
                        System.out.println(line);
                        String[] str = line.split(" ");
                        String I_account = str[0];
                        String Y_account = str[1];
                        StringBuilder Msg = new StringBuilder();
                        for (int i = 2; i < str.length; i++)
                        {
                            Msg.append(str[i]).append(" ");
                        }
                        //获取信息格式
                        try
                        {
                            //别人把你添加为好友的信息
                            switch (Msg.toString())
                            {
                                case "###@ ":
                                    System.out.println("别人把你添加为好友的信息");
                                    ResultSet set = Controller.database.execResult("SELECT head FROM user WHERE account = ?", I_account);
                                    set.next();
                                    mainWindow.addFriend(set.getString("head"), I_account, I_account, Controller.database, Controller.friendPage);
                                    MsgData.accountList.add(I_account);
                                    MsgData.msg.add(new Vector<>());
                                    mainWindow.friendVector.get(MsgData.accountList.size() - 1).setOnline();
                                    if (((Label) mainWindow.$("Y_account")).getText().equals("SimpleChat聊天助手"))
                                    {
                                        String msg = I_account + "把你添加为好友,和他聊天吧";
                                        mainWindow.addLeft("system", msg);
                                        MsgData.msg.get(0).add("SimpleChat聊天助手 " + msg);
                                    }
                                    else
                                    {
                                        String msg = I_account + "把你添加为好友,和他聊天吧";
                                        MsgData.msg.get(0).add("SimpleChat聊天助手 " + msg);
                                        int cnt = MsgData.msgTip.get("SimpleChat聊天助手");
                                        cnt++;
                                        MsgData.msgTip.put("SimpleChat聊天助手", cnt);
                                        mainWindow.friendVector.get(0).addMsgTip(cnt);
                                    }
                                    MsgData.msgTip.put(I_account, 0);
                                    break;
                                case "##@@ ":
//别人把你删除的信息

                                    System.out.println("别人把你删除的信息");
                                    if (((Label) mainWindow.$("Y_account")).getText().equals("SimpleChat聊天助手"))
                                    {
                                        String msg = I_account + "已把你删除";
                                        mainWindow.addLeft("system", msg);
                                        MsgData.msg.get(0).add("SimpleChat聊天助手 " + msg);
                                        int index = MsgData.accountList.indexOf(I_account);
                                        if (index != -1)
                                        {
                                            mainWindow.friendVector.remove(index);
                                            ((ListView) mainWindow.$("FriendList")).getItems().remove(index);
                                            MsgData.accountList.remove(index);
                                            MsgData.msg.remove(index);
                                            MsgData.MsgMap.remove(I_account);
                                        }
                                    }
                                    else
                                    {
                                        String msg = I_account + "已把你删除";
                                        MsgData.msg.get(0).add("SimpleChat聊天助手 " + msg);
                                        int cnt = MsgData.msgTip.get("SimpleChat聊天助手");
                                        cnt++;
                                        MsgData.msgTip.put("SimpleChat聊天助手", cnt);
                                        mainWindow.friendVector.get(0).addMsgTip(cnt);
                                        if (((Label) mainWindow.$("Y_account")).getText().equals(I_account))
                                        {
                                            mainWindow.friendList.getSelectionModel().select(0);
                                        }
                                        int i = MsgData.accountList.indexOf(I_account);
                                        if (i != -1)
                                        {
                                            mainWindow.friendVector.remove(i);
                                            ((ListView) mainWindow.$("FriendList")).getItems().remove(i);
                                            MsgData.accountList.remove(i);
                                            MsgData.msg.remove(i);
                                            MsgData.MsgMap.remove(I_account);
                                        }
                                    }

                                    break;
                                case "#@@@ ":
                                {//有用户上线的信息
                                    System.out.println("有用户上线的信息");
                                    int i = MsgData.accountList.indexOf(I_account);
                                    if (i != -1)
                                    {
                                        mainWindow.friendVector.get(i).setOnline();
                                    }
                                    break;
                                }
                                case "@@@@ ":
//用户下线信息
                                {
                                    System.out.println("用户下线信息");
                                    int i = MsgData.accountList.indexOf(I_account);
                                    if (i != -1)
                                    {
                                        mainWindow.friendVector.get(i).setOutline();
                                    }
                                    break;
                                }
                                default: //一般信息
                                    System.out.println("YIBAN " + Msg);
                                    if (MsgData.accountList.get(mainWindow.friendList.getSelectionModel().getSelectedIndex()).equals(I_account))
                                    {
                                        ResultSet resultSet = Controller.database.execResult("SELECT head FROM user WHERE account = ?", I_account);
                                        resultSet.next();
                                        mainWindow.addLeft(resultSet.getString("head"), Msg.toString());
                                        int i = MsgData.accountList.indexOf(I_account);
                                        if (i != -1) MsgData.msg.get(i).add(I_account + " " + Msg);
                                    }
                                    else
                                    {
                                        int i = MsgData.accountList.indexOf(I_account);
                                        if (i != -1)
                                        {
                                            MsgData.msg.get(i).add(I_account + " " + Msg);
                                            int cnt = MsgData.msgTip.get(I_account);
                                            cnt++;
                                            MsgData.msgTip.put(I_account, cnt);
                                            mainWindow.friendVector.get(i).addMsgTip(cnt);
                                        }
                                    }
                                    break;
                            }
                        }
                        catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    in.close();
                    out.close();
                    in = null;
                    out = null;
                }
                catch (IOException e)
                {
                    System.out.println("好吧");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void send(String Msg) throws Exception
    {//发送消息  向服务器发送
        if (out != null)
        {
            out.writeUTF(Msg);
            out.flush();
        }
        else
        {
            Controller.alert.setInformation("发送失败!");
            Controller.alert.exec();
        }
    }
}
