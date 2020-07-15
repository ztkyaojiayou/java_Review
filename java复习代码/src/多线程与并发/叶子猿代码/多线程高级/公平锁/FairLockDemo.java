package 多线程与并发.叶子猿代码.多线程高级.公平锁;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的公平锁策略（默认是非公平的）
 * //和我理解的刚好相反~~
 * 公平锁：完全按照时间顺序来获取锁，则执行顺序是有序的
 * 非公平锁（默认）：谁先抢到谁就获取锁,即执行结果是混乱的（可是这为什么就是非公平的呢？？？这不挺公平的嘛）
 *
 * 此类的构造方法接受一个可选的公平 参数。当设置为 true 时，在多个线程的争用下，这些锁倾向于将访问权授予等待时间最长的线程。
 * 否则此锁将无法保证任何特定访问顺序。与采用默认设置（使用不公平锁）相比，
 * 使用公平锁的程序在许多线程访问时表现为很低的总体吞吐量（即速度很慢，常常极其慢），但是在获得锁和保证锁分配的均衡性时差异较小。
 * 不过要注意的是，公平锁不能保证线程调度的公平性。
 * 因此，使用公平锁的众多线程中的一员可能获得多倍的成功机会，这种情况发生在其他活动线程没有被处理并且目前并未持有锁时。
 * 还要注意的是，未定时的 tryLock 方法并没有使用公平设置。
 * 因为即使其他线程正在等待，只要该锁是可用的，此方法就可以获得成功。
 */


public class FairLockDemo {

    //Lock lock = new ReentrantLock(true);//设置为公平锁，则会按照线程的绝对顺序执行
    Lock lock = new ReentrantLock();//默认为非公平锁，则会按照系统的调度执行
    public void a () {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "------a");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {

        FairLockDemo t = new FairLockDemo();

        //创建四个线程，并同时执行同一个任务
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    t.a();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    t.a();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    t.a();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                    t.a();
            }
        }).start();

    }

}

