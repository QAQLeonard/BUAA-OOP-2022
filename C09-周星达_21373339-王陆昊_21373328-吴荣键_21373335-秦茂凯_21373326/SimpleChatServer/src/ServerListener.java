import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 服务端
 * 监听客户端请求
 */
public class ServerListener extends Thread{
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(2347);
            while(true){
                Socket linkSocket = serverSocket.accept();//将使Server端的程序处于等待状态，程序将一直阻塞直到捕捉到一个来自Client端的请求，并返回一个用于与该Client通信的Socket对象Link-Socket
                ChatSocket chatSocket = new ChatSocket(linkSocket);
                DataInputStream in = new DataInputStream(linkSocket.getInputStream());
                String name = in.readUTF();
                chatSocket.start();
                ChatManager.getCm().add(name,chatSocket);
                ChatManager.getCm().onLine(name);
                System.out.print(name);
                System.out.println("Link Success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
