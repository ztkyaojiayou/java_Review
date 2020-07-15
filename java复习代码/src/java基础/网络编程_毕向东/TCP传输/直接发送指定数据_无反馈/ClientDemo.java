package java基础.网络编程_毕向东.TCP传输.直接发送指定数据_无反馈;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//版本1：使用tcp实现发送和接收指定数据（直接发送指定数据_无反馈，单向通信）
//1.发送端/客户端：发送数据
public class ClientDemo {

    /**
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        //客户端发数据到服务端
        /*
         * Tcp传输，客户端建立的过程。
         * 1，创建tcp客户端socket服务。使用的是Socket对象，利用Socket类创建。
         * （注意：与udp不同，tcp中的服务端和客户端创建socket对象所使用的对象是不同的，客户端为Socket类，而服务端则为ServerSocket类）
         * 		建议该对象一创建就明确目的地。要连接的主机。
         * 2，如果连接建立成功，说明数据传输通道已建立。
         * 		该通道就是socket流 ,是底层建立好的。 既然是流，说明这里既有输入，又有输出。
         * 		想要输入或者输出流对象，可以找Socket来获取。
         * 		可以通过getOutputStream(),和getInputStream()来获取两个字节流。
         * 3，使用输出流，将数据写出。
         * 4，关闭资源。
         */

        //创建客户端socket服务。
        /**
         * public Socket(InetAddress address,int port) throws IOException
         * 创建流套接字并将其连接到指定IP地址的指定端口号。
         */
        Socket socket = new Socket("192.168.1.100",10002);//注意，这里的ip和端口号是目标ip和端口号，而不是自己的（这和udp不同）

        //获取socket流中的输出流。
        OutputStream out = socket.getOutputStream();


        //使用输出流将指定的数据写到硬盘上。
        out.write("tcp演示：哥们又来了!".getBytes());

        //关闭资源。
        socket.close();






    }

}

