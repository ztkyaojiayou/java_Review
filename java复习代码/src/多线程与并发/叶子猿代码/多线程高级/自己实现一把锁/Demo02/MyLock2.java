package 多线程与并发.叶子猿代码.多线程高级.自己实现一把锁.Demo02;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//方法2：使用AQS框架实现锁

/**
 * AQS使用了模板方法模式，且定义了两种资源共享模式，即独占和共享，本demo采用独占式。
 * 自定义同步器时需要重写下面几个AQS提供的模板方法:
 * (1)isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
 * //独占模式下相关的方法：
 * (2)tryAcquire(int)//独占方式。尝试获取资源，成功则返回true,失败则返回false。
 * (3)tryRelease( int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
 * //共享模式下相关的方法：
 * (4)tryAcquireShared (int)//共享方式。尝试获取资源。负数表示失败; 0表示成功，但没有
 * 剩余可用资源;正数表示成功，且有剩余资源。
 * (5)tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回
 * false。
 *
 * 注意：默认情况下，每个方法都抛出UnsupportedOperationException。这些方法的实现
 * 必须是内部线程安全的，并且通常应该简短而不是阻塞。AQS类中的其他方法都是final
 * 所以无法被其他类使用，只有这几个方法可以被其他类使用。
 */
public class MyLock2 implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {//著名的AQS框架：抽象队列同步器

        //tryAcquire(int)//独占方式。尝试获取资源，成功则返回true,失败则返回false。
        @Override
        protected boolean tryAcquire(int arg) {

            // 如果第一个线程进来，可以拿到锁，因此我们可以返回true

            // 如果第二个线程进来，则拿不到锁，返回false。有种特例，如果当前进来的线程和当前保存的线程是同一个线程，则可以拿到锁，但是有代价，要更新状态值

            // 如何判断是第一个线程进来还是其他线程进来？
            int state = getState();
            Thread t = Thread.currentThread();//当前线程

            if (state == 0) {
                if (compareAndSetState(0, arg)) {//使用著名的CAS机制，即先比较再设值
                    setExclusiveOwnerThread(t);
                    return true;
                }
            } else if (getExclusiveOwnerThread() == t) {
                setState(state + 1);//更新状态值
                return true;
            }
            return false;
        }
        //tryRelease( int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
        @Override
        protected boolean tryRelease(int arg) {

            // 锁的获取和释放肯定是一一对应的，那么调用此方法的线程一定是当前线程

            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new RuntimeException();
            }

            int state = getState() - arg;//每释放一个锁，该状态值就减1

            boolean flag = false;

            if (state == 0) {//直到为0时，表示全部释放
                setExclusiveOwnerThread(null);
                flag = true;
            }

            setState(state);

            return flag;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

    }

    //获取锁
    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }
//释放锁
    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

}

