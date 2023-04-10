import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * 客户端请求管理类
 */
public class RequestManager
{
    private RequestManager()
    {
    }

    private static final RequestManager cm = new RequestManager();

    public static RequestManager getCm()
    {
        return cm;
    }

    Map<String, RequestSocket> map = new HashMap<>();//账号名映射客户端请求

    public void add(String name, RequestSocket requestSocket)
    {
        map.put(name, requestSocket);
    }

    public void remove(String name)
    {
        map.remove(name);
    }

    //发消息
    public void sendMsg(String from, String to, String Msg) throws IOException
    {
        for (Map.Entry<String, RequestSocket> entry : map.entrySet())
        {
            RequestSocket socket = entry.getValue();
//                    if(entry.getKey().equals(to))
            socket.out(from + " " + to + " " + Msg);
        }
    }

    //上线
    public void onLine(String dialogName)
    {
        for (Map.Entry<String, RequestSocket> entry : map.entrySet())
        {
            RequestSocket socket = entry.getValue();
            if (!entry.getKey().equals(dialogName))
            {
                socket.out(dialogName + " #### #@@@");
            }
        }

    }

    //下线
    public void offLine(String name)
    {

        for (Map.Entry<String, RequestSocket> entry : map.entrySet())
        {
            RequestSocket socket = entry.getValue();
            if (!entry.getKey().equals(name))
            {
                socket.out(name + " #### @@@@");
            }
        }
    }
}