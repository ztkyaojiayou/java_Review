package 数据结构与算法.离职后刷题;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author :zoutongkun
 * @date :2022/4/7 10:32 上午
 * @description :
 * @modyified By:
 */
public class TestCopyOnWriteVector {
    public static void main(String[] args) throws InterruptedException {
        //CopyOnWriteArrayList
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        Object o = new Object();
        Object o3 = new Object();
        objects.add(o);
        Object o1 = objects.get(0);

        //CopyOnWriteArraySet
        CopyOnWriteArraySet<Object> safeSet = new CopyOnWriteArraySet<>();
        safeSet.add(o);
        safeSet.remove(o);

        //普通队列（不支持并发安全）
        //ArrayDeque
        Deque<Object> arrayDeque = new ArrayDeque<>();
        PriorityQueue priorityQueue = new PriorityQueue();
        //并发队列（阻塞队列）
        DelayQueue delayQueue = new DelayQueue();

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add(o);

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(16);
        boolean add = arrayBlockingQueue.add(o);
        boolean offer = arrayBlockingQueue.offer(o);
        arrayBlockingQueue.put(o3);
    }
}
