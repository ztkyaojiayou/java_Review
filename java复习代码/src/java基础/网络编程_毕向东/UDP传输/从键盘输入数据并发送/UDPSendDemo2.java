package java基础.网络编程_毕向东.UDP传输.从键盘输入数据并发送;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//版本2：从键盘输入数据并发送
//发送端
public class UDPSendDemo2 {

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
        //1,udpsocket服务。使用DatagramSocket对象。
        DatagramSocket ds = new DatagramSocket(8888);//发送端指定的端口号


//		String str = "udp传输演示：哥们来了！";
        //改为从键盘录入即可
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        while((line=bufr.readLine())!=null){


            byte[] buf = line.getBytes();
            DatagramPacket dp =
                    new DatagramPacket(buf,buf.length, InetAddress.getByName("192.168.1.100"),10000);//发给端口号为10000的接收端
            ds.send(dp);
            if("over".equals(line))//即当录入为over时，退出录入
                break;
        }

        //4，关闭资源。
        ds.close();


    }

}
