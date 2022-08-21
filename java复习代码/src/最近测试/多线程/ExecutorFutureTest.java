package 最近测试.多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 分析：
 * 因为我们一开始用 Thread1.get() 获取第一个线程的结果时，是阻塞的，
 * 而且我们假定任务1执行了10s钟，导致了线程2（3s就执行完任务）和线程3（2s就执行完任务）
 * 都执行完了任务，也不打印出来。那在实际业务中，这种方法肯定是不可取的。
 * @author :zoutongkun
 * @date :2022/8/18 3:59 下午
 * @description :
 * @modyified By:
 */
public class ExecutorFutureTest {
    public static final ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int i = 0;i<3;i++){
            //添加任务
            int finalI1 = i;
            Future<Integer> task = service.submit(() -> {
                System.out.println("任务"+finalI1+"执行开始");
//                //阻塞的并不是线程池中的线程，而是主线程！！！
//                        Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
//                int a = 1/0;
                System.out.println("任务"+finalI1+"执行完毕");
                return finalI1;
            });
            // 执行时间
            System.err.println(i+"任务执行完成的时间: " + (System.currentTimeMillis() - start));
            futures.add(task);
        }
        // 执行时间
        System.err.println("任务执行完成的时间: " + (System.currentTimeMillis() - start));
//        Thread.sleep(5000);
        //处理结果
        for (Future future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 执行时间
        System.err.println("任务结果处理完成的时间: " + (System.currentTimeMillis() - start));

    }
}
