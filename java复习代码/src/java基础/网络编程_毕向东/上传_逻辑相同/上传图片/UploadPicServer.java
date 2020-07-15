package java基础.网络编程_毕向东.上传_逻辑相同.上传图片;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//接收端/服务端
public class UploadPicServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //创建tcp的socket服务端。
        ServerSocket ss = new ServerSocket(10006);

        while(true){
            Socket s = ss.accept();

            new Thread(new UploadTask(s)).start();

        }
        //获取客户端。

//		ss.close();


    }

}

