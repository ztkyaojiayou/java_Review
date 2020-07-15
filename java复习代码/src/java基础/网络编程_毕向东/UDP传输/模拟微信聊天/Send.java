package java基础.网络编程_毕向东.UDP传输.模拟微信聊天;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//发送端（至于代码逻辑，与之前的没什么差别，无非就是把它封装成了一个线程任务）
public class Send implements Runnable {

    private DatagramSocket ds;//传入一个Socket对象

    public Send(DatagramSocket ds){
        this.ds = ds;
    }

    @Override
    public void run() {//包装成一个线程任务

        try {
            //从键盘录入（常用写法）
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            while((line=bufr.readLine())!=null){


                byte[] buf = line.getBytes();
                //把数据封装成数据包（这是简写）
                DatagramPacket dp =
                        new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.255"),10001);
                ds.send(dp);//发送出去

                if("over".equals(line))//若输入为“over”，则结束录入
                    break;
            }

            ds.close();
        } catch (Exception e) {
        }
    }

}

