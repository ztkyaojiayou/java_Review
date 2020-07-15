package java基础.IO流_毕向东.字符流.普通字符流;

import java.io.FileReader;
import java.io.IOException;

//需求：读取一个文本文件。将读取到的字符打印到控制台.
//先存储到一个数组，再一次性读取出来
public class FileReaderDemo2 {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("demo.txt");

        /*
         * 使用read(char[])读取文本文件数据。
         *
         * 先创建字符数组，用于缓存，再一次性读出来。
         */
        char[] buf = new char[1024];

        int len = 0;

        while((len=fr.read(buf))!=-1){//先读取出来并存入buf数组
            System.out.println(new String(buf,0,len)); //再从该数组中一次性读出来，提高效率。
        }

		/*
		int num = fr.read(buf);//将读取到的字符存储到数组中。
		System.out.println(num+":"+new String(buf,0,num));
		int num1 = fr.read(buf);//将读取到的字符存储到数组中。
		System.out.println(num1+":"+new String(buf,0,num1));
		int num2 = fr.read(buf);//将读取到的字符存储到数组中。
		System.out.println(num2+":"+new String(buf));
		*/

        fr.close();
    }

}

