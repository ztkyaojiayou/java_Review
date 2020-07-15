package 多线程与并发.叶子猿代码.多线程高级.自己实现一把锁.Demo01;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//自己实现一把锁
//方法1：使用“synchronized关键字+等待唤醒机制”实现，很容易理解
//关键就是实现lock方法和unlock方法
public class MyLock implements Lock {

    private boolean isLocked = false;//“是否上锁成功”标志

    private Thread lockBy = null;//“哪个线程获取到了该锁”标志

    private int lockCount = 0;//获取到该锁的次数

    //上锁
    @Override
    public synchronized void lock() {//加上synchronized关键字，实现同步
        // ...

        Thread currentThread = Thread.currentThread(); // Thread-0

        while (isLocked && currentThread != lockBy)//若没有获取到锁，就等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        isLocked = true;//表示上锁成功
        lockBy = currentThread;//获取到该锁的线程即为当前线程
        lockCount ++; //每获取一次就加1，表示当前线程获得了一次锁 1   2
    }

    //释放锁
    @Override
    public synchronized void unlock() {//加上synchronized关键字，实现同步
        if(lockBy == Thread.currentThread()) {//若为当前获取到了该锁的线程
            lockCount --;  //则每释放一次，该次数就减1，直到为0。

            if(lockCount == 0) {
                notify();//唤醒另外一个线程，同时自己释放该锁
                isLocked = false;//标志重新置为false
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }



    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }

}

