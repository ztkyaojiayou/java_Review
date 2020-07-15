package java基础.网络编程_毕向东.UDP传输.直接发送指定数据;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//版本1：直接发送指定数据_无反馈
//发送端
public class UDPSendDemo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("发送端启动......");
        /*
         * 创建UDP传输的发送端。
         * 思路：
         * 1，建立udp的socket服务。
         * 2，将要发送的数据封装到数据包中。
         * 3，通过udp的socket服务将数据包发送出去。
         * 4，关闭socket服务。
         */
        //1,udpsocket服务。使用DatagramSocket类来创建Socket对象。
        //注意：在UDP中，发送端和接收端的socket对象都由同一个类创建，即DatagramSocket，但在TCP中则不同，后面会讲到。
        /**
         * public DatagramSocket(int port) throws SocketException
         * 构造数据报套接字并将其绑定到本地主机上的指定端口。套接字将被绑定到wildcard地址，一个由内核选择的IP地址。
         * 如果有安全管理器， checkListen方法首先被调用与port作为其参数，以确保允许该操作。 这可能会导致SecurityException。
         * 参数
         * port - 要使用的端口。
         */
        DatagramSocket ds = new DatagramSocket(8888);

        //2,将要发送的数据封装到数据包中。
        String str = "udp传输演示：哥们来了！";
        //使用DatagramPacket将数据封装到的该对象包中。
        byte[] buf = str.getBytes();

        /**
         * public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
         * 构造用于发送长度的分组的数据报包length指定主机上到指定的端口号。 length参数必须小于或等于buf.length 。
         * 参数
         * buf - 分组数据。
         * length - 包长度。
         * address - 目的地址。
         * port - 目的端口号。（重点）
         */
        DatagramPacket dp =
                new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.100"),10000);//往10000端口发送数据


        //3，通过udp的socket服务将数据包发送出去。使用send方法。
        ds.send(dp);

        //4，关闭资源。
        ds.close();

    }

}

