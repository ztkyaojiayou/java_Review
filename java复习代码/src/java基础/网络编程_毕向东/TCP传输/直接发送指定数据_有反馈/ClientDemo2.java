package java基础.网络编程_毕向东.TCP传输.直接发送指定数据_有反馈;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//版本2：使用tcp实现相互通信
//1.发送端/客户端
public class ClientDemo2 {

    /**
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        //客户端发数据到服务端
        /*
         * Tcp传输，客户端建立的过程。
         * 1，创建tcp客户端socket服务。使用的是Socket对象。
         * 		建议该对象一创建就明确目的地。要连接的主机。
         * 2，如果连接建立成功，说明数据传输通道已建立。
         * 		该通道就是socket流 ,是底层建立好的。 既然是流，说明这里既有输入，又有输出。
         * 		想要输入或者输出流对象，可以找Socket来获取。
         * 		可以通过getOutputStream(),和getInputStream()来获取两个字节流。
         * 3，使用输出流，将数据写出。
         * 4，关闭资源。
         */

        //1.创建socket对象
        Socket socket = new Socket("192.168.1.100",10002);//发往目标端口

        //Socket类提供了获取输入输出流的方法，直接使用即可(这是实现双向通信的基础和关键）
        //2.1写出去/存储到硬盘，供服务端接收，则需要使用输出流
        OutputStream out = socket.getOutputStream();
        out.write("tcp演示：哥们又来了!".getBytes());

        //2.2读取服务端返回的数据,则需要使用socket读取流。
        InputStream in = socket.getInputStream();
        byte[] buf = new byte[1024];

        int len = in.read(buf);

        String  text = new String(buf,0,len);

        System.out.println(text);

        //关闭资源。
        socket.close();






    }

}

