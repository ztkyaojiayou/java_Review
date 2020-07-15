package java基础.IO流_毕向东.字节流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//案例：复制一个MP3文件（字节流）（掌握）
public class CopyMp3Test {

    //方法1：最原始的方法，千万不要用，没有效率！
    public static void copy_1() throws IOException {
        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        FileOutputStream fos = new FileOutputStream("c:\\4.mp3");

        int ch = 0;

        while((ch =fis.read())!=-1){
            fos.write(ch);
        }

        fos.close();
        fis.close();
    }

    //方法2：使用一个临时缓存数组区，且利用available()方法来指定该数组的大小，思路很好但不建议使用。（不推荐）
    public static void copy_2() throws IOException {
        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        FileOutputStream fos = new FileOutputStream("c:\\3.mp3");

        byte[] buf = new byte[fis.available()];//即数据有多大我就设置多大
        fis.read(buf);
        fos.write(buf);
        fos.close();
        fis.close();
    }

    //方法3：使用一个临时缓存数组区，但该数组的大小指定为1024（推荐）
    public static void copy_3() throws IOException {

        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        FileOutputStream fos = new FileOutputStream("c:\\1.mp3");

        byte[] buf = new byte[1024];

        int len = 0;

        while((len=fis.read(buf))!=-1){
            fos.write(buf,0,len);
        }

        fos.close();
        fis.close();
    }

    //方法4：使用缓存流（推荐，和字符流的逻辑相同）
    public static void copy_4() throws IOException {

        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        BufferedInputStream bufis = new BufferedInputStream(fis);//用于读

        FileOutputStream fos = new FileOutputStream("c:\\2.mp3");
        BufferedOutputStream bufos = new BufferedOutputStream(fos);//用于写

        int ch = 0;

        while((ch=bufis.read())!=-1){
            bufos.write(ch);
        }

        bufos.close();
        bufis.close();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        copy_4();

    }
}

