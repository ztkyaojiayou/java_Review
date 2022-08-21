package 最近测试.多线程;

import java.util.concurrent.*;

/**
 * @author :zoutongkun
 * @date :2022/8/21 1:09 下午
 * @description :
 * @modyified By:
 */
public class ScheduleExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("doSomething");
            //5000:第一次调度时的等待时间，1000：之后每次调度的时间间隔
        }, 5000, 1000, TimeUnit.MILLISECONDS);
    }
}
