package java基础.IO流_毕向东.字符流.缓冲字符流;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//案例：复制一个文件，这里先把数据存入到一个自定义的字符数组中，然后再一次性存入目标文件
//即比原来的“一个一个字符直接往目标文件存”的方法效率还是要高一点，因为用到了缓存的思想，但后面会直接使用缓冲流来解决
public class CopyTextTest_2 {

    private static final int BUFFER_SIZE = 1024;//一般都选1024

    /**
     * @param args
     */
    public static void main(String[] args) {

        FileReader fr = null;
        FileWriter fw = null;
        try {//同理，要进行异常处理
            fr = new FileReader("原文件.txt");
            fw = new FileWriter("目的地.txt");

            //自己创建一个临时的数组，用于缓存读取到的字符。
            char[] buf = new char[BUFFER_SIZE];//这就是缓冲区。

            //定义一个变量记录读取到的字符数，(其实就是往数组里装的字符个数 )
            int len = 0;

            while((len=fr.read(buf))!=-1){//先将字符读入缓存区数组
                fw.write(buf, 0, len);//再一次性从缓存区把字符写入到目标文件
            }

        } catch (Exception e) {
//			System.out.println("读写失败");
            throw new RuntimeException("读写失败");
        }finally{
            //关闭原则为：先使用的后关闭
            if(fw!=null)//先关闭写流
                try {//再次进行异常处理
                    fw.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            if(fr!=null)//后关闭读流
                try {
                    fr.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
        }
    }

}
