package java基础.IO流_毕向东.字符流.缓冲字符流;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//使用缓冲流来写/存储，其中也有一个特殊方法newLine，其目的就是换行
public class BufferedWriterDemo {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //先创建一个写入流，指定要写入的目标文件
        FileWriter fw = new FileWriter("buf.txt");

        //为了提高写入的效率。使用了字符流的缓冲区。
        //创建了一个字符写入流的缓冲区对象，并和指定要被缓冲的流对象相关联（即把刚才创建的写入流塞进缓冲流，最后使用缓冲流来操作）
        BufferedWriter bufw = new BufferedWriter(fw);

        //使用缓冲区的写入方法将数据先写入到缓冲区中。
        //方法1：一行一行存，没存完一行就调用newLine方法换行
//		bufw.write("abcdefq"+LINE_SEPARATOR+"hahahha");
//		bufw.write("xixiixii");
//		bufw.newLine();
//		bufw.write("heheheheh");
        //使用缓冲区的刷新方法将数据刷到目的地中。
//		bufw.flush();

        //方法2：也是一行一行存，不过使用了for循环
        for(int x=1; x<=4; x++){
            bufw.write("abcdef"+x);
            bufw.newLine();//换行
            bufw.flush();//使用缓冲区的刷新方法将数据刷到目的地中。
        }
        //关闭缓冲区。其实关闭的就是被缓冲的流对象。
        bufw.close();

//		fw.write("hehe");

//		fw.close();
    }

}


