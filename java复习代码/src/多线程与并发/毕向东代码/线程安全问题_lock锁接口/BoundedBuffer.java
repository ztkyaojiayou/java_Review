package 多线程与并发.毕向东代码.线程安全问题_lock锁接口;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//关于lock接口的使用的官方例子
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();//加锁
        try {
            while (count == items.length) {
                notFull.await();//等待
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();//唤醒对方的锁
        } finally {
            lock.unlock();//释放锁
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();//加锁
        try {
            while (count == 0) {
                notEmpty.await();//等待
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();//唤醒
            return x;
        } finally {
            lock.unlock();//释放锁
        }
    }
}

