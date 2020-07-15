package java基础.IO流_毕向东.字符流.缓冲字符流;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//使用缓冲流来读/显示，即先把数据读到缓存，再用其提供的一个特殊方法readline来一行一行的读，以提高效率
public class BufferedReaderDemo {
//方法2：采用特殊方法readline来读
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("buf.txt");//目标文件

        BufferedReader bufr = new BufferedReader(fr);//先存入缓存流

        String line = null;

        while((line=bufr.readLine())!=null){//再使用其特有方法readline一行一行的读出来，返回的是该行内容的一个字符串（重点）
            System.out.println(line);
        }
		/*
		String line1 = bufr.readLine();
		System.out.println(line1);
		String line2 = bufr.readLine();
		System.out.println(line2);
		String line3 = bufr.readLine();
		System.out.println(line3);
		String line4 = bufr.readLine();
		System.out.println(line4);
		String line5 = bufr.readLine();
		System.out.println(line5);
		*/


        bufr.close();


    }

    //方法1（普通方法）
    /**
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void demo() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("buf.txt");

        char[] buf = new char[1024];

        int len = 0;
        while((len=fr.read(buf))!=-1){
            System.out.println(new String(buf,0,len));//普通的输出方法，即一个一个读到一个string对象中，再一次性打印出来
        }

        fr.close();
    }

}

