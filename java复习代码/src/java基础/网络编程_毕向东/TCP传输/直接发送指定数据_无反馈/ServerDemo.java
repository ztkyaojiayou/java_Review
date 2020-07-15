package java基础.网络编程_毕向东.TCP传输.直接发送指定数据_无反馈;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


//2.服务端/接收端：接收数据
public class ServerDemo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//		服务端接收客户端发送过来的数据，并打印在控制台上。
        /*
         * 建立tcp服务端的思路：
         * 1，创建服务端socket服务。通过ServerSocket对象。
         * 2，服务端必须对外提供一个端口，否则客户端无法连接。
         * 3，获取连接过来的客户端对象。
         * 4，通过客户端对象获取socket流读取客户端发来的数据
         * 		并打印在控制台上。
         * 5，关闭资源。关客户端，关服务端。
         */

        //1创建服务端对象。使用的是ServerSocket类来创建的
        ServerSocket ss = new ServerSocket(10002);//这个端口号是自己的

        //2,获取连接过来的客户端对象。
        Socket s = ss.accept();//阻塞式.

        String ip = s.getInetAddress().getHostAddress();

        //3，通过socket对象获取输入流，要读取客户端发来的数据
        InputStream in = s.getInputStream();

        byte[] buf = new byte[1024];

        int len = in.read(buf);
        String text = new String(buf,0,len);
        System.out.println(ip+":"+text);

        s.close();
        ss.close();

    }

}

