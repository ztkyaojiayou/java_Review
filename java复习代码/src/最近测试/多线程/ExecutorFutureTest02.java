package 最近测试.多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class ExecutorFutureTest02 {
    public static final ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        // 任务1--异步执行
        Future<Boolean> booleanTask = service.submit(() -> {
            System.out.println("任务1+booleanTask--6s");
            Thread.sleep(6000);
            int a = 1/0;
            System.out.println("任务1--执行完毕");
            return true;
        });
        futures.add(booleanTask);

        // 任务2--异步执行
        Future<String> stringTask = service.submit(() -> {
            System.out.println("任务2+stringTask--3s");
            Thread.sleep(3000);
            System.out.println("任务2--执行完毕");
            return "Hello World";
        });
        futures.add(stringTask);
//        futures.add(booleanTask);
        System.out.println("执行完就拿结果+stringTask1" +" " +stringTask.get());
        System.out.println("执行完就拿结果+booleanTask1" + " " +booleanTask.get());
        System.out.println("执行完就拿结果+stringTask1" +" " +stringTask.get());
        // 任务3--异步执行
        Future<Integer> integerTask = service.submit(() -> {
            System.out.println("任务3+integerTask--10s");
            Thread.sleep(10000);
            System.out.println("任务3--执行完毕");
            return new Random().nextInt(100);
        });
        futures.add(integerTask);
        //此时main线程会等待/阻塞，直到拿到该结果
        System.out.println("执行完就拿结果+integerTask2" + integerTask.get());
        System.out.println("执行完就拿结果+booleanTask2" + booleanTask.get());
        System.out.println("执行完就拿结果+stringTask2" + stringTask.get());
//            while (true) {
//                if (booleanTask.isDone() && !booleanTask.isCancelled()) {
//                    Boolean result = booleanTask.get();
//                    System.err.println("任务1-10s： " + result);
//                    break;
//                }
//            }
//            while (true) {
//                if (stringTask.isDone() && !stringTask.isCancelled()) {
//                    String result = stringTask.get();
//                    System.err.println("任务2-3s： " + result);
//                    break;
//                }
//            }
//            while (true) {
//                if (integerTask.isDone() && !integerTask.isCancelled()) {
//                    Integer result = integerTask.get();
//                    System.err.println("任务3-2s：" + result);
//                    break;
//                }
//            }

        //处理结果
        for (Future future : futures) {
            try {
                System.out.println(future.get()+"3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 执行时间
        System.err.println("任务结果处理完成的时间: " + (System.currentTimeMillis() - start));


    }
}
