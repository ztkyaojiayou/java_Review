package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//创建线程的方式6:通过线程池来创建线程（非常重要，但一般要使用构造方法来自定义线程池）
public class Demo6 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();//一般不采用这样的方式创建线程池

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new Demo2() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        threadPool.shutdown();
    }

}

