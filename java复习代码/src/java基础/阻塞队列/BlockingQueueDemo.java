package java基础.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue myBlockingQueue = new ArrayBlockingQueue(3);
        //list的元素是可以重复的
        System.out.println(myBlockingQueue.add("a"));
        System.out.println(myBlockingQueue.add("a"));
        System.out.println(myBlockingQueue.add("a"));

        System.out.println(myBlockingQueue.add("a"));//插不进去，会报异常
    }
}
