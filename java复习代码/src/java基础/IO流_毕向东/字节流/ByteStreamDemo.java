package java基础.IO流_毕向东.字节流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//字节流（可用于传输任意类型的文件，因为任何文件都是由字节构成）
public class ByteStreamDemo {

    //1.读=读进/输入/显示/get到内存=InputStream
    public static void demo_read() throws IOException {

        //1，创建一个读取流对象。和指定文件关联。
        FileInputStream fis = new FileInputStream("bytedemo.txt");//要读取的文件

        //2.读取一个字节的方法。
//		int ch = fis.read();
//		System.out.println(ch);

        //3.1全部读取出来的方法：使用while循环
//		int ch = 0;
//		while((ch=fis.read())!=-1){
//			System.out.println((char)ch);
//		}

        //3.2全部读取方法的改进版：创建一个临时缓存区（建议使用）
//		byte[] buf = new byte[1024];//当该数组不知道该设为多大时，一般就取1024
//		int len = 0;
//		while((len=fis.read(buf))!=-1){
//			System.out.println(new String(buf,0,len));
//		}

        //3.3全部读取方法的最终版：再创建临时缓存区的前提下，使用available()方法来确定该数组的大小（但不建议使用，不知道为啥？）
//		System.out.println(fis.available());//该方法就是用于统计该输入流中要读取（或跳过）的估计剩余字节数，
//		易知，当第一次调用时，其结果即为该流的大小。
//		byte[] buf = new byte[fis.available()];
//		fis.read(buf);
//		System.out.println(new String(buf));

        //4.关闭该流对象（必须）
        fis.close();

    }

    //2.写=写出/输出/存储/add到硬盘/文件=OutputStream
    public static void demo_write() throws IOException {

        //1.创建字节输出流对象。用于操作文件.
        FileOutputStream fos = new FileOutputStream("bytedemo.txt");

        //2.写数据。直接写入到了目的地中。
        fos.write("abcdefg".getBytes());//因为该文件中的数据为字符，因此要先使用getBytes()方法把其转换为字节数据，才可以在字节流中传输

        //3.主动刷新（非必须）并关闭该流（必须）
//		fos.flush();//这一步可以不写，因为执行close时会先执行这一步
        fos.close();//关闭资源动作要完成。（这一步是必须的）
    }

    //测试
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        demo_read();
        demo_write();
    }

}

