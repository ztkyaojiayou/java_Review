package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//2.使用lock实现队列，思路没什么区别
public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;
    private int removeIndex;
    private int queueSize;

    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        obj = new Object[count];
    }
//添加元素
    public void add(E e) {
        lock.lock();//主动上锁
        while (queueSize == obj.length) {
            try {
                addCondition.await();//元素满了以后，等待（删除）
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex] = e;

        if (++addIndex == obj.length) {
            addIndex = 0;
        }

        queueSize++;
        removeCondition.signal();//唤醒 删除线程
        lock.unlock();//主动释放锁
    }
//删除元素
    public void remove() {
        lock.lock();//主动上锁

        while (queueSize == 0) {
            try {
                removeCondition.await();//元素全部删完了以后，等待（添加）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        obj[removeIndex] = null;

        if (++removeIndex == obj.length) {
            removeIndex = 0;
        }

        queueSize--;

        addCondition.signal();//唤醒 添加线程

        lock.unlock();//主动释放锁
    }

}


