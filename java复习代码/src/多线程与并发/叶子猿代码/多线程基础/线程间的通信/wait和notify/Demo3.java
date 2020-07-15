package 多线程与并发.叶子猿代码.多线程基础.线程间的通信.wait和notify;


import java.util.concurrent.TimeUnit;

public class Demo3 {

    private volatile int signal;

    public synchronized void set () {//wait和notify（all）都必须要放在synchronized同步代码块中才可以被执行
        signal = 1;
        notify(); // notify方法会随机叫醒一个处于wait状态的线程，此时会拿到锁（加锁）
        //notifyAll();// notifyAll会叫醒所有的处于wait线程，但争夺到时间片的线程只有一个
        System.out.println("叫醒线程叫醒之后休眠开始...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized int get () {
        System.out.println(Thread.currentThread().getName() + " 方法执行了...");
        if(signal != 1) {
            try {
                wait();//当执行到wait方法时，会释放锁，因此其他线程会争夺cpu执行权，最终也只有一个线程拿到该锁进来
                System.out.println("叫醒之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 方法执行完毕...");
        return signal;
    }

    public static void main(String[] args) {

        Demo3 d = new Demo3();
        Target1 t1 = new Target1(d);
        Target2 t2 = new Target2(d);

        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1).start();

    }
}

