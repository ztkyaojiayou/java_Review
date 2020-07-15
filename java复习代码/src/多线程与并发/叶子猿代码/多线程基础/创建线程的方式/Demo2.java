package 多线程与并发.叶子猿代码.多线程基础.创建线程的方式;


//创建线程的方式2:implements Demo2.java
/**
 * 作为线程任务存在
 *
 * @author worker
 *
 */
public class Demo2 implements Runnable {

    @Override
    public void run() {
        while(true) {
            System.out.println("thread running ...");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo2());
        thread.start();
    }

}

