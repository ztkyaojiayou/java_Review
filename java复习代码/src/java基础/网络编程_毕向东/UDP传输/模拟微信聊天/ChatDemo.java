package java基础.网络编程_毕向东.UDP传输.模拟微信聊天;


import java.io.IOException;
import java.net.DatagramSocket;

//场景：实现相互发信息，类比即时通讯软件，如QQ/微信（掌握）
//使用两个线程，分别代表发送者和接收者
public class ChatDemo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

//创建两个socket对象，一个用于发送，一个用于接收
        DatagramSocket send = new DatagramSocket();
        DatagramSocket rece = new DatagramSocket(10001);
        //创建两个线程，分别执行发送和接收任务，模拟两个人聊天
        new Thread(new Send(send)).start();
        new Thread(new Rece(rece)).start();

    }

}

