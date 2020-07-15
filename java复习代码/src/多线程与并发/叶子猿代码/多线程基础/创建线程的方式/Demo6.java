package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//创建线程的方式6:通过线程池来创建线程（主要就是一些API的应用，没有什么技术含量，但常用）
public class Demo6 {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

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

