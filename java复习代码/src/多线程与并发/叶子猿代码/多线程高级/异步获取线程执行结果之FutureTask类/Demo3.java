package 多线程与并发.叶子猿代码.多线程高级.异步获取线程执行结果之FutureTask类;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Demo3 {

    public static void main(String[] args) {
        //第一种方式：使用线程池创建线程（也是使用的demo2的任务）
        ExecutorService executor = Executors.newCachedThreadPool();
        task02 task03 = new task02();
        FutureTask<Integer> result03 = new FutureTask<Integer>(task03);
        executor.submit(result03);//执行任务到
        executor.shutdown();//关闭线程池

        //（老方式）第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
//        task02 task = new task02();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            if(result03.get()!=null){
                System.out.println("task运行结果"+result03.get());
            }else{
                System.out.println("future.get()未获取到结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
