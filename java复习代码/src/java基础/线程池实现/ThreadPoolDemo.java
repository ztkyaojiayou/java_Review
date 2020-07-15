package java基础.线程池实现;

import java.util.concurrent.*;

//创建一个线程任务类
class myThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程执行了该任务了----------");
    }
}
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //自己创建一个线程池
        ExecutorService myThreadPool = new ThreadPoolExecutor(3,//常驻线程为3个
                7,//最大的线程数为7个
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),//等待队列里可以放3个任务
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //创建一个具体的线程任务
        myThread myTask = new myThread();
        try {
            /**
             * 执行12次任务，由于同时最大可以处理7个任务，在等待队列里面等待3个任务，
             * 因此这个时候会有多余的任务:
             * 此时，看不同拒绝策略的反馈情况：
             * 1）若为AbortPolicy(),则就会拒绝多余的任务，报出异常，
             * 即java.util.concurrent.RejectedExecutionException: Task java基础.线程池实现.myThread@2503dbd3 rejected from java.util
             * 2）若为CallerRunsPolicy():则不会报异常，而是多余的任务会由“调用该线程池的”线程来执行，
             * 即谁调用了该线程池，就会让该线程来执行多余的任务，
             * 比如这个就为main线程，总执行的任务不变，但由于线程池处理的速度不同，
             * 可能会导致真正要执行的任务不确定，剩余的任务全为“调用者”线程任务，
             * 比如，可能只执行了10个任务，则还有一个任务则为主线程来执行，
             * 也有可能只执行了9个任务，则还有两个任务由主线程来执行，
             * 当然也还有可能都执行完毕了，则就没有调用者线程啥事了，
             * 总之，总任务都会执行完毕，只是可能会有“调用者”线程来帮着执行（关键）
             * 即多余的任务由调用该线程池的线程来执行（关键）
             * 比如：pool-1-thread-2线程执行了该任务来----------
             *      main线程执行了该任务来----------
             *      pool-1-thread-1线程执行了该任务来----------
             * 3）若为DiscardPolicy()，则将直接丢弃没有处理的新任务，但也有可能全部可以处理完毕(与执行效率有关）
             * 4）若为DiscardOldestPolicy()，则将丢弃最早没有处理的任务，但同样也有可能全部可以处理完毕(与执行效率有关）
             */
            for (int i = 1; i < 12; i++) {
                System.out.println("任务"+i);
              myThreadPool.execute(myTask);//执行/启动线程任务
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            myThreadPool.shutdown();//关闭线程池，重要
        }
    }
}
