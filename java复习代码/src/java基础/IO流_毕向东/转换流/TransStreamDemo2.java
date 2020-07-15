package java基础.IO流_毕向东.转换流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TransStreamDemo2 {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        /*
         * 以下三个经典案例的思路和代码很重要，务必掌握：
         * 1,需求：将键盘录入的数据写入到一个文件中。（写）
         *
         * 2,需求：将一个文本文件内容显示在控制台上。（读）
         *
         * 3,需求：将一个文件文件中的内容复制到的另一个文件中。（读+写）
         */

        //从键盘上输入的常见写法，务必掌握
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("b.txt")));


        String line = null;

        while((line=bufr.readLine())!=null){//读取/获取到从键盘上录入的数据（读）
            if("over".equals(line))
                break;

            bufw.write(line.toUpperCase());//再把从键盘上输入的内容变成大写再存入b.txt文件中（写）
            bufw.newLine();
            bufw.flush();
        }


    }

}

