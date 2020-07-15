package 多线程与并发.叶子猿代码.多线程高级.公平锁.自己实现一把公平锁;


import java.util.ArrayList;
import java.util.List;

public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        try {
            queueObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this) {
                waitingThreads.remove(queueObject);
            }
            throw e;
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this 线程安全问题_lock锁接口");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }
}
