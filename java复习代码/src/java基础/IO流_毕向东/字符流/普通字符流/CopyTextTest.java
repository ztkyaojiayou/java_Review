package java基础.IO流_毕向东.字符流.普通字符流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 需求：作业：将c盘的一个文本文件复制到d盘。（要掌握）
 *
 * 思路：
 * 1，需要读取源，
 * 2，将读到的源数据写入到目的地。
 * 3，既然是操作文本数据，使用字符流。
 *
 */

public class CopyTextTest {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //1,读取一个已有的文本文件，使用字符读取流和文件相关联。
        FileReader fr = new FileReader("IO流_2.txt");
        //2,创建一个目的，用于存储读到数据。
        FileWriter fw = new FileWriter("要复制的目的地.txt");
        //3,频繁的读写操作。（其实效率是很低的，后面会使用缓冲流）
        int ch = 0;
        while((ch=fr.read())!=-1){//表示还没有到最末尾
            fw.write(ch);
        }
        //4,关闭流资源（读取流和写流都要关闭）。
        fw.close();
        fr.close();
    }

}

