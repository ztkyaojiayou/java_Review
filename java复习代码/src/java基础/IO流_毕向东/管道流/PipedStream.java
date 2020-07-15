package java基础.IO流_毕向东.管道流;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//管道流
/**
 * 管道输入流：PipedInputStream
 * 管道输入流应该连接到管道输出流；管道输入流提供要写入管道输出流的所有数据字节。
 * 通常，数据由某个线程从 PipedInputStream 对象读取，并由其他线程将其写入到相应的 PipedOutputStream。
 * 不建议对这两个对象尝试使用单个线程，因为这样可能死锁线程。
 * 管道输入流包含一个缓冲区，可在缓冲区限定的范围内将读操作和写操作分离开。（核心）
 * 如果向连接管道输出流提供数据字节的线程不再存在，则认为该管道已损坏。
 */
// 输入/读取/显示
class Input implements Runnable{

    private PipedInputStream in;//管道输入流
    Input(PipedInputStream in){
        this.in = in;
    }
    public void run(){//显示

        try {
            byte[] buf = new byte[1024];
            int len = in.read(buf);

            String s = new String(buf,0,len);

            System.out.println("s="+s);
            in.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

/**
 * 管道输出流：PipedOutputStream
 * 可以将管道输出流连接到管道输入流来创建通信管道。管道输出流是管道的发送端。
 * 通常，数据由某个线程写入 PipedOutputStream 对象，并由其他线程从连接的 PipedInputStream 读取。
 * 不建议对这两个对象尝试使用单个线程，因为这样可能会造成该线程死锁。
 * 如果某个线程正从连接的管道输入流中读取数据字节，但该线程不再处于活动状态，则该管道被视为处于 毁坏 状态。
 */
//输出/写出/存储
class Output implements Runnable{
    private PipedOutputStream out;//管道输出流
    Output(PipedOutputStream out){
        this.out = out;
    }
    public void run(){//存储

        try {
            Thread.sleep(5000);
            out.write("hi，管道来了！".getBytes());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
//测试
public class PipedStream {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream();

        input.connect(output);

        //创建两个线程并启动（目的：一个线程读/显示，一个线程写/存储）
        new Thread(new Input(input)).start();
        new Thread(new Output(output)).start();

    }

}

