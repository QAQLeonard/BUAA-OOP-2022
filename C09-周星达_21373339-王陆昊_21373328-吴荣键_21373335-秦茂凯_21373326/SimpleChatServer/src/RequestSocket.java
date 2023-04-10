import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/*
 * 服务端
 */
public class RequestSocket extends Thread
{//客户端请求处理类
    Socket socket;

    public RequestSocket(Socket s)
    {
        socket = s;
    }

    public void out(String Msg)
    {
        try
        {
            new DataOutputStream(socket.getOutputStream()).writeUTF(Msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            DataInputStream in = new DataInputStream(socket.getInputStream());//获取输入流
            String line;
            try
            {
                while ((line = in.readUTF()) != null)
                {// 读取客户端发送的消息
                    System.out.println(line);// 打印消息
                    String str[] = line.split(" ");// 拆分消息
                    String I_account = str[0];// 发送者
                    String Y_account = str[1];// 接收者
                    String Msg = "";
                    for (int i = 2; i < str.length; i++)
                    {// 消息内容
                        Msg += str[i] + " ";
                    }
                    // 发送消息
                    if (I_account.equals("####"))
                    {
                        RequestManager.getCm().remove(Y_account);// 移除下线用户
                        RequestManager.getCm().offLine(Y_account);
                        System.out.println(Y_account + "has logout!");
                    }
                    else if (I_account.equals("###@"))
                    {
                        RequestManager.getCm().sendMsg(I_account, Y_account, Msg);
                    }
                    else if (I_account.equals("##@@"))
                    {
                        RequestManager.getCm().sendMsg(I_account, Y_account, Msg);
                    }
                    else
                    {
                        if (Msg.equals("####") || Msg.equals("###@") || Msg.equals("##@@") || Msg.equals("#@@@"))
                            Msg += ".";
                        RequestManager.getCm().sendMsg(I_account, Y_account, Msg);
                    }
                }
            }
            catch (SocketException e)
            {
//                e.printStackTrace();
            }

            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


