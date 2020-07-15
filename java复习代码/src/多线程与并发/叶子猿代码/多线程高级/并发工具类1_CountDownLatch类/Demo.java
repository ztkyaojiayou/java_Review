package 多线程与并发.叶子猿代码.多线程高级.并发工具类1_CountDownLatch类;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//采用原始方法对指定文件中的数据按照行数进行多线程并发求和，最终对所有的和值汇总
public class Demo {

    private int[] nums;

    public Demo(int line) {
        nums = new int[line];
    }

    //先拆分后分别求和
    public void calc(String line, int index) {
        String[] nus = line.split(","); // 切分出每个值
        int total = 0;
        for (String num : nus) {
            total += Integer.parseInt(num);
        }
        nums[index] = total; // 把计算的结果放到数组中指定的位置
        System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为：" + total);
    }
    //再汇总
    public void sum() {
        System.out.println("汇总线程开始执行... ");
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        System.out.println("最终的结果为：" + total);
    }

    public static void main(String[] args) {

        List<String> contents = readFile();
        int lineCount = contents.size();

        Demo d = new Demo(lineCount);
        for (int i = 0; i < lineCount; i++) {//创建线程，有几行就创建几个线程
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j);
                }
            }).start();
        }

////若有这一段，则汇总线程永远不会执行，会一直自旋
//        while (Thread.activeCount() > 1) {
//            //当线程还没有执行完毕时，一直等待/自旋
////            System.out.println("啥情况啊——————————————");
//        }

        d.sum();
    }

    //读取文件
    private static List<String> readFile() {
        List<String> contents = new ArrayList<>();
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/Users/zoutongkun/Desktop/叶子猿版多线程-代码/nums.txt"));
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return contents;
    }

    /**
     * 结果如下：（易知，没有达到预想的目的，
     * 此时的汇总线程并不特殊，并没有等所有子线程都执行完毕之后才执行）
     * Thread-0 执行计算任务... 10,20,30,33,12,23 结果为：128
     * Thread-2 执行计算任务... 23,45,67,78,89 结果为：302
     * Thread-1 执行计算任务... 21,12,18,45,11 结果为：107
     * 汇总线程开始执行...
     * Thread-3 执行计算任务... 12,12 结果为：24
     * 最终的结果为：561
     */
}

