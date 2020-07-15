package java基础.网络编程_毕向东.UDP传输.模拟微信聊天;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

//接收端（但好像并没有写“再发送”的代码？？？）
public class Rece implements Runnable {

    private DatagramSocket ds;//传入一个Socket对象

    public Rece(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {//包装成一个线程任务
        try {
            while (true) {//表示该线程会一直执行，即一直接收另外一个线程从键盘上输入的数据，直到其退出录入

                // 2,创建数据包。
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);

                // 3,使用接收方法将数据存储到数据包中。
                ds.receive(dp);//阻塞式的。

                // 4，通过数据包对象的方法，解析其中的数据,比如，地址，端口，数据内容。
                String ip = dp.getAddress().getHostAddress();
                int port = dp.getPort();
                String text = new String(dp.getData(), 0, dp.getLength());

                System.out.println(ip + "::" + text);
                if(text.equals("over")){
                    System.out.println(ip+"....退出聊天室");
                }

            }
        } catch (Exception e) {

        }

    }

}

