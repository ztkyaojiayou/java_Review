package 多线程与并发.叶子猿代码.多线程基础.单例模式;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//多线程下的测试
public class MultiThreadMain {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        for(int i = 0;i<20;i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //懒汉式双重检测加锁版
                    System.out.println(Thread.currentThread().getName() + ":" +Singleton2.getInstance());
                    //饿汉式
                    System.out.println(Thread.currentThread().getName() + ":" +Singleton.getInstance());
                }
            });
        }

        threadPool.shutdown();//主动结束线程


    }

}

