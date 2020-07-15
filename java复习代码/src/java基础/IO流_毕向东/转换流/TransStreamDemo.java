package java基础.IO流_毕向东.转换流;

import java.io.*;

//转换流：只有从字节流转换到字符流的流，反之没有。
public class TransStreamDemo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //1.先获取/读取键盘的内容到内存中
        //1.1先获取/读取键盘的内容到内存中，该内容是字节流
        InputStream in = System.in;
//		int ch = in.read();
//		System.out.println(ch);
//		int ch1 = in.read();
//		System.out.println(ch1);

        //1.2再将字节转成字符，使用转换流
        InputStreamReader isr = new InputStreamReader(in);

//		int ch = isr.read();
//		System.out.println((char)ch);

        //1.3再使用缓冲读的字符流，提高效率
        BufferedReader bufr = new BufferedReader(isr);

        //小结：以上三个步骤可以简写为：务必记住，以后基本都这么写
        //（思路：先获取键盘上录入的字节流数据，再把其转化为字符流，并使用字符流缓冲流来提高效率）
        //BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        //2.再把内存中的数据输出到控制台上（注意：这是一种特殊的写入操作，以前都是写到硬盘/文件中）
        //2.1先获取键盘输出流，也是字节流，用于输出到控制台上（之前都是写到某个文件中，这里是直接输出到控制台上，务必要清楚）
        OutputStream out = System.out;
        //2.2再同样把其转化为字符流
        OutputStreamWriter osw = new OutputStreamWriter(out);
        //2.3也使用缓冲写的字符流
        BufferedWriter  bufw = new BufferedWriter(osw);

        //小结：以上三个步骤也可以简写为：务必记住，以后基本都这么写
        //（思路：先获取键盘输出流（一个特殊的输出流/显示流），它是字节流，再把其转化为字符流，并使用字符流缓冲流来提高效率）
        //BufferedWriter  bufw = new BufferedWriter(new OutputStreamWriter(System.out));


        String line = null;
        //2.4开始写
        while((line=bufr.readLine())!=null){//先读取到键盘上的内容
            if("over".equals(line))
                break;
//			System.out.println(line.toUpperCase());
//			osw.write(line.toUpperCase()+"\r\n");
//			osw.flush();

            bufw.write(line.toUpperCase());//再输出到控制台上（之前是写到某个文件中）
            bufw.newLine();
            bufw.flush();
        }


    }

}

