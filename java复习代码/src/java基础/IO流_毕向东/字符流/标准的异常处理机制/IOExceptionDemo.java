package java基础.IO流_毕向东.字符流.标准的异常处理机制;

import java.io.FileWriter;
import java.io.IOException;

//IO流中异常的标准处理（掌握）
//以写入/存储为主
public class IOExceptionDemo {

    private static final String LINE_SEPARATOR = System
            .getProperty("line.separator");

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {

        FileWriter fw = null;
        try {//开始第一次异常处理：因为有可能指定文件存在，但它是一个目录，而不是一个常规文件；
            // 或者该文件不存在，但无法创建它；抑或因为其他某些原因而无法打开它

            fw = new FileWriter("k:\\demo.txt");

            fw.write("abcde" + LINE_SEPARATOR + "hahaha");

        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (fw != null)
                try {//开始第一次异常处理：因为该方法有可能发生 I/O 错误，即可能用于硬件的原因无法正常关闭
                    fw.close();
                } catch (IOException e) {
                    // code....
                    throw new RuntimeException("刷新失败");
                }
        }

    }

}

