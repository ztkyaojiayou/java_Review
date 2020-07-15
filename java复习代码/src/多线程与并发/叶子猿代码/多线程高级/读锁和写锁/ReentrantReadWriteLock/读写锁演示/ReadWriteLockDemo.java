package 多线程与并发.叶子猿代码.多线程高级.读锁和写锁.ReentrantReadWriteLock.读写锁演示;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//ReentrantReadWriteLock的演示

/**
 * /**
 *  * ReentrantReadWriteLock:可重入的读写锁
 *  * 背景：
 *  * 现实中有这样一种场景：对共享资源有读和写的操作，且写操作没有读操作那么频繁。
 *  * 在没有写操作的时候，多个线程同时读一个资源没有任何问题，
 *  * 所以应该允许多个线程同时读取共享资源；
 *  * 但是如果一个线程想去写这些共享资源，就不应该允许其他线程对该资源进行读和写的操作了。
 *  * 即读读可共享，但读写和写写都互斥（关键）
 *  * 方案：
 *  * 针对这种场景，JAVA的并发包提供了读写锁ReentrantReadWriteLock，它表示两个锁，
 *  * 一个是读操作相关的锁，称为共享锁；一个是写相关的锁，称为独占锁/互斥锁，描述如下：
 *  *
 *  * （1）线程进入读锁的前提条件：
 *  * 没有其他线程的写锁(但可以有读锁）
 *  * 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个。
 *  *
 *  * （2）线程进入写锁的前提条件：
 *  * 没有其他线程的读锁
 *  * 没有其他线程的写锁
 *  *
 *  * （3）而读写锁有以下三个重要的特性：
 *  *  3.1公平选择性：支持非公平（默认）和公平的锁获取方式，吞吐量还是非公平优于公平。
 *  *  3.2可重入：读锁和写锁都支持线程重进入。
 *  *  3.3锁降级：可以先获取写锁、再获取读锁，接着释放写锁，从而降级成读锁；即写锁能够降级成为读锁。
 *  *     但反之不行，即不可以先获取读锁，再获取写锁，接着释放读锁而升级为写锁，只能先把读锁释放后，才可以获取写锁
 *  *
 *  *   所以总逻辑可以是:先获取读锁，再释放读锁，再获取写锁，此时可以再获取读锁，再释放写锁，最后再释放读锁
 *  */

public class ReadWriteLockDemo {

    private Map<String, Object> map = new HashMap<>();
    //先得到一个可重入的读写锁对象
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    //再得到一个读锁和写锁对象（此时还没有加锁，只是获取到了一个对象）
    private Lock readLock = rwl.readLock();//得到一个读锁对象
    private Lock writeLock = rwl.writeLock();//得到一个写锁对象

    //读操作
    public Object get(String key) {
        readLock.lock();//先获取读锁
        System.out.println(Thread.currentThread().getName() + " 读操作在执行..");
        try {
            try {//休息一会儿
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);//处理读任务
        } finally {
            readLock.unlock();//再释放读锁
            System.out.println(Thread.currentThread().getName() + " 读操执行完毕..");
        }
    }
    //写操作
    public void put(String key, Object value) {
        writeLock.lock();//获取写锁
        System.out.println(Thread.currentThread().getName() + " 写操作在执行..");
        try {
            try {//休息一会儿
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);//处理写任务
        } finally {
            writeLock.unlock();//释放写锁
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕..");
        }
    }

    //测试
    public static void main(String[] args) {

        //主线程（写）
        ReadWriteLockDemo d = new ReadWriteLockDemo();
        d.put("key1", "value1");

        //线程1（读）
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();
        //线程2（读和写）
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key2", "value2");
                System.out.println(d.get("key2"));
            }
        }).start();


        ////子线程（读）
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				d.put("key2", "value2");
//			}
//		}).start();
    }

}

