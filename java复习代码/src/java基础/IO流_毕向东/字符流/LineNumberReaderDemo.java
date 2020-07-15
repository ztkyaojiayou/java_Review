package java基础.IO流_毕向东.字符流;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

//LineNumberReader：设置和获取文件中的行号
public class LineNumberReaderDemo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //目标文件
        FileReader fr = new FileReader("IO123.txt");
        LineNumberReader lnr = new LineNumberReader(fr);//获取LineNumberReader对象

        String line = null;
        lnr.setLineNumber(100);//设置初始行号
        while((line=lnr.readLine())!=null){
            System.out.println(lnr.getLineNumber()+":"+line);//读取带有行号的数据
        }

        lnr.close();
    }
}

