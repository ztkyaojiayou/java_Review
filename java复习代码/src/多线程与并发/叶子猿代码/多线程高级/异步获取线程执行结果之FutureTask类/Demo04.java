package 多线程与并发.叶子猿代码.多线程高级.异步获取线程执行结果之FutureTask类;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//(复制过来的，用于汇总）创建线程的方式4:通过implements Callable创建有返回值的线程
//（后面会详细讲，即FutureTask类会涉及到）
public class Demo04 implements Callable<Integer> {//里面有个call方法


    public static void main(String[] args) throws Exception {
        Demo04 task = new Demo04();
//创建一个任务，传入了一个实现了Callable接口的对象，该对象里面实现了call方法
        FutureTask<Integer> result = new FutureTask<>(task);

        //创建一个线程，把任务传进去（类比Runnable，也是把一个Runnable接口对象传进去，
        //且这里也是可以传入Runnable接口对象的）
        Thread t = new Thread(result);

        t.start();//启动该线程

        System.out.println("我先干点别的。。。");

        Integer Result = result.get();//获取到该线执行完任务后的值
        System.out.println("计算结束，线程执行的结果为：" + Result);
    }

    //实现有返回值的call方法
    @Override
    public Integer call() throws Exception {//相当于run方法，只不过该方法有返回值，线程启动时会调用此方法（也只是作为线程任务存在）
        System.out.println("后台正在进行紧张的计算....");
        Thread.sleep(3000);
        return 1;
    }

}

