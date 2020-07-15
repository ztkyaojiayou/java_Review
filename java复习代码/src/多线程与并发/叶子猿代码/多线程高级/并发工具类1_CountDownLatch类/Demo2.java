package 多线程与并发.叶子猿代码.多线程高级.并发工具类1_CountDownLatch类;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//并发工具类1：CountDownLatch类（递减计数器锁存器）的使用（其实很简单）

//是什么：是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
//用给定的计数初始化 CountDownLatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，调用await方法的当前线程会一直受阻塞。
//之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次――计数无法被重置。
//如果需要重置计数，请考虑使用 CyclicBarrier。

//CountDownLatch 是一个通用同步工具，它有很多用途。
//当CountDownLatch的计数器初始化为1时，它用作一个简单的开/关锁存器，或入口：在通过调用 countDown() 的线程打开入口前，
//所有调用 await 的线程都一直在入口处等待。
//当CountDownLatch的计数器初始化为N时，它可以使一个（或多个）线程在 N 个线程完成某项操作之前一直等待，
//或者使其在某项操作完成 N 次之前一直等待。
//CountDownLatch 的一个有用特性是，它不要求调用 countDown 方法的线程等到计数到达零时才继续，
//而在所有线程都能通过之前，它只是阻止任何线程继续通过一个 await。

/**
 * 弊端：CountDownLatch是一次性的，即计数器的值只能在构造方法中初始化一次，
 * 之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
 *
 * 和join的区别：
 * 调用thread.join方法需要等待thread执行完毕后，当前线程才能继续向下执行,
 * 而CountDownLatch则通过计数器提供了更灵活的控制，只需要检查计数器的值为零就可以继续向下执行，
 * 而不用管相应的thread是否执行完毕。
 * 相比之下，CountDownLatch更加灵活一些，可以实现一些更加复杂的业务场景。
 *
 */

//需求（使用场景）：对指定文件中的数据按照行数进行多线程并发求和，最终对所有的和值汇总
public class Demo2 {

    private int[] nums;

    public Demo2(int line) {
        nums = new int[line];
    }

    //先分行求和：此时会有多个线程（有几行就有几个线程）在里面求和，
    //每执行完一个线程，latch就会减1，直到为0，则退出子线程，转去执行主线程(汇总）
    //注意：此时，主线程会一直等待它退出子线程
    public void calc(String line, int index, CountDownLatch latch) {
        String[] nus = line.split(","); // 切分出每个值
        int total = 0;
        for (String num : nus) {
            total += Integer.parseInt(num);
        }
        nums[index] = total; // 把计算的结果放到数组中指定的位置
        System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为：" + total);
        latch.countDown();//此方法为：递减锁存器的计数，如果计数达到零，则释放所有等待的线程
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
        int lineCount = contents.size();//表示目标文件的行数

        CountDownLatch latch = new CountDownLatch(lineCount);//传入的行数，也就是要创建的线程数，也是

        Demo2 d = new Demo2(lineCount);
        for (int i = 0; i < lineCount; i++) {//创建线程：有几行就创建几个
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j, latch);//调用calc方法
                }
            }).start();
        }
        //主线程，等子线程执行完毕之后才汇总，使用了latch.await()方法
        try {
            latch.await();//此方法的目的：是使当前线程（即主线程main）在锁存器倒计数达到零之前（即在子线程执行完之前）一直等待（除非线程被中断）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d.sum();//最后再对每一行的和汇总
    }

    //1.读取文件
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
     * 输出如下：
     * Thread-1 执行计算任务... 21,12,18,45,11 结果为：107
     * Thread-3 执行计算任务... 12,12 结果为：24
     * Thread-2 执行计算任务... 23,45,67,78,89 结果为：302
     * Thread-0 执行计算任务... 10,20,30,33,12,23 结果为：128
     * 汇总线程开始执行...
     * 最终的结果为：561
     */
}

