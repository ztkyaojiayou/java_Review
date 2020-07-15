package 多线程与并发.叶子猿代码.多线程高级.异步获取线程执行结果之FutureTask类;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//异步获取线程执行结果之FutureTask类
//注意：FutureTask实现了RunnableFuture接口，而RunnableFuture继承了Runnable和Future，
//也就是说FutureTask既是Runnable，也是Future。

//是什么：FutureTask可用于异步获取执行结果或取消执行任务的场景。
//通过传入Runnable或者Callable的任务给FutureTask，直接调用其run/call方法或者放入线程池执行，
//之后可以在外部通过FutureTask的get方法异步获取执行结果
//因此，FutureTask非常适合用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。

//另外，FutureTask还可以确保即使调用了多次run方法，它都只会执行一次Runnable或者Callable任务，
//或者通过cancel取消FutureTask的执行等。

//使用场景：可用于异步获取执行结果或取消执行任务的场景。
public class Demo1 {

    /**
     * Callalbe和Runnable的区别（整体类似）
     * （总体而言，Callalbe中的call方法和Runnable方法中的run方法类似，都是一个线程的执行任务）

     * Runnable中的run方法是被线程调用的，在run方法是异步执行的，没有返回值
     *
     * Callable的call方法，不是异步执行的，是由Future的run方法调用的，有返回值
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//call方法相当于run方法，只不过有返回值，也就是一个线程任务（其实标准写法应该是“创建线程的方式”包中的Demo4）
        Callable<Integer> task01 = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };
//1.在这里传入Callable的call方法中的（线程）任务给FutureTask，于是就可以异步获取该任务中的值啦，这就是目的
        FutureTask<Integer> result01 = new FutureTask<>(task01);
//2.再把task交给线程去执行
        Thread thread = new Thread(result01);
        thread.start();//线程开始时，会调用/执行call方法

        // do something
        System.out.println(" 我先干点别的...");
//3.通过FutureTask的get方法异步获取刚才call方法执行的结果（这是重点，对于继承thread和实现runnable是做不到这一点的）
        Integer result = result01.get();

        System.out.println("拿到的结果为：" + result);

    }

}

