package java基础.IO流_毕向东.字符流.缓冲字符流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//案例：也是复制一个文件，不过这里直接使用缓冲流来进行（推荐）
public class CopyTextByBufTest {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//1.先创建一个读流，同时指定要复制的文件
        FileReader fr = new FileReader("buf.txt");
        //2.再创建一个缓冲的读流，把刚才创建的读流塞进来
        BufferedReader bufr = new BufferedReader(fr);
//3.创建一个写流，同时指定目的地
        FileWriter fw = new FileWriter("目标地址.txt");
        //4.再创建一个缓冲的写流，同时把刚才创建的写流塞进来
        BufferedWriter bufw = new BufferedWriter(fw);
        //5.开始使用缓冲的读流和写流来操作啦（关键）
        String line = null;
        while((line=bufr.readLine())!=null){//使用缓冲读流的特有方法readLine()来实现一行一行的读（之前讲过的），返回的是该行内容的一个字符串
            bufw.write(line);//再写入该字符串
            bufw.newLine();//这是缓冲写流中的特殊方法，即换行
            //6.最后，刷新写流，同时把所有的流都关闭
            bufw.flush();//6.1刷新该流中的缓存（但其实可以不用专门刷新，因为当直接使用close方法时，也会先进行流的刷新）
        }

		/*
		//这是最原始的方法，即一个一个字符直接读到目标文件中，效率极低，非常不推荐，
		//再怎么着也可以使用一个临时缓存区（之前讲过）
		int ch = 0;

		while((ch=bufr.read())!=-1){

			bufw.write(ch);
		}
		*/
		//关闭此流，同样遵循先使用的后关闭
        bufw.close();//6.1关闭写流，注意：在关闭时要先刷新它。
        // 且在关闭该流之后，再调用 write() 或 flush() 将导致抛出 IOException。同时，关闭以前关闭的流是无效的。
        bufr.close();//6.2再关闭读流
    }



}

