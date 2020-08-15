package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//创建线程的方式4:通过implements Callable创建有返回值的线程（重要）
//（后面会详细讲，即FutureTask类会涉及到）
//使用FutureTask类异步获取线程运行的结果
public class Demo4 implements Callable<Integer> {//里面有个call方法


    public static void main(String[] args) throws Exception {
        //1.先创建一个实现了Callable接口的线程任务task
        Demo4 task = new Demo4();
        //2.再创建一个FutureTask对象，把刚才创建的任务task传入其构造器
        FutureTask<Integer> result = new FutureTask<>(task);
        //3.创建一个线程，把刚才创建的FutureTask对象传进去（而不是向以前一样直接把任务传到这里）
        Thread t = new Thread(result);
        //4.再启动该线程
        t.start();
        //注意：以上四步属于标准做法，也可以由线程池完成，后面会讲
        System.out.println("我先干点别的。。。");

        Integer Result = result.get();//获取到该线执行完任务后的值
        System.out.println("计算结束，线程执行的结果为：" + Result);
    }

    //实现有返回值的call方法
    @Override
    public Integer call() throws Exception {//相当于run方法，只不过该方法有返回值，
        // 线程启动时会调用此方法（也只是作为线程任务存在）
        System.out.println("后台正在进行紧张的计算....");
        Thread.sleep(3000);
        return 1;
    }

}

