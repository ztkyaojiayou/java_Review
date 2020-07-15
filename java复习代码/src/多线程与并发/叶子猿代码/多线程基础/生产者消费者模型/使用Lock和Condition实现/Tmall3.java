package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Lock和Condition实现;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Shop接口的具体实现3，即方法3：使用阻塞队列实现（不难理解，字节跳动喜欢问）
public class Tmall3 implements Shop {

    public final int MAX_COUNT = 10;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    public void push() {
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take() {
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void size() {
        while (true) {
            System.out.println("当前队列的长队为：" + queue.size());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

