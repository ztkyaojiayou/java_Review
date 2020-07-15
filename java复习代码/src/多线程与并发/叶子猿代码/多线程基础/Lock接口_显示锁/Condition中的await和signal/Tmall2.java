package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁.Condition中的await和signal;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//3.lock接口中的线程通信，使用的是Condition中的await、signal和signalAll方法
//且一个锁可以绑定多个Condition对象（每个对象都可以单独调用await、signal和signalAll方法），则不易产生死锁

//再用Condition进行改造
public class Tmall2 {

    private int count;

    private Lock lock = new ReentrantLock();
    Condition p = lock.newCondition();
    Condition t = lock.newCondition();

    public final int MAX_COUNT = 10;

    public void push() {
        lock.lock();
        while (count >= MAX_COUNT) {
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量达到上限，生产者停止生产。");
                p.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + " 生产者生产，当前库存为：" + count);
        t.signal();
        lock.unlock();
    }

    public void take() {
        lock.lock();
        while (count <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量为零，消费者等待。");
                t.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " 消费者消费，当前库存为：" + count);
        p.signal();
        lock.unlock();
    }

}

